package com.create.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xmy
 * @date 2021/2/1 14:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AuditStatusEnum {

    WAIT(0,"待审核"),
    PASS(1,"已发布"),
    BLOCK(2,"未通过");

    private Integer code;
    private String msg;
}
