package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Purchase;
import br.com.trabalhoweb.repository.PurchaseRepository;
import br.com.trabalhoweb.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl extends CrudServiceImpl<Purchase, Long> implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    protected JpaRepository<Purchase, Long> getRepository() {
        return purchaseRepository;
    }
}
