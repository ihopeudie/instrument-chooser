package ru.h3f.instrumentchooser.scraper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.h3f.instrumentchooser.model.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryScraper {

    @Value("${vi.listing-class}")
    private String listingClass;
    @Value("${vi.card-class}")
    private String cardClass;
    @Value("${vi.card-url-container-class}")
    private String urlContainerClass;
    @Value("${parser.max-pages}")
    private int maxPages;

    public List<String> getCardUrls(Category category) {
        log.info("trying to parse category: {}", category);
        List<String> result = new ArrayList<>();
        var url = category.getUrl();
        try {
            int i = 2;
            do {
                log.info("trying to parse category from url: {}", url);
                Document document = Jsoup.connect(url).get();
                var listingContainer = document.getElementsByClass(listingClass).get(0);
                var cards = listingContainer.getElementsByClass(cardClass);
                result.addAll(cards
                        .stream()
                        .map(this::parseUrl)
                        .collect(Collectors.toList()));
                url = category.getUrl()+"page"+i+"/";
                i++;
            } while (i < maxPages);
            return result;
        } catch (HttpStatusException e) {
            log.info("404 on url: {}", url);
            return result;
        }
        catch (IOException e) {
            log.error("Error category page: {}", e.getMessage());
            return List.of();
        }
    }

    private String parseUrl(Element card) {
        return parseCategoryLink(card).attr("href");
    }

    private Element parseCategoryLink(Element card) {
        var titleDiv = card.getElementsByClass(urlContainerClass).get(0);
        return titleDiv.getElementsByTag("a").get(0);
    }
}
