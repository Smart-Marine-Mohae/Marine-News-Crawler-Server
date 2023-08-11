package ict.smartmarine.news.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MarineNewsResponseDto {
    private String category;
    private String subsection;
    private String subSectionCode;
    private String url;
    private List<MarineNewsDto> marineNews;
}
