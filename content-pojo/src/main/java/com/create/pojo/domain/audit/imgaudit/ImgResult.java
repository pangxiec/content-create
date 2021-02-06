package com.create.pojo.domain.audit.imgaudit;

import lombok.Data;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/6 12:27
 */
@Data
public class ImgResult {

    /**
     * 随机UUID
     */
    private String dataId;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 审核二级结果(一张图片不同审核类型的结果)
     */
    private List<SubResult> subResults;

}
