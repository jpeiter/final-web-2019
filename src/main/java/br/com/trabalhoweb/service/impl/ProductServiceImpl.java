package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Product;
import br.com.trabalhoweb.repository.ProductRepository;
import br.com.trabalhoweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CrudServiceImpl<Product, Long> implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }
}
