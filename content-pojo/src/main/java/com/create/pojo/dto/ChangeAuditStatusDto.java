package com.create.pojo.dto;

import com.create.common.enums.AuditStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xmy
 * @date 2021/2/6 14:48
 */
@Data
public class ChangeAuditStatusDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;

    private AuditStatusEnum status;

    private String auditAdmin;

}
