package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Brand;
import br.com.trabalhoweb.repository.BrandRepository;
import br.com.trabalhoweb.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends CrudServiceImpl<Brand, Long> implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    protected JpaRepository<Brand, Long> getRepository() {
        return brandRepository;
    }
}
