package ict.smartmarine.news.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "news")
@Data
public class MarineNewsConfig {
    private String url;
    private String baseUrl;
    private List<String> code;
    private String viewType;
}
