package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
public class UserService {
    @Autowired
    private Map<String, BiConsumer<UserEntity, Map<String, ?>>> updateProcessors;
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

    public UserDTO getUserByLastname(String lastname) {
        System.out.println(userRepository.findByLastname(lastname));
        return userRepository.findByLastname(lastname).orElseThrow(() -> new RuntimeException("Юзер не найден"));
    }


    public UserDTO patch(Long id, Map<String, ?> updates) {
        return userRepository.findById(id)
                .map((user) -> {
                    updates.keySet()
                            .forEach((key) -> updateProcessors.get(key).accept(user, updates));
                    return user;
                }).map(userRepository::save).map(UserDTO::of)
                .orElseThrow(() -> new RuntimeException("Сущность не найдена"));
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    // Методы для работы с фотографиями
    public UserEntity uploadPhoto(Long userId, MultipartFile file) throws IOException {
        UserEntity user = getUserById(userId);
        user.setPhoto(file.getBytes());
        user.setPhotoFileName(file.getOriginalFilename());
        user.setPhotoContentType(file.getContentType());
        return userRepository.save(user);
    }

    public UserEntity updatePhoto(Long employeeId, MultipartFile file) throws IOException {
        UserEntity user = getUserById(employeeId);
        user.setPhoto(file.getBytes());
        user.setPhotoFileName(file.getOriginalFilename());
        user.setPhotoContentType(file.getContentType());
        return userRepository.save(user);
    }

    public void deletePhoto(Long employeeId) {
        UserEntity employee = getUserById(employeeId);
        employee.setPhoto(null);
        employee.setPhotoFileName(null);
        employee.setPhotoContentType(null);
        userRepository.save(employee);
    }

}
