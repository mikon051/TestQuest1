package org.example;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserDTO> findByName(String name);
    Optional<UserDTO> findByLastname(String lastname);
    }
