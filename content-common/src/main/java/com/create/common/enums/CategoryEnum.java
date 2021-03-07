package com.create.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xmy
 * @date 2021/2/1 14:31
 */
@AllArgsConstructor
@Getter
public enum CategoryEnum {

    STUDY(0,"学习"),
    LIFE(1,"生活"),
    BOLE(2,"伯乐"),
    EXPERIENCE(3,"经验");

    @EnumValue
    private Integer code;
    private String msg;

}
