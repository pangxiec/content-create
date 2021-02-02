package com.create.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xmy
 * @date 2021/2/1 14:32
 */
@AllArgsConstructor
@Getter
public enum AuditStatusEnum {
    WAIT(0,"审核中"),
    PASS(1,"已发布"),
    REVIEW(2,"待人工审核"),
    BLOCK(3,"未通过");
    private Integer code;
    private String msg;
}
