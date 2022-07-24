package ru.h3f.instrumentchooser.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.h3f.instrumentchooser.repo.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final CategoryRepository categoryRepository;
    private final CategoryScraper categoryScraper;

    public void scrapCards() {
        var cardUrls = categoryRepository.findAll()
                .stream()
                .map(categoryScraper::getCardUrls)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        log.info("parsed card urls: {}", cardUrls);
    }
}
