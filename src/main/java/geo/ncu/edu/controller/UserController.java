package geo.ncu.edu.controller;

import geo.ncu.edu.Service.UserService;
import geo.ncu.edu.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        Iterable<User> userList = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        Integer result = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity upadteTodo(@PathVariable Integer id, @RequestBody User user) {
        Boolean result = userService.updateUser(id ,user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.OK).body("成功更新");
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        Boolean result = userService.deleteUser(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.OK).body("成功刪除");
    }

}
