package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ClientPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPurchaseRepository extends JpaRepository<ClientPurchase, Long> {
}
