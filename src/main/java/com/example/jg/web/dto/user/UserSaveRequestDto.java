package com.example.jg.web.dto.user;

import com.example.jg.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String password;
    private String email;

    @Builder
    public UserSaveRequestDto(String name,String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User toEntity() {
        return User.builder().name(name).email(email).password(password).build();
    }
}
