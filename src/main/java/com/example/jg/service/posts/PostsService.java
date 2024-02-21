package com.example.jg.service.posts;

import com.example.jg.domain.posts.Posts;
import com.example.jg.domain.posts.PostsRepository;
import com.example.jg.web.dto.PostsResponseDto;
import com.example.jg.web.dto.PostsSaveRequestDto;
import com.example.jg.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id,PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
    return id;
    }
    @Transactional
    public List<PostsResponseDto> findAll(){
        List<PostsResponseDto> responseDtoList = postsRepository.findAll().stream().map(PostsResponseDto::new).toList();
        return responseDtoList;
    }
    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
