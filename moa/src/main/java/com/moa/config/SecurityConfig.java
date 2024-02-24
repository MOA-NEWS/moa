//package com.moa.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration // 스프링 컨테이너에 빈으로 등록
//@EnableWebSecurity //스프링 시큐리티에서 관리한다 커스텀이 가능해짐
//public class SecurityConfig {
//
//    // BCrypt 암호화
//    // 시큐리티는 사용자의 비밀번호에 대해 암호화를 진행하여 저장되어 있는 비밀번호와 대조
//    // 단방향 해시 값으로 암호화 한다
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                // 경로에 대한 권한 설정
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/login", "/boards/list", "/boards/{boardId}").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//
//                // 로그인 설정
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/")
//                        .failureUrl("/login?error=true")
//                )
//
//
//
//                ;
//        return http.build();
//    }
//}
//
