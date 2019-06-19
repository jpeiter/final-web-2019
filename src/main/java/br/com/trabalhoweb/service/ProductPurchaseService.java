package br.com.trabalhoweb.service;

import br.com.trabalhoweb.model.ProductPurchase;
import br.com.trabalhoweb.model.Purchase;

import java.util.List;

public interface ProductPurchaseService extends CrudService<ProductPurchase, Long> {

    List<ProductPurchase> findByPurchase(Purchase p);
}
