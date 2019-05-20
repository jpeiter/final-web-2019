package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.Product;
import br.com.trabalhoweb.service.BrandService;
import br.com.trabalhoweb.service.CategoryService;
import br.com.trabalhoweb.service.CrudService;
import br.com.trabalhoweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("product")
public class ProductController extends CrudController<Product, Long> {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;


    @Override
    protected CrudService<Product, Long> getService() {
        return productService;
    }

    @Override
    protected String getUrl() {
        return "product";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Product product) {
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/form");
        if (product != null) {
            modelAndView.addObject(product);
        } else {
            modelAndView.addObject(new Product());
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
    public ResponseEntity<?> save(@Valid Product entity, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Product edit(@PathVariable Long id) {
        return getService().findOne(id);
    }

    @Override
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Product> list = this.getService().findAll(PageRequest.of(currentPage - 1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/list");
        modelAndView.addObject("list", list);

        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("brands", brandService.findAll());
        modelAndView.addObject("categories", categoryService.findAll());

        if (list.getTotalPages() >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        return modelAndView;
    }
}
