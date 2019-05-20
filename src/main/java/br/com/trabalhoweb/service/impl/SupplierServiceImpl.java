package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Supplier;
import br.com.trabalhoweb.repository.SupplierRepository;
import br.com.trabalhoweb.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends CrudServiceImpl<Supplier, Long> implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    protected JpaRepository<Supplier, Long> getRepository() {
        return supplierRepository;
    }
}
