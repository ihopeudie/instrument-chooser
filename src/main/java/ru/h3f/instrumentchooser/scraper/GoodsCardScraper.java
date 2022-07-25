package ru.h3f.instrumentchooser.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.h3f.instrumentchooser.model.Instrument;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoodsCardScraper {
    public Instrument parseCard(String url) {
        //TODO
        return Instrument.builder().build();
    }
}
