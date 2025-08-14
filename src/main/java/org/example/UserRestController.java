package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

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


    @PostMapping("/{id}/photo")
    public UserEntity uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            return userService.uploadPhoto(id, file);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload photo", e);
        }
    }


    @PutMapping("/{id}/photo")
    public UserEntity updatePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            return userService.updatePhoto(id, file);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update photo", e);
        }
    }

}
