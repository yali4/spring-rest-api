package hello.Repository;

import hello.Builder.UserBuilder;
import hello.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    List<User> users = new ArrayList<User>();

    public UserRepository(){
        this.users.add(new UserBuilder().id(1).name("Yalçın Ceylan").age(27).build());
        this.users.add(new UserBuilder().id(2).name("Babak Partovi").age(37).build());
        this.users.add(new UserBuilder().id(3).name("Buğra Aslan").age(23).build());
    }

    public List<User> getUsers() {
        return users;
    }
}
