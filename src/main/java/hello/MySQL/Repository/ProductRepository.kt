package hello.MySQL.Repository

import hello.MySQL.Entity.Product
import org.springframework.data.repository.PagingAndSortingRepository

open interface ProductRepository : PagingAndSortingRepository<Product?, Int?> {}