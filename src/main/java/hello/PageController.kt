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
class PageController constructor() {
    @Autowired
    private val pageRepository: PageRepository? = null

    @RequestMapping("/page")
    fun pageList(): Iterable<hello.MySQL.Entity.Page?>? {
        return this.pageRepository?.findAll()
    }

    @RequestMapping(value = ["/page"], method = [RequestMethod.POST])
    fun createPage(@RequestParam("title") title: kotlin.String?, @RequestParam("body") body: kotlin.String?): kotlin.Unit {
        val page: hello.MySQL.Entity.Page? = hello.MySQL.Entity.Page()
        page?.title = title
        page?.body = body
        this.pageRepository?.save<hello.MySQL.Entity.Page?>(page)
    }

    @RequestMapping(value = ["/page/{id}"], method = [RequestMethod.DELETE])
    fun deletePage(@PathVariable("id") id: Int?): kotlin.Unit {
        this.pageRepository?.deleteById(id)
    }
}