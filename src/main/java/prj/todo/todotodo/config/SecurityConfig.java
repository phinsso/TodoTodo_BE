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
    public SecurityFilterChain securityfilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/todo/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        // 로그인을 처리할 URL
                        .loginProcessingUrl("/login")
                        // 로그인에 성공하면 (인증이 되면) 리다이렉트 될 URL
                        // true: 로그인 전에 접근하려던 페이지가 있을 경우 그 페이지로 리다이렉트되게 함.
                        // .defaultSuccessUrl("/todos", true)
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailHandler())
                )
                .csrf((auth) -> auth.disable());

        return httpSecurity.build();
    }
}

