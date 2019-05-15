package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Country;
import br.com.trabalhoweb.repository.CountryRepository;
import br.com.trabalhoweb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends CrudServiceImpl<Country, Long> implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    protected JpaRepository<Country, Long> getRepository() {
        return countryRepository;
    }
}
