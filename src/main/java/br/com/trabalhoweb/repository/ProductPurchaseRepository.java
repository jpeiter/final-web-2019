package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
}
