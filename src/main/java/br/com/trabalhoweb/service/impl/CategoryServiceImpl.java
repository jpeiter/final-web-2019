package br.com.trabalhoweb.service.impl;

import br.com.trabalhoweb.model.Category;
import br.com.trabalhoweb.repository.CategoryRepository;
import br.com.trabalhoweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category, Long> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
