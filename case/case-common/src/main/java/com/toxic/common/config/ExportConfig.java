package com.toxic.common.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 *
 * @author py
 * @date 2018/7/23 下午3:55.
 */
@Component
@ConfigurationProperties(locations = "classpath:config/exportconfig.yml",prefix = "export")
public class ExportConfig {
    private  String pageSize;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
