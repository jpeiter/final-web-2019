package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ProductClientPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductClientPurchaseRepository extends JpaRepository<ProductClientPurchase, Long> {

    List<ProductClientPurchase> findProductClientPurchaseByClientPurchaseUserId(Long id);
}
