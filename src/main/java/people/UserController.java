package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class UserController {

    /*
    Fabryka Springa szuka klasy ktora implementuje interfejs UserRepository i ktora jest Beanem;
    OK Fabryka nie znajduje takiego Beana.
    Zgadza sie?
    Tak? :slightly_smiling_face:
    Ale nie generuje błedu tylko bierze generacje klasy i tworzenie Beana na swoja klate :smile:
    w locie generuje sobie taka klase za nas!
    */

    @Autowired
    private UserRepository userRepository;

    //http://localhost:8080/demo/add?name=adam&email=adam@gmail.com.pl dodaje do bazy adama z mailem adam@...

    @GetMapping("/add")
    public String add(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        userRepository.save(user);
        return "added";
    }

    @GetMapping("/form")
    public String addedUser(){
        return "form";
    }

    @GetMapping("/usersBase")
    public String usersBase(ModelMap map){
        map.put("users",userRepository.findAll());
        return "usersBase";
    }

    @ResponseBody
    @GetMapping("/all")
    public Iterable<User> getUser() {
        return userRepository.findAll();
    }
}
