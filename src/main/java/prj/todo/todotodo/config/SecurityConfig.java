package prj.todo.todotodo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 해당 클래스가 설정 파일임을 선언
@EnableWebSecurity // 해당 클래스가 스프링 시큐리티에게 관리됨
public class SecurityConfig {

    // 비밀번호 암호화 (단방향 해시 암호화)
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 각 경로에 따른 접근 권한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "login", "/join", "/h2-console").permitAll() // 해당 경로에 대해 모든 사용자의 접근을 허용
//                        .requestMatchers("/admin").hasRole("ADMIN") // 사용자가 주어진 역할이 있다면 접근 허용
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().authenticated() // 이외의 경로에 대해서는 로그인한 사용자만 접근 허용
//                );

        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }
}

