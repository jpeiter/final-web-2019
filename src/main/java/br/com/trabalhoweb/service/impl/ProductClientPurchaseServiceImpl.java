package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.ProductClientPurchase;
import br.com.trabalhoweb.repository.ProductClientPurchaseRepository;
import br.com.trabalhoweb.service.ProductClientPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductClientPurchaseServiceImpl extends CrudServiceImpl<ProductClientPurchase, Long> implements ProductClientPurchaseService {

    @Autowired
    private ProductClientPurchaseRepository productClientPurchaseRepository;

    @Override
    protected JpaRepository<ProductClientPurchase, Long> getRepository() {
        return productClientPurchaseRepository;
    }
}
