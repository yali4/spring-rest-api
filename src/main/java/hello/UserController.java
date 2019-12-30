package hello;

import java.util.List;
import java.util.ArrayList;

import hello.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    List<User> users = new ArrayList<User>();

    public UserController() {
        UserRepository userRepository = new UserRepository();
        this.users = userRepository.getUsers();
    }

    @RequestMapping("/user")
    public List<User> getAllUser() {
        return users;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addSingleUser(@RequestParam(value="name") String name, @RequestParam(value = "age") int age ) {
        int userCount = this.users.size();
        User user = new User(userCount + 1, name, age);
        this.users.add(user);
    }

    @RequestMapping("/user/{id}")
    public User getSingleUser(@PathVariable(value="id") int id) {
        User result = null;
        for(User currentUser: this.users) {
            if (currentUser.getId() == id) {
                result = currentUser;
                break;
            }
        }

        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }

        return result;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void removeSingleUser(@PathVariable(value="id") int id) {
        for(User currentUser: this.users) {
            if (currentUser.getId() == id) {
                this.users.remove(currentUser);
                break;
            }
        }
    }

    @RequestMapping("/user/search")
    public List<User> searchUser(@RequestParam(value = "query") String query) {
        if (query.isBlank() || query.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search Query Can Not be Empty!");
        }
        List<User> result = new ArrayList<User>();
        for(User currentUser: this.users) {
            if (currentUser.getName().contains(query)) {
                result.add(currentUser);
            }
        }
        return result;
    }

}