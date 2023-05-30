package com.nhnacademy.board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@PropertySource("classpath:common.properties")
public class CommonPropertiesConfig {
    @Value("#{'${login.excludeUrls}'.split(',')}")
    private Set<String> excludeUrls;

    @Value("${upload.path}")
    public String uploadPath;

    public Set<String> getExcludeUrls() {
        return excludeUrls;
    }

    public String getUploadPath() {
        return uploadPath;
    }
}
