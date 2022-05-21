package com.example.accenture.service;

import com.example.accenture.clients.JsonPlaceHolder;
import com.example.accenture.domain.Album;
import com.example.accenture.domain.Comment;
import com.example.accenture.domain.Photo;
import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.dtomapper.EntityToDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UsersService{

    private final JsonPlaceHolder jsonPlaceHolder;
    private final EntityToDto entityToDto;

    @Override
    public List<User> getAllUsers() {
        return jsonPlaceHolder.getAllUsers();
    }

    @Override
    public List<PhotoDto> getAllUsersPhotos(int userId) {

        List<Album> userAlbumList = jsonPlaceHolder.getAllUserAlbums(userId);
        List<Photo> userPhotosList = new ArrayList<>();

        userAlbumList.forEach(album ->
            jsonPlaceHolder.getAllUserPhoto(album.getId()).forEach(photo ->
                userPhotosList.add(new Photo(photo.getAlbumId(),photo.getId(),photo.getTitle(),photo.getUrl(),photo.getThumbnailUrl()))
            )
        );
        //CABE RECALCAR, QUE SE PUEDE DEVOLVER TODO EL OBJETO CON LAS IDS, PERO ALGUANS VECES ES MEJOR NO MONSTRAR ESAS IDS
        List<PhotoDto> userPhotosDtoList = new ArrayList<>();
        userPhotosList.forEach(photo -> userPhotosDtoList.add(entityToDto.photoEntityToDto(photo)));
        return userPhotosDtoList;
    }

    @Override
    public List<Comment> getAllUserOrNameComments(String userId, String name) {

        List<Comment> userCommentsList = new ArrayList<>();
        
        jsonPlaceHolder.getAllPostId(Integer.parseInt(userId)).forEach(post ->
            jsonPlaceHolder.getAllUserComments(post.getId()).forEach(comment ->
                userCommentsList.add(new Comment(comment.getPostId(),comment.getId(),comment.getName(),comment.getEmail(),comment.getBody()))
            )
        );


        return userCommentsList;
    }
}
