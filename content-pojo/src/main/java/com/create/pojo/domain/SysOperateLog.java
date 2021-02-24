package com.create.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xmy
 * @date 2021/2/19 16:41
 */
@Data
public class SysOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "操作描述")
    private String operateDescription;

    @ApiModelProperty(value = "操作用户")
    private String operatePeople;

    @ApiModelProperty(value = "权限值")
    private String permissions;

    @ApiModelProperty(value = "消耗时间")
    private Integer spendTime;

    @ApiModelProperty(value = "响应状态")
    private Integer result;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "操作地址")
    private String operateAddress;

    @ApiModelProperty(value = "操作时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
