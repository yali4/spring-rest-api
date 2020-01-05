package hello;

import java.util.List;
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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RestController
public class ProductController {

    @Autowired
    EntityManager entityManager;

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

    @RequestMapping("/product/category/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable("categoryId") Integer categoryId) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        criteriaQuery.select(productRoot).where(
            criteriaBuilder.equal(productRoot.get("category"), categoryId)
        );

        TypedQuery<Product> customQuery = this.entityManager.createQuery(criteriaQuery);

        return customQuery.getResultList();

    }

}
