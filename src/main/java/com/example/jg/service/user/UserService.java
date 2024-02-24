package com.example.jg.service.user;

import com.example.jg.config.auth.dto.CustomUserDetails;
import com.example.jg.domain.user.Role;
import com.example.jg.domain.user.User;
import com.example.jg.domain.user.UserRepository;
import com.example.jg.web.dto.user.UserResponseDto;
import com.example.jg.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public String save(UserSaveRequestDto userSaveRequestDto) {
        if(userRepository.findByEmail(userSaveRequestDto.getEmail()).isPresent())
            return null;
        return userRepository.save(User.builder()
                .name(userSaveRequestDto.getName())
                .password(bCryptPasswordEncoder.encode(userSaveRequestDto.getPassword()))
                .email(userSaveRequestDto.getEmail()).role(Role.USER).build()).getEmail();
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(username + "사용자를 찾을 수 없습니다"));
        return new CustomUserDetails(user);
    }
}
