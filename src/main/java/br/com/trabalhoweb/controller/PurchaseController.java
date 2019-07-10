package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.ProductPurchase;
import br.com.trabalhoweb.model.Purchase;
import br.com.trabalhoweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("purchase")
public class PurchaseController extends CrudController<Purchase, Long> {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPurchaseService productPurchaseService;

    @Autowired
    private UserService userService;


    @Override
    protected CrudService<Purchase, Long> getService() {
        return purchaseService;
    }

    @Override
    protected String getUrl() {
        return "purchase";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Purchase purchase) {
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/new");
        modelAndView.addObject("suppliers", supplierService.findAll());
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("users", userService.findAll());
        if (purchase != null) {
            modelAndView.addObject(purchase);
        } else {
            modelAndView.addObject(new Purchase());
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

    @Override
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Purchase> list = this.getService().findAll(PageRequest.of(currentPage - 1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/list");
        modelAndView.addObject("list", list);

        modelAndView.addObject("purchases", purchaseService.findAll());
        modelAndView.addObject("suppliers", supplierService.findAll());
        modelAndView.addObject("products", productService.findAll());
        modelAndView.addObject("users", userService.findAll());

        if (list.getTotalPages() >= 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        return modelAndView;
    }

    @PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody @Valid Purchase entity, BindingResult result, Model model,
                                      RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        entity.setSupplier(supplierService.findOne(entity.getSupplier().getId()));
        entity.setUser(userService.findOne(entity.getUser().getId()));

        getService().save(entity);

        for (ProductPurchase pp : entity.getProductsPurchase()) {
            pp.setProduct(productService.findOne(pp.getProduct().getId()));
            BigDecimal totalValue = pp.getQuantity().multiply(pp.getProduct().getPrice());
            pp.setTotalPrice(totalValue);
            pp.setPurchase(entity);

            productPurchaseService.save(pp);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
