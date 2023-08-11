package ict.smartmarine.news.controller;

import ict.smartmarine.news.model.dto.MarineNewsDto;
import ict.smartmarine.news.service.crawler.MarineNewsCrawlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marine-news")
@RequiredArgsConstructor
public class MarineNewsController {
    private final MarineNewsCrawlService marineNewsCrawlService;

    @GetMapping("/crawl")
    public ResponseEntity<Object> getNews(@RequestParam String code) {
        Optional<List<MarineNewsDto>> result = marineNewsCrawlService.crawl(code);

        return result.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
