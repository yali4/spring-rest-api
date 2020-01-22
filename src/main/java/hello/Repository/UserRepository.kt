package hello.Repository

import hello.Builder.UserBuilder
import hello.User
import java.util.*

class UserRepository {
    var users: MutableList<User> = ArrayList()
    init {
        users.add(UserBuilder().id(1).name("Yalçın Ceylan").age(27).build())
        users.add(UserBuilder().id(2).name("Babak Partovi").age(37).build())
        users.add(UserBuilder().id(3).name("Buğra Aslan").age(23).build())
    }
}