package com.example.jg.web;

import com.example.jg.service.posts.PostsService;
import com.example.jg.web.dto.PostsResponseDto;
import com.example.jg.web.dto.PostsSaveRequestDto;
import com.example.jg.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;



    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    @GetMapping("/api/v2/posts")
    public List<?> get(){
        return postsService.findAll();
    }
    @GetMapping("/api/v2/posts/{id}")
    public PostsResponseDto getById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
