package com.example.jg.config.auth;

import com.example.jg.config.auth.dto.CustomUserDetails;
import com.example.jg.config.auth.dto.SessionUser;
import com.example.jg.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// id password 기반 인증
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(email);
        if(passwordEncoder.matches(password,userDetails.getPassword())) {
            httpSession.setAttribute("user", new SessionUser(userDetails.getUser()));
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        }
        else
            throw new BadCredentialsException("아이디 또는 비밀번호를 찾을 수 없습니다");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
