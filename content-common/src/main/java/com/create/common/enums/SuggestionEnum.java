package com.create.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xmy
 * @date 2021/2/6 14:39
 */
@AllArgsConstructor
@Getter
public enum SuggestionEnum {
    PASS(0,"通过"),
    BLOCK(1,"不通过"),
    REVIEW(2,"人工审核");

    @EnumValue
    private Integer code;
    private String msg;
}
