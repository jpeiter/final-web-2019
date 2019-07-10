package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
