package ict.smartmarine.news.service.crawler;

import ict.smartmarine.news.config.MarineNewsConfig;
import ict.smartmarine.news.model.MarineSubsectionCode;
import ict.smartmarine.news.model.dto.MarineNewsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarineNewsCrawlService {
    private final MarineNewsConfig marineNewsConfig;

    private String baseUrl;
    private String url;

    private final List<MarineNewsDto> marineNewsResult = new ArrayList<>();
    private final List<String> hrefList = new ArrayList<>();
    private final List<String> titleList = new ArrayList<>();
    private final List<List<String>> contentList = new ArrayList<>();


    public boolean validateCode(String code) {
        Optional<MarineSubsectionCode> marineSubsectionCode = Arrays
                .stream(MarineSubsectionCode.values())
                .filter(c -> c.getCode().equals(code))
                .findAny();

        return marineSubsectionCode.isPresent();
    }

    public Optional<List<MarineNewsDto>> crawl(String code) {
        if (!validateCode(code)){
            return Optional.empty();
        }

        initUrl(code);

        try {
            getContents();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        // Combine titles, links, and contents
        for (int i = 0; i < contentList.size()-1; i++) {
            MarineNewsDto responseDto = MarineNewsDto.builder()
                    .title(titleList.get(i))
                    .link(baseUrl + hrefList.get(i))
                    .content(String.valueOf(contentList.get(i)))
                    .build();
            marineNewsResult.add(responseDto);
        }

        return Optional.of(marineNewsResult);
    }


    private void initUrl(String code){

        baseUrl = marineNewsConfig.getBaseUrl();
        url = UriComponentsBuilder.fromUriString(marineNewsConfig.getUrl())
                .queryParam("sc_sub_section_code", code)
                .queryParam("view_type", marineNewsConfig.getViewType())
                .toUriString();
    }

    private void getContents() throws IOException {
        Document document = Jsoup.connect(url).get();

        // Titles
        Elements titleElements = document.select("h4.titles");
        for (Element title : titleElements) {
            Element link = title.selectFirst("a");

            // Hrefs
            String href = Objects.requireNonNull(link).attr("href");
            // Titles
            String titleContent = link.text().trim();

            // Article Contents
            Document innerDoc;
            try {
                innerDoc = Jsoup.connect(baseUrl + href).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Element contentDiv = innerDoc.getElementById("article-view-content-div");
            if (contentDiv == null) {
                break;
            }

            String contentDivText = contentDiv.text().trim();
            Elements paragraphs = Objects.requireNonNull(contentDiv).select("p");

            List<String> content = new ArrayList<>();
            paragraphs.forEach(p -> content.add(p.text().trim()));

            if (content.isEmpty()) contentList.add(Collections.singletonList(contentDivText));
            else contentList.add(content.subList(0, 3));

            hrefList.add(href);
            titleList.add(titleContent);
        }
    }
}
