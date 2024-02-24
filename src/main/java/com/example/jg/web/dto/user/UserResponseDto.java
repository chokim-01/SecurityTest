package com.example.jg.web.dto.user;

import com.example.jg.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String picture;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.picture = entity.getPicture();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
