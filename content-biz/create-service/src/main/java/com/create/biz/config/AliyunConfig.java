package com.create.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private String regionId;
    private String accessKeyId;
    private String secret;
    private List<String> labelList;
    private List<String> sceneList;
    private String callback;
    private String seed;
}
