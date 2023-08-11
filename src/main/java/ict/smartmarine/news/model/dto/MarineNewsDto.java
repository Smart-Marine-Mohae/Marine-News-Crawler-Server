package ict.smartmarine.news.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarineNewsDto {
    private String link;
    private String title;
    private String content;
}
