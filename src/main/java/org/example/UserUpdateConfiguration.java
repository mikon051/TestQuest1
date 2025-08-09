package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.BiConsumer;

@Configuration
public class UserUpdateConfiguration {
    @Bean
    public BiConsumer<UserEntity, Map<String,?>> name(){
        return (entity,updates) -> {
            entity.setName(String.valueOf(updates.get("name")));
        };
    }

    @Bean
    public BiConsumer<UserEntity,Map<String,?>> lastname(){
        return (entity,updates) -> {
            entity.setLastname(String.valueOf(updates.get("lastname")));
        };
    }
}
