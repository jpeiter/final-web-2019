package br.com.trabalhoweb.repository;

import br.com.trabalhoweb.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
