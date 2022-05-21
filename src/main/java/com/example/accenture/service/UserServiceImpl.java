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

    public static final String NAME_OR_USERID_REQUIRED = "Porfavor ingresar un userId o un Name";
    public static final String USERID_REQUIRED = "Porfavor ingrese un UserId";
    public static final String ALBUMS_NOT_FOUND = "No se ha encontrado informacion del Albumes";

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

        List<Album> userAlbumList = jsonPlaceHolder.getAllUserAlbums(userId);
        //TODO: TAMBIEN SE PEUDE BUSCAR PRIMERO POR USUARIOS Y VER SI NO EXISTE
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
    public List<Comment> getAllUserOrNameComments(String userId, String name) {

        if (userId == null && name == null) {
            throw new BadRequestException(NAME_OR_USERID_REQUIRED);
        }
        List<Comment> userCommentsList = new ArrayList<>();

        if (name != null) {
            return jsonPlaceHolder.getAllCommentosByName(name);
        }

        jsonPlaceHolder.getAllPostId(Integer.parseInt(userId)).forEach(post ->
                jsonPlaceHolder.getAllUserComments(post.getId()).forEach(comment ->
                        userCommentsList.add(new Comment(comment.getPostId(), comment.getId(), comment.getName(), comment.getEmail(), comment.getBody()))
                )
        );
        return userCommentsList;
    }
}
