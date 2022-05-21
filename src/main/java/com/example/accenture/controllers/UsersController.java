package com.example.accenture.controllers;

import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/" )
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
    }

    @GetMapping("/albums/{userId}")
    public ResponseEntity<List<PhotoDto>> getAllUsersAlbums(@PathVariable("userId") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUserAlbums(userId));
    }
}
