package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.User;
import br.com.trabalhoweb.service.CrudService;
import br.com.trabalhoweb.service.RoleService;
import br.com.trabalhoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("user")
public class UserController extends CrudController<User, Long> {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private RoleService roleService;

    @Override
    protected CrudService<User, Long> getService() {
        return this.usuarioService;
    }

    @Override
    protected String getUrl() {
        return "user";
    }

    @Override
    protected ModelAndView form(User entity) {
        ModelAndView modelAndView = new ModelAndView((getUrl() + "/form"));
        if (entity == null) {
            modelAndView.addObject("user", new User());
        } else {
            modelAndView.addObject("user", entity);
        }

        return modelAndView;
    }

    @Override
    protected ModelAndView form(Long id) {
        return null;
    }


    @GetMapping("ajax/{id}")
    @ResponseBody
    public User edit(@PathVariable Long id) {
        return getService().findOne(id);
    }


    @PostMapping("ajax")
    public ResponseEntity<?> saveAjax(@Valid User entity, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        if (!usuarioService.findOne(entity.getId()).getPassword().equals(entity.getPassword())
                || entity.getPassword() != null) {
            entity.setPassword(
                    entity.getEncodedPassword(
                            entity.getPassword()
                    )
            );
        }

        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> list = this.getService().findAll(
                PageRequest.of(currentPage - 1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/list");
        modelAndView.addObject("list", list);
        modelAndView.addObject("roles", roleService.findAll());

        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }
}
