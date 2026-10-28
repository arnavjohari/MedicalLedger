package com.example.medical_ledger.Controller;

import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUser(@PathVariable Long id){
      return  userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody User user ,
                       @PathVariable  Long id  ){
        userService.updateUser(user , id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    }
}
