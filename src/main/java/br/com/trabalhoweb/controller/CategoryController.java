package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.Category;
import br.com.trabalhoweb.service.CategoryService;
import br.com.trabalhoweb.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

//    @GetMapping("")
//    public String index(ModelAndView modelAndView) {
//        return "redirect:/" + getUrl() + "/page";
//    }

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
}
