package com.create.common.config.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xmy
 * @date 2021/1/1 10:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

}
