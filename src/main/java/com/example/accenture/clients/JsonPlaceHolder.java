package com.example.accenture.clients;

import com.example.accenture.domain.*;
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

    @GetMapping("/posts/{userId}/posts")
    List<Post> getAllPostId(@PathVariable("userId") int userId);

    @GetMapping("posts/{postId}/comments")
    List<Comment> getAllUserComments(@PathVariable("postId") int postId);

    @GetMapping("/comments")
    List<Comment> getAllCommentosByName(@RequestParam("name") String name);
}
