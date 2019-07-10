package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
