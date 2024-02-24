package com.example.jg.config.auth;

import com.example.jg.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable()).authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers("api/v1/signPage","api/v1/signIn").permitAll()
                        .requestMatchers("api/v1/**").hasRole(Role.USER.toString()).anyRequest().authenticated())

                .logout(l -> l.logoutSuccessUrl("/"))
                .formLogin(Customizer.withDefaults())
                .oauth2Login(o -> o.userInfoEndpoint(u -> u.userService(customOAuth2UserService)));
        return http.build();
    }

    @Bean
    protected static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests().antMathcers("/","/css/**").permitAll()
                .antMatchers("api/v1/**").hasRole(Role.USER.name())
                .antRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
*/