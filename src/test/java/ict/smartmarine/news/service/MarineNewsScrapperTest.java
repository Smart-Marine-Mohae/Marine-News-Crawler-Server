package ict.smartmarine.news.service;

import ict.smartmarine.news.model.MarineSubsectionCode;
import ict.smartmarine.news.model.dto.MarineNewsDto;
import ict.smartmarine.news.service.crawler.MarineNewsCrawlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MarineNewsScrapperTest {
    @Autowired
    private MarineNewsCrawlService marineNewsCrawlService;

    @Test
    @DisplayName("올바르지 않은 Subsection Code를 입력 받았을 때 Runtime Exception이 발생한다.")
    void crawl_fail_with_unappropriated_code() {
        Optional<List<MarineNewsDto>> result = marineNewsCrawlService.crawl("S2N2111");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("올바른 Subsection Code를 입력 받았을 때 정상적으로 crawling을 진행한다.")
    void crawl_fail_with_appropriate_code() {
        Optional<List<MarineNewsDto>> crawl = marineNewsCrawlService.crawl(MarineSubsectionCode.HOME_WATER.getCode());
        assertThat(crawl).isPresent();
    }
}