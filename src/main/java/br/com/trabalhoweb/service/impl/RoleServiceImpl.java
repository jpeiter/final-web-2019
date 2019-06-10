package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Role;
import br.com.trabalhoweb.repository.RoleRepository;
import br.com.trabalhoweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role, Long> implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    protected JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }
}
