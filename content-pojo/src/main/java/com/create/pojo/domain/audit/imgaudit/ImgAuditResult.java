package com.create.pojo.domain.audit.imgaudit;

import lombok.Data;

import java.util.List;

/**
 * @author xmy
 * @date 2021/2/6 12:26
 */
@Data
public class ImgAuditResult {
    private List<ImgResult> results;
}

