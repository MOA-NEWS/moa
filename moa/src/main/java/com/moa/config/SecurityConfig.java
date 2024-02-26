package com.moa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration // 스프링 컨테이너에 빈으로 등록
@EnableWebSecurity //스프링 시큐리티에서 관리한다 커스텀이 가능해짐
public class SecurityConfig {

    // BCrypt 암호화
    // 시큐리티는 사용자의 비밀번호에 대해 암호화를 진행하여 저장되어 있는 비밀번호와 대조
    // 단방향 해시 값으로 암호화 한다
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //권한에 대해 계층을 두고 싶을 때
    //URL 접근시 hasAnyRole() 인자로 최소 접근 권한을 입력
    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        // ROLE_ADMIN > ROLE_USER
        hierarchy.setHierarchy("""
                ROLE_ADMIN > ROLE_USER
                """);

        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 경로에 대한 권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                        .requestMatchers(
                                "/", "/login",
                                "/boards/list", "/boards/{boardId}",
                                "/members/new",
                                "/jpaTest/**", "/spTest/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/boards/new").hasAnyRole("USER") // USER, ADMIN 둘 다 가능
                        .anyRequest().authenticated()
                )

                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("name")
                        .passwordParameter("password")
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/"))
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화


                // 로그아웃 설정
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"))



        ;
        return http.build();
    }
}

