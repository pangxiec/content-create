package com.create.biz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.green.model.v20180509.VideoAsyncScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest;
import com.aliyuncs.imageaudit.model.v20191230.ScanImageResponse;
import com.aliyuncs.imageaudit.model.v20191230.ScanTextRequest;
import com.aliyuncs.imageaudit.model.v20191230.ScanTextResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.create.biz.config.AliyunConfig;
import com.create.common.utils.FastJsonUtil;
import com.create.pojo.domain.audit.imgaudit.ImgAuditResult;
import com.create.pojo.domain.audit.imgaudit.ImgResult;
import com.create.pojo.domain.audit.textaudit.TextAuditResult;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author xmy
 * @date 2021/2/6 14:27
 */
@Component
public class AliAuditUtil {
    @Resource
    private AliyunConfig aliyunConfig;
    private static DefaultProfile profile;
    private static IAcsClient client;
    private static List<ScanTextRequest.Labels> labelList = new ArrayList<>();

    @PostConstruct
    public void init(){
        profile = DefaultProfile.getProfile(aliyunConfig.getRegionId(), aliyunConfig.getAccessKeyId(), aliyunConfig.getSecret());
        client = new DefaultAcsClient(profile);

        //初始化文本审核类型
        for(String str : aliyunConfig.getLabelList()){
            ScanTextRequest.Labels label = new ScanTextRequest.Labels();
            label.setLabel(str);
            labelList.add(label);
        }
    }

    /**
     * 文本审核
     * @param contents
     * @return
     * @throws Exception
     */
    public TextAuditResult auditText(List<String> contents) throws Exception{
        ScanTextRequest request = new ScanTextRequest();
        ArrayList<ScanTextRequest.Tasks> taskList = new ArrayList<>();
        for (String content : contents){
            ScanTextRequest.Tasks tasks = new ScanTextRequest.Tasks();
            tasks.setContent(content);
            taskList.add(tasks);
        }
        request.setTaskss(taskList);
        request.setLabelss(labelList);

        ScanTextResponse response = client.getAcsResponse(request);
        TextAuditResult result = FastJsonUtil.parseToClass(JSONObject.parseObject(FastJsonUtil.parseToJSON(response)).getJSONObject("data").toJSONString(), TextAuditResult.class);
        return result;
    }

    /**
     * 图片审核
     * @param urls
     * @return
     * @throws Exception
     */
    public ImgAuditResult auditImg(List<String> urls) throws Exception{
        ImgAuditResult finalResult = new ImgAuditResult();
        finalResult.setResults(new ArrayList<ImgResult>());
        //一次最多审核10张
        for(int i = 0; i < urls.size(); i+=10){
            List<String> sub = urls.subList(i, Math.min(i+10, urls.size()));
            ScanImageRequest request = new ScanImageRequest();
            ArrayList<ScanImageRequest.Task> taskList = new ArrayList<>();
            for (String url : sub){
                ScanImageRequest.Task task = new ScanImageRequest.Task();
                task.setDataId(UUID.randomUUID().toString().replace("-", ""));
                task.setImageURL(url);
                taskList.add(task);
            }
            request.setScenes(aliyunConfig.getSceneList());
            request.setTasks(taskList);

            ScanImageResponse response = client.getAcsResponse(request);
            ImgAuditResult tempResult = FastJsonUtil.parseToClass(JSONObject.parseObject(FastJsonUtil.parseToJSON(response)).getJSONObject("data").toJSONString(), ImgAuditResult.class);
            finalResult.getResults().addAll(tempResult.getResults());
        }
        return finalResult;
    }

    /**
     * 视频审核
     *
     * @param url
     * @param postId
     * @return
     * @throws Exception
     */
    public Boolean auditVideo(String url, String postId) throws Exception{
        IAcsClient client = new DefaultAcsClient(profile);
        VideoAsyncScanRequest videoAsyncScanRequest = new VideoAsyncScanRequest();

        List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
        Map<String, Object> task = new LinkedHashMap<String, Object>();
        // dataId需设置为postId以说明视频来源
        task.put("dataId", postId);
        task.put("url", url);
        tasks.add(task);

        JSONObject data = new JSONObject();
        data.put("scenes", aliyunConfig.getSceneList());
        data.put("tasks", tasks);
        data.put("callback", aliyunConfig.getCallback());
        data.put("seed", aliyunConfig.getSeed());

        videoAsyncScanRequest.setHttpContent(data.toJSONString().getBytes("UTF-8"), "UTF-8", FormatType.JSON);
        HttpResponse httpResponse = client.doAction(videoAsyncScanRequest);
        if(httpResponse.isSuccess()){
            JSONObject jsonObject = JSON.parseObject(new String(httpResponse.getHttpContent(), "UTF-8"));
            System.out.println(JSON.toJSONString(jsonObject, true));
            return true;
        }else{
            System.out.println("response not success. status:" + httpResponse.getStatus());
            return false;
        }
    }
}
