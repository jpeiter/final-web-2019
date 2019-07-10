package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
