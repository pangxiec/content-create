package com.create.biz.factory;

import com.create.biz.service.LoginLogService;
import com.create.biz.service.SysLogOperateService;
import com.create.common.utils.AddressUtil;
import com.create.common.utils.IpUtil;
import com.create.common.utils.ServletUtils;
import com.create.pojo.domain.LoginLog;
import com.create.pojo.domain.SysOperateLog;
import com.create.pojo.dto.LoginLogDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.TimerTask;

/**
 * 生产异步任务工厂（产生需要异步执行的TimerTask）
 * @author xmy
 * @date 2021/2/19 16:27
 */
@Component
public class AsyncTaskFactory {
    private LoginLogService logLoginService;

    private SysLogOperateService logOperateService;

    @Autowired
    public void setLogLoginService(LoginLogService logLoginService) {
        this.logLoginService = logLoginService;
    }

    @Autowired
    public void setLogOperateService(SysLogOperateService logOperateService) {
        this.logOperateService = logOperateService;
    }

    /**
     * 记录登陆信息日志
     *
     * @param success 登录成功与否
     * @param message 消息
     * @param args    列表
     * @return 任务task
     */
    public TimerTask recordLoginLog(Boolean success, String message, final Object... args) {
        HttpServletRequest request = ServletUtils.getRequest();
        String username = request.getParameter("username");
        String ipAddr = IpUtil.getIpAddr();
        String location = AddressUtil.getCityInfo(ipAddr);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String os = userAgent.getOperatingSystem().getName();
        String browser = userAgent.getBrowser().getName();
        return new TimerTask() {
            @Override
            public void run() {
                LoginLogDTO logLoginDO = new LoginLogDTO();
                logLoginDO.setLoginUser(username);
                logLoginDO.setLoginIp(ipAddr);
                logLoginDO.setLoginAddress(location);
                logLoginDO.setLoginTime(new Date());
                logLoginDO.setBrowser(browser);
                logLoginDO.setOs(os);
                LoginLog(logLoginDO);
            }
        };
    }

    public void LoginLog(LoginLogDTO loginLogDto){
        LoginLog loginLog = new LoginLog();
        BeanUtils.copyProperties(loginLogDto,loginLog);
        logLoginService.save(loginLog);
    }

    /**
     * 操作日志记录
     *
     * @param logOperate 操作日志信息
     * @return 任务task
     */
    public TimerTask recordOperateLog(final SysOperateLog logOperate) {
        return new TimerTask() {
            @Override
            public void run() {
                logOperateService.save(logOperate);
            }
        };
    }
}
