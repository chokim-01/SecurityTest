package com.example.jg.web;

import com.example.jg.service.user.UserService;
import com.example.jg.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/signIn")
    public String save(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return userService.save(userSaveRequestDto);
    }
    @GetMapping("/api/v1/users")
    public List<?> get() {
        return userService.findAll();
    }
}
