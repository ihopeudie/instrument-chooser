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
        loadData();
        scrapService.parseWebsite();
    }

    private void loadData() {
        categoryRepository.saveAll(
                List.of(
                        Category.builder()
                                .name("Винтоверты")
                                .url("https://tver.vseinstrumenti.ru/instrument/shurupoverty/vintoverty/")
                                .build(),
                        Category.builder()
                                .name("УШМ (Болгарки)")
                                .url("https://tver.vseinstrumenti.ru/instrument/akkumulyatornyj/shlifmashiny/bolgarki-ushm/")
                                .build()
                )
        );
    }
}
