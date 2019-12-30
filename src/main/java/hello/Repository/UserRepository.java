package hello.Repository;

import hello.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    List<User> users = new ArrayList<User>();

    public UserRepository(){
        this.users.add(new User(1, "Yalçın Ceylan", 27));
        this.users.add(new User(2, "Babak Partovi", 37));
        this.users.add(new User(3, "Buğra Aslan", 23));
    }

    public List<User> getUsers() {
        return users;
    }
}
