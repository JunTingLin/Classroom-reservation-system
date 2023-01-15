package geo.ncu.edu.controller;

import geo.ncu.edu.Service.UserService;
import geo.ncu.edu.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody User user){
        User result = userService.save(user);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }
}
