package com.example.accenture.controllers;

import com.example.accenture.clients.JsonPlaceHolder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

    private final JsonPlaceHolder jsonPlaceHolder;


}
