package ru.h3f.instrumentchooser.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.h3f.instrumentchooser.model.Category;
import ru.h3f.instrumentchooser.repo.CategoryRepository;
import ru.h3f.instrumentchooser.scraper.ScrapService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Gogo implements CommandLineRunner {
    private final ScrapService scrapService;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        insertCategories();
        scrapService.scrapCards();
    }

    private void insertCategories() {
        categoryRepository.saveAll(
                List.of(
                        Category.builder()
                                .name("Винтоверты")
                                .url("https://tver.vseinstrumenti.ru/instrument/shurupoverty/vintoverty/")
                                .build()
                )
        );
    }
}
