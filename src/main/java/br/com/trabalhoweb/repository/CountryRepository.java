package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
