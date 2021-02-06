package com.create.pojo.domain.audit.textaudit;

import lombok.Data;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/6 9:39
 */
@Data
public class TextResult {

    private String suggestion;
    private String label;
    private double rate;
    private List<Detail> details;

}
