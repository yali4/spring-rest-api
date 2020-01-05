package hello.Builder;

import hello.User;

public class UserBuilder {

    User user;

    public UserBuilder() {
        this.user = new User();
    }

    public User build() {
        return this.user;
    }

    public UserBuilder id(Integer id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder name(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder age(Integer age) {
        this.user.setAge(age);
        return this;
    }
}
