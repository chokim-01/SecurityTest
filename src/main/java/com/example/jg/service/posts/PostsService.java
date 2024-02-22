package com.example.jg.service.posts;

import com.example.jg.domain.posts.Posts;
import com.example.jg.domain.posts.PostsRepository;
import com.example.jg.web.dto.PostsListResponseDto;
import com.example.jg.web.dto.PostsResponseDto;
import com.example.jg.web.dto.PostsSaveRequestDto;
import com.example.jg.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deletePostById(Long id){
        postsRepository.deleteById(id);
    }
}
