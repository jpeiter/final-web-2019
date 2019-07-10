package br.com.trabalhoweb.controller;

import br.com.trabalhoweb.model.Product;
import br.com.trabalhoweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private CountryService countryService;

    @Autowired
    private EntityManager em;


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
        ModelAndView modelAndView = new ModelAndView(this.getUrl() + "/product");
        modelAndView.addObject("product", this.getService().findOne(id));
        modelAndView.addObject("countries", countryService.findAll());

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

    @GetMapping("{prodId}/images")
    public ResponseEntity<?> getProductImages(HttpServletRequest request,
                                              @PathVariable Long prodId) {

        File dir = new File("C:\\trabalhoweb\\images\\");
        List<File> files = Arrays.asList(dir.listFiles());

        List<byte[]> images = new ArrayList<>();

        for (File file : files) {
            if (file.getName().subSequence(0, prodId.toString().length()).equals(prodId.toString())) {
                try {
                    images.add(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ResponseEntity<>(images, HttpStatus.OK);
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

    @PostMapping("upload")
    public ResponseEntity<?> save(@Valid Product entity, BindingResult result,
                                  @RequestParam("attachments") MultipartFile[] anexos,
                                  HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);

        if (anexos.length > 0 && !anexos[0].getOriginalFilename().isEmpty()) {
            saveFile(entity.getId(), anexos, request);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveFile(Long id, MultipartFile[] anexos, HttpServletRequest request) {
        File dir = new File("C:\\trabalhoweb\\images\\");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String caminhoAnexo = "C:\\trabalhoweb\\images\\";


        int i = 0;
        for (MultipartFile anexo : anexos) {
            i++;
            String extensao = anexo
                    .getOriginalFilename()
                    .substring(
                            anexo.getOriginalFilename().lastIndexOf("."),
                            anexo.getOriginalFilename().length()
                    );
            String nomeArquivo = id + "_" + i + extensao;

            try {
                FileOutputStream fileOut = new FileOutputStream(
                        new File(caminhoAnexo + nomeArquivo)
                );
                BufferedOutputStream stream = new BufferedOutputStream(fileOut);
                stream.write(anexo.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
