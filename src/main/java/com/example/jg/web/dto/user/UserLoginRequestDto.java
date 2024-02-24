package com.example.jg.web.dto.user;

import com.example.jg.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String name;
    private String email;
    private String picture;

    @Builder
    public UserLoginRequestDto(String name, String email, String password, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public User toEntity() {
        return User.builder().name(name).email(email).picture(picture).build();
    }
}
