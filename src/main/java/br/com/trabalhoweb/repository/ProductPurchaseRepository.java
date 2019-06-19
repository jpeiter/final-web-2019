package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ProductPurchase;
import br.com.trabalhoweb.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {

    List<ProductPurchase> findByPurchase(Purchase p);
}
