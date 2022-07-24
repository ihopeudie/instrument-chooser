package ru.h3f.instrumentchooser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.h3f.instrumentchooser.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
