package ru.h3f.instrumentchooser.scraper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.h3f.instrumentchooser.model.Category;

import java.io.IOException;
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

    public List<String> getCardUrls(Category category) {
        try {
            Document document = Jsoup.connect(category.getUrl()).get();
            var listingContainer = document.getElementsByClass(listingClass).get(0);
            var cards = listingContainer.getElementsByClass(cardClass);
            return cards
                    .stream()
                    .map(this::parseUrl)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error scraping main page: {}", e.getMessage());
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
