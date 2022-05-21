package com.example.accenture.controllers;

import com.example.accenture.domain.Comment;
import com.example.accenture.domain.User;
import com.example.accenture.dto.PhotoDto;
import com.example.accenture.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private final UsersService usersService;

    @ApiOperation(value = "Obtener todos los usuarios de JsonPlaceHolder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios encontrados"),
    })
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
    }

    @ApiOperation(value = "Obtener todas las photos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photos encontradas correctamente"),
            @ApiResponse(code = 400, message = "Porfavor ingrese un userId correcto"),
            @ApiResponse(code = 404, message = "No se ha encontrado photo para este usuario")
    })
    @GetMapping("/photos/{userId}")
    public ResponseEntity<List<PhotoDto>> getAllUserPhotos(
            @ApiParam("Id del usuario al que se le quiera buscar las photos") @PathVariable("userId") int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUserPhotos(userId));
    }

    @ApiOperation(value = "Obtener todos los comentarios por usuario o por nombre del comentario", notes = "Aqui no se si era por el campo name del usuario o por el campo name del comentario, lo hice por el campo name del comentario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comentarios encontrados correctamente"),
            @ApiResponse(code = 400, message = "Se debe de ingresar un usuario y/o un nombre"),
            @ApiResponse(code = 404, message = "No encontrado")
    })
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByUserOrName(
            @ApiParam(value = "Name del comentario que se quiera buscar") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Id del usuario que se quiera buscar") @RequestParam(value = "userId", required = false) Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUserOrNameComments(userId, name));
    }
}
