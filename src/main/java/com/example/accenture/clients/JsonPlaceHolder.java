package com.example.accenture.clients;

import com.example.accenture.domain.Album;
import com.example.accenture.domain.Photo;
import com.example.accenture.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${spring.jsonplaceholder.url}", name = "${spring.jsonplaceholder.name}")
public interface JsonPlaceHolder {

    @GetMapping("/users")
    List<User> getAllUsers();

    @GetMapping("/albums")
    List<Album> getAllUserAlbums(@RequestParam("userId") int userId);

    @GetMapping("/albums/{albumId}/photos")
    List<Photo> getAllUserPhoto(@PathVariable("albumId") int albumId);
}
