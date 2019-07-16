package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.ClientPurchase;
import br.com.trabalhoweb.model.ProductClientPurchase;
import br.com.trabalhoweb.repository.ClientPurchaseRepository;
import br.com.trabalhoweb.repository.ProductClientPurchaseRepository;
import br.com.trabalhoweb.repository.UserRepository;
import br.com.trabalhoweb.service.*;
import br.com.trabalhoweb.statics.ShippingPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("buy")
public class ClientPurchaseController extends CrudController<ClientPurchase, Long> {

    @Autowired
    private ClientPurchaseService clientPurchaseService;

    @Autowired
    private ClientPurchaseRepository clientPurchaseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductClientPurchaseService productClientPurchaseService;

    @Autowired
    private ProductClientPurchaseRepository productClientPurchaseRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected CrudService<ClientPurchase, Long> getService() {
        return clientPurchaseService;
    }

    @Override
    protected String getUrl() {
        return "buy";
    }

    @Override
    protected ModelAndView form(ClientPurchase entity) {
        return null;
    }

    @Override
    protected ModelAndView form(Long aLong) {
        return null;
    }

    @GetMapping("cart")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/cart");
        return modelAndView;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody @Valid ClientPurchase entity, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        entity.setDate(LocalDate.now());
        entity.setUser(userRepository.findByUsername(principal.getName()));
        entity.setCountry(countryService.findOne(entity.getCountry().getId()));
        entity.setShippingPrice(
                entity.getCountry().getCode().equals("BRA") ? new BigDecimal(ShippingPrices.BRAZIL) : new BigDecimal(ShippingPrices.OTHERS)
        );
        getService().save(entity);

        for (ProductClientPurchase pcp : entity.getProductClientPurchases()) {
            pcp.setProduct(productService.findOne(pcp.getProduct().getId()));
            pcp.setQuantity(pcp.getQuantity());
            pcp.setTotalPrice(pcp.getQuantity().multiply(pcp.getProduct().getPrice()));
            pcp.setClientPurchase(entity);

            productClientPurchaseService.save(pcp);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("history")
    public ModelAndView history(Principal principal) {
        List<ClientPurchase> history =
                clientPurchaseRepository.findClientPurchaseByUserId(
                        userRepository.findByUsername(principal.getName()).getId()
                );

        List<ProductClientPurchase> pcps =
                productClientPurchaseRepository
                        .findProductClientPurchaseByClientPurchaseUserId(
                                userRepository.findByUsername(principal.getName()).getId()
                        );

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (ProductClientPurchase pcp  : pcps) {
            totalAmount = totalAmount.add(pcp.getTotalPrice());
        }

        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/history");
        modelAndView.addObject("history", history);
        modelAndView.addObject("totalAmount", totalAmount);
        return modelAndView;
    }

}
