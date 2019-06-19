package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.ProductPurchase;
import br.com.trabalhoweb.model.Purchase;
import br.com.trabalhoweb.repository.ProductPurchaseRepository;
import br.com.trabalhoweb.service.ProductPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPurchaseServiceImpl extends CrudServiceImpl<ProductPurchase, Long> implements ProductPurchaseService {

    @Autowired
    private ProductPurchaseRepository productPurchaseRepository;

    @Override
    protected JpaRepository<ProductPurchase, Long> getRepository() {
        return productPurchaseRepository;
    }

    public List<ProductPurchase> findByPurchase(Purchase p){
        return productPurchaseRepository.findByPurchase(p);
    }
}
