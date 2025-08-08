package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("user/get/{name}")
    public void getUserName(@PathVariable("name") String name, @PathVariable("name") String lastname) {
        if(lastname != null){
            userService.getUserByName(lastname);
        }else
        if(name != null){
            userService.getUserByName(name);
        }
    }

//    @GetMapping("user/get/{lastname}")
//    public void getUserLastname(@PathVariable("lastname") String lastname) {
//        userService.getUserByLastname(lastname);
//    }

    @PostMapping("user/create")
    public UserDTO create(@RequestBody UserDTO newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("user/put/{id}")
    public void updateUserPut(@RequestBody() UserDTO updateUser, @PathVariable("id") Long id) {
        userService.updateUserPut(updateUser, id);
    }

    @DeleteMapping("user/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PatchMapping("user/patch/{name}")
    public void updateUserPatchName(@PathVariable Long id, @RequestBody UserDTO updatePatchUserName) {
        userService.updateUserPatchName(updatePatchUserName, id);
    }

    @PatchMapping("user/patch/{lastName}")
    public void updateUserPatchLastName(@PathVariable Long id, @RequestBody UserDTO updatePatchUserLastName) {
        userService.updateUserPatchLastName(updatePatchUserLastName, id);
    }
}
