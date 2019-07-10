package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ProductClientPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductClientPurchaseRepository extends JpaRepository<ProductClientPurchase, Long> {
}
