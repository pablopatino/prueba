package com.example.accenture.service;

import com.example.accenture.domain.Comment;
import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UsersService usersService;

    @Test
    void getAllUsers() {
        List<User> userList = usersService.getAllUsers();
        assertThat(userList).isNotEmpty();
    }

    @Test
    void getAllUserPhotos() {
        List<PhotoDto> userPhotosList = usersService.getAllUserPhotos(1);
        assertThat(userPhotosList).isNotEmpty();
    }

    @Test
    void getAllCommentsByUserId() {
        List<Comment> commentsList = usersService.getAllUserOrNameComments("1",null);
        assertThat(commentsList).isNotEmpty();
    }

    @Test
    void gettAllCommentsByName(){
        List<Comment> commentsList = usersService.getAllUserOrNameComments(null,"id labore ex et quam laborum");
        assertThat(commentsList).isNotEmpty();
    }

    @Test
    void getExceptionInGetAllUserPhotos(){
        try {
            usersService.getAllUserPhotos(0);
        } catch (BadRequestException e){
            assertThat(e.getMessage()).isEqualTo("Porfavor ingrese un UserId");
        }
    }

    @Test
    void getExceptionInGetAllCommentsWhenIdAndNameIsNull(){
        try {
            usersService.getAllUserOrNameComments(null,null);
        } catch (BadRequestException e){
            assertThat(e.getMessage()).isEqualTo("Porfavor ingresar un userId o un Name");
        }
    }
}