package hello.MySQL.Repository

import org.springframework.data.repository.CrudRepository

open interface PageRepository : CrudRepository<hello.MySQL.Entity.Page?, Int?> {}