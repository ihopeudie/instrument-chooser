package ru.h3f.instrumentchooser.repo;

import org.springframework.data.repository.CrudRepository;
import ru.h3f.instrumentchooser.model.Instrument;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
