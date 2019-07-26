package people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String addedUser(ModelMap map) {
        map.put("user", new User());
        return "/demo/form";
    }

    @GetMapping("/usersBase")
    public String usersBase(ModelMap map) {
        map.put("users", userRepository.findAll());
        return "usersBase";
    }

    @GetMapping("/usersBase/{id}/delete")
    public String delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return "redirect:/demo/usersBase";
    }

    @GetMapping("/usersBase/{id}/edit")
    public String edit(@PathVariable Integer id, ModelMap map) {
        map.put("user", userRepository.findById(id));
        return "edit";
    }

    @GetMapping("/usersBase/{id}")
    public String show(@PathVariable Integer id, ModelMap map) {
        map.put("user", userRepository.findById(id).get());
        return "show";
    }

    /*
    @PostMapping("/users")
    public String create(@RequestParam String name, @RequestParam String email) {
        return null;
    }

    @PostMapping("/users")
    public String create(@ModelAttribute User user) { //Robi to samo co wyżej ale krócej
        return null;
    }
    */

    @ResponseBody
    @GetMapping("/all")
    public Iterable<User> getUser() {
        return userRepository.findAll();
    }
}
