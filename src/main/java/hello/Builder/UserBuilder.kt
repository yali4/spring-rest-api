package hello.Builder

import hello.User

class UserBuilder {
    var user: User
    fun build(): User {
        return user
    }

    fun id(id: Int?): UserBuilder {
        user.id = id
        return this
    }

    fun name(name: String?): UserBuilder {
        user.name = name
        return this
    }

    fun age(age: Int?): UserBuilder {
        user.age = age
        return this
    }

    init {
        user = User()
    }
}