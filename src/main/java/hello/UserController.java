package hello;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UserController {

    List<User> userList = new ArrayList<User>();
    List<Address> addressList = new ArrayList<Address>();

    public UserController() {
        this.userList.add(new User(1, "Yalçın Ceylan", 27));
        this.userList.add(new User(2, "Babak Partovi", 37));
        this.userList.add(new User(3, "Buğra Aslan", 23));

        this.addressList.add(new Address(1, "Yalçın Adres", "İnönü Mh. Kainat Cd."));
        this.addressList.add(new Address(2, "Babak Adres", "Çeliktepe Mh. Eyalet Sk."));
    }

    @RequestMapping("/user")
    public List<User> getAllUser() {
        return userList;
    }

    @RequestMapping("/user/{id}")
    public User getSingleUser(@PathVariable(value="id") int id) {
        User resultUser = null;
        for(User currentUser: this.userList) {
            if (currentUser.getId() == id) {
                resultUser = currentUser;
                break;
            }
        }
        return resultUser;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void removeSingleUser(@PathVariable(value="id") int id) {
        for(User currentUser: this.userList) {
            if (currentUser.getId() == id) {
                this.userList.remove(currentUser);
                break;
            }
        }
    }

    @RequestMapping("/user/search")
    public List<User> searchUser(@RequestParam(value = "query") String query) {
        List<User> result = new ArrayList<User>();
        for(User currentUser: this.userList) {
            if (currentUser.getName().contains(query)) {
                result.add(currentUser);
            }
        }
        return result;
    }

}