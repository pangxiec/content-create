package com.create.pojo.domain.audit.textaudit;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @author xmy
 * @date 2021/2/6 9:40
 */
@Data
public class Detail {

    private String label;
    private JSONArray contexts;

}
