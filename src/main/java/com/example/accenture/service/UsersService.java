package com.example.accenture.service;

import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    List<PhotoDto> getAllUserAlbums(int userId);
}
