package com.example.accenture.clients;

import com.example.accenture.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "${spring.jsonplaceholder.url}", name = "${spring.jsonplaceholder.name}")
public interface JsonPlaceHolder {

    @GetMapping("/users")
    List<User> getAllUsers();
}
