package com.example.accenture.service;

import com.example.accenture.clients.JsonPlaceHolder;
import com.example.accenture.domain.Album;
import com.example.accenture.domain.Comment;
import com.example.accenture.domain.Photo;
import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.dtomapper.EntityToDto;
import com.example.accenture.exceptions.BadRequestException;
import com.example.accenture.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UsersService {

    public static final String NAME_OR_USERID_REQUIRED = "Porfavor ingresar un usuario o un Name";
    public static final String USERID_REQUIRED = "Porfavor ingrese un usuario valido";
    public static final String ALBUMS_NOT_FOUND = "Este usuario no existe y/o no tiene photos";
    public static final String USER_NOT_FOUND= "Usuario no encontrado";
    public static final String COMMENT_NOT_FOUND = "Comentarios no encontrados";

    public static final int CERO = 0;

    private final JsonPlaceHolder jsonPlaceHolder;
    private final EntityToDto entityToDto;

    @Override
    public List<User> getAllUsers() {
        return jsonPlaceHolder.getAllUsers();
    }

    @Override
    public List<PhotoDto> getAllUserPhotos(int userId) {

        if (userId == CERO) {
            throw new BadRequestException(USERID_REQUIRED);
        }

        userExist(userId);

        List<Album> userAlbumList = jsonPlaceHolder.getAllUserAlbums(userId);
        if (userAlbumList.isEmpty()) {
            throw new NotFoundException(ALBUMS_NOT_FOUND);
        }
        List<Photo> userPhotosList = new ArrayList<>();

        userAlbumList.forEach(album ->
                jsonPlaceHolder.getAllUserPhoto(album.getId()).forEach(photo ->
                        userPhotosList.add(new Photo(photo.getAlbumId(), photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl()))
                )
        );
        //CABE RECALCAR, QUE SE PUEDE DEVOLVER TODO EL OBJETO CON LAS IDS, PERO ALGUANS VECES ES MEJOR NO MONSTRAR ESAS IDS
        List<PhotoDto> userPhotosDtoList = new ArrayList<>();
        userPhotosList.forEach(photo -> userPhotosDtoList.add(entityToDto.photoEntityToDto(photo)));
        return userPhotosDtoList;
    }

    @Override
    public List<Comment> getAllUserOrNameComments(Integer userId, String name) {

        if (userId == null && name == null) {
            throw new BadRequestException(NAME_OR_USERID_REQUIRED);
        }

        if (name != null) {
            List<Comment> commentListByName =  jsonPlaceHolder.getAllCommentosByName(name);
            if (commentListByName.isEmpty()){
                throw new NotFoundException(COMMENT_NOT_FOUND);
            }
            return commentListByName;
        }

        userExist(userId);

        List<Comment> userCommentsList = new ArrayList<>();

        jsonPlaceHolder.getAllPostId(userId).forEach(post ->
                jsonPlaceHolder.getAllUserComments(post.getId()).forEach(comment ->
                        userCommentsList.add(new Comment(comment.getPostId(), comment.getId(), comment.getName(), comment.getEmail(), comment.getBody()))
                )
        );
        return userCommentsList;
    }

    private void userExist(int userId){
        List<User> user = jsonPlaceHolder.getUserById(userId);
        if (user.isEmpty()){
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }
}
