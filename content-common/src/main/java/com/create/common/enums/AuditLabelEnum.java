package com.create.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xmy
 * @date 2021/3/5 11:16
 */
@AllArgsConstructor
@Getter
public enum AuditLabelEnum {
    NORMAL(0, "正常内容"),
    SEXY(1,"性感图片内容"),
    PORN(2, "涉黄内容"),
    BLOODY(3,"血腥内容"),
    EXPLOSION(4,"爆炸烟光内容"),
    OUTFIT(5,"特殊装束内容"),
    LOGO(6, "包含LOGO内容"),
    WEAPON(7,"武器内容"),
    POLITICS(8, "涉政内容"),
    VIOLENCE(9,"打斗内容"),
    CROWD(10,"聚众内容"),
    PARADE(11,"游行内容"),
    CARCRASH(12,"车祸现场内容"),
    FLAG(13,"旗帜内容"),
    ABUSE(14, "辱骂内容"),
    LOCATION(15,"地标内容"),
    TERRORISM(16, "涉恐内容"),
    CONTRABAND(17, "违规内容"),
    SPAM(18, "垃圾内容"),
    NPX(19,"牛皮癣广告内容"),
    QRCODE(20,"包含二维码内容"),
    PROGRAMCODE(21,"包含小程序码内容"),
    AD(22,"其他广告内容"),
    MEANINGLESS(23,"无意义图片内容"),
    PIP(24,"画中画内容"),
    SMOKING(25,"吸烟内容"),
    DRIVELIVE(26,"车内直播内容"),
    TV(27, "带有管控logo的图片内容"),
    TRADEMARK(28, "商标内容"),
    OTHERS(29,"其他内容");

    private Integer code;
    private String msg;
}
