package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.Category;
import br.com.trabalhoweb.service.CategoryService;
import br.com.trabalhoweb.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController extends CrudController<Category, Long> {

    @Autowired
    private CategoryService categoryService;

    @Override
    protected CrudService<Category, Long> getService() {
        return categoryService;
    }

    @Override
    protected String getUrl() {
        return "category";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Category category) {
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/form");
        if (category != null) {
            modelAndView.addObject(category);
        } else {
            modelAndView.addObject(new Category());
        }
        return modelAndView;
    }

    @Override
    @GetMapping("{id}")
    protected ModelAndView form(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/form");
        modelAndView.addObject(this.getService().findOne(id));

        return modelAndView;
    }

    @PostMapping("ajax")
    public ResponseEntity<?> save(@Valid Category entity, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Category edit(@PathVariable Long id) {
        return getService().findOne(id);
    }
}
