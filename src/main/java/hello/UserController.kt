package hello

import hello.Builder.UserBuilder
import hello.Repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class UserController {
    var users: MutableList<User> = ArrayList()
    @get:RequestMapping("/user")
    val allUser: List<User>
        get() = users

    @RequestMapping(value = ["/user"], method = [RequestMethod.POST])
    fun addSingleUser(@RequestParam(value = "name") name: String?, @RequestParam(value = "age") age: Int) {
        val userCount = users.size
        val user = UserBuilder().id(userCount + 1).name(name).age(age).build()
        users.add(user)
    }

    @RequestMapping("/user/{id}")
    fun getSingleUser(@PathVariable(value = "id") id: Int): User {
        var result: User? = null
        for (currentUser in users) {
            if (currentUser.id == id) {
                result = currentUser
                break
            }
        }
        if (result == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        }
        return result
    }

    @RequestMapping(value = ["/user/{id}"], method = [RequestMethod.DELETE])
    fun removeSingleUser(@PathVariable(value = "id") id: Int) {
        for (currentUser in users) {
            if (currentUser.id == id) {
                users.remove(currentUser)
                break
            }
        }
    }

    @RequestMapping("/user/search")
    fun searchUser(@RequestParam(value = "query") query: String): List<User> {
        if (query.isBlank() || query.isEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Search Query Can Not be Empty!")
        }
        val result: MutableList<User> = ArrayList()
        for (currentUser in users) {
            if (currentUser.name!!.contains(query)) {
                result.add(currentUser)
            }
        }
        return result
    }

    init {
        val userRepository = UserRepository()
        users = userRepository.users
    }
}