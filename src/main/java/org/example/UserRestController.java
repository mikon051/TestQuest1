package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public UserDTO getUserName(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "lastname", required = false) String lastname) {
        if (lastname != null) {
            return userService.getUserByLastname(lastname);
        }
        if (name != null) {
            return userService.getUserByName(name);
        }
        return null;
    }

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


    @PatchMapping("/user/{id}")
    public UserDTO patch(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates){
        return userService.patch(id, updates);
    }
}
