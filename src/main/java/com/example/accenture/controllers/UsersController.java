package com.example.accenture.controllers;

import com.example.accenture.domain.Comment;
import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    //TODO: ARREGLAR ESTE GET MAPPING Y AGREGAR SWAGGER
    @GetMapping("" )
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
    }

    @GetMapping("/photos/{userId}")
    public ResponseEntity<List<PhotoDto>> getAllUserPhotos(@PathVariable("userId") int userId){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUserPhotos(userId));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByUserOrName(@RequestParam(value = "name", required = false) String name,
                                                                    @RequestParam(value = "userId", required = false) String userId){
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUserOrNameComments(userId, name));
    }
}
