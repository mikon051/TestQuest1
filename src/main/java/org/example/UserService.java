package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO newUser) {
        System.out.println("Юзер создан " + newUser.getName() + " " + newUser.getLastname());
        UserEntity user = new UserEntity();
        user.setName(newUser.getName());
        user.setLastname(newUser.getLastname());
        userRepository.save(user);
        return newUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUserPut(UserDTO updateUser, Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Юзер не найден"));
        if (updateUser.getName() != null) {
            user.setName(updateUser.getName());
        }
        if (updateUser.getLastname() != null) {
            user.setLastname(updateUser.getLastname());
        }
        userRepository.save(user);
    }

    public void updateUserPatchName(UserDTO updateUser, Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Юзер не найден"));
        if (updateUser.getName() != null) {
            user.setName(updateUser.getName());
        }
        userRepository.save(user);
    }

    public void updateUserPatchLastName(UserDTO updateUser, Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Юзер не найден"));
        if (updateUser.getLastname() != null) {
            user.setLastname(updateUser.getLastname());
        }
        userRepository.save(user);
    }

    public UserDTO getUserByName(String name) {
        System.out.println(userRepository.findByName(name));
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException("Юзер не найден"));
    }
//
//    public UserDTO getUserByLastname(String lastname) {
//        System.out.println(userRepository.findByLastname(lastname));
//        return userRepository.findByLastname(lastname).orElseThrow(() -> new RuntimeException("Юзер не найден"));
//    }
}
