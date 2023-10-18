package com.AgriGest.AgriGestApp.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AgriGest.AgriGestApp.Dao.Interfaces.UserDao;
import com.AgriGest.AgriGestApp.Models.User;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "api/User")
    public List<User> GetUsers (){
        return userDao.GetUser();
    }

    @PostMapping("api/User")
    public ResponseEntity<String> PostUser(@RequestBody User user){
        return userDao.postUser(user);
    }

    @DeleteMapping(value = "api/User/{Id}")
    public ResponseEntity<String> DeleteUserbyId(@PathVariable String Id){
        return userDao.deleteUserbyId(Id);
    }

    @DeleteMapping(value = "api/user/{name}/{email}")
    public ResponseEntity<String> deleteUserbyName(@PathVariable("name") String Name,@PathVariable("email") String Email){
        return userDao.deleteUserbyName(Name, Email);
    }


}

