package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
