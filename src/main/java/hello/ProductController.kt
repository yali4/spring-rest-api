package hello

import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import hello.MySQL.Repository.PageRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import javax.persistence.EntityManager
import hello.MySQL.Repository.ProductRepository
import org.springframework.data.web.PageableDefault
import hello.MySQL.Entity.Product
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Root
import javax.persistence.TypedQuery
import hello.Service.Model.ExchangeRate
import hello.Service.ExchangeRateService
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.CrudRepository

@RestController
class ProductController constructor() {
    @Autowired
    var entityManager: EntityManager? = null
    @Autowired
    var productRepository: ProductRepository? = null

    @RequestMapping("/product")
    fun getProducts(@PageableDefault(size = 5) pageable: org.springframework.data.domain.Pageable?): org.springframework.data.domain.Page<Product?>? {
        return this.productRepository?.findAll(pageable)
    }

    @RequestMapping("/product/{id}")
    fun getProduct(@PathVariable("id") id: Int?): java.util.Optional<Product?>? {
        return this.productRepository?.findById(id)
    }

    @RequestMapping("/product/category/{categoryId}")
    fun getProductsByCategoryId(@PathVariable("categoryId") categoryId: Int?): kotlin.collections.MutableList<Product?>? {
        val criteriaBuilder: CriteriaBuilder? = this.entityManager?.getCriteriaBuilder()
        val criteriaQuery: javax.persistence.criteria.CriteriaQuery<Product?>? = criteriaBuilder?.createQuery(Product::class.java)
        val productRoot: Root<Product?>? = criteriaQuery?.from(Product::class.java)
        criteriaQuery?.select(productRoot)?.where(
                criteriaBuilder?.equal(productRoot?.get<kotlin.Any?>("category"), categoryId)
        )
        val customQuery: TypedQuery<Product?>? = this.entityManager?.createQuery<Product?>(criteriaQuery)
        return customQuery?.getResultList()
    }
}