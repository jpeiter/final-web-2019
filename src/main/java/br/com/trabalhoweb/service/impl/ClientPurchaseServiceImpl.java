package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.ClientPurchase;
import br.com.trabalhoweb.repository.ClientPurchaseRepository;
import br.com.trabalhoweb.service.ClientPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientPurchaseServiceImpl extends CrudServiceImpl<ClientPurchase, Long> implements ClientPurchaseService {

    @Autowired
    private ClientPurchaseRepository clientPurchaseRepository;

    @Override
    protected JpaRepository<ClientPurchase, Long> getRepository() {
        return clientPurchaseRepository;
    }
}
