package com.create.pojo.domain.audit.imgaudit;

import lombok.Data;

/**
 * @author xmy
 * @date 2021/2/6 12:28
 */
@Data
public class SubResult {

    private String label; //审核结果, normal、porn...
    private String suggestion; //审核意见, pass、block、review...
    private float rate; //匹配百分比
    private String scene; //审核类型, porn、terrorism...

}
