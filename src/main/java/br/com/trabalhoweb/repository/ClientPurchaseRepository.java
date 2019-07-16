package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.ClientPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientPurchaseRepository extends JpaRepository<ClientPurchase, Long> {

    List<ClientPurchase> findClientPurchaseByUserId(Long id);
}
