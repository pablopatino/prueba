package com.example.accenture.service;

import com.example.accenture.clients.JsonPlaceHolder;
import com.example.accenture.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UsersService{

    private final JsonPlaceHolder jsonPlaceHolder;

    @Override
    public List<User> getAllUsers() {
        return jsonPlaceHolder.getAllUsers();
    }
}
