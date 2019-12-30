package hello;

import java.util.Optional;
import hello.MySQL.Entity.Product;
import hello.MySQL.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/product")
    public Page<Product> getProducts(@PageableDefault(size = 5) Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @RequestMapping("/product/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Integer id) {
        return this.productRepository.findById(id);
    }

}
