package com.nougatbar.lxp.member.config;


import com.nougatbar.lxp.member.entity.MemberRole;
import com.nougatbar.lxp.member.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthFailHandler authFailHandler;


    @Autowired
    public SecurityConfig(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    // 비밀번호 단방향 해시용 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스에 대한 요청은 인증 제외하겠다.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
                    // 비로그인 사용자도 접근 가능한 공개 경로, /auth/fail -> 로그인 실패 페이지 현재 없음
                    auth.requestMatchers("/auth/login", "/auth/fail", "/members/signup", "/").permitAll();
                    // 관리자 페이지 x
                    auth.requestMatchers("/admin/**").hasAnyAuthority(MemberRole.ADMIN.getValue());
                    // 일반회원 전용 영역
                    auth.requestMatchers("/member/**").hasAnyAuthority(MemberRole.MEMBER.getValue());
                    // 그 외 모든 요청은 인증 필요
                    auth.anyRequest().authenticated();
                }
                // 로그인 설정
        ).formLogin(form -> {
            form.loginPage("/auth/login");
            form.defaultSuccessUrl("/", true);
            form.failureHandler(authFailHandler);
        }).logout(logout -> {
            logout.logoutUrl("/auth/logout");
            // JSESSION 쿠키 제거 - 브라우저에 남은 세션 식별자도 함께 정리
            logout.deleteCookies("JSESSEIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
            // 세션 관리, 동일 사용자 최대 1세션, 만료시 루트로 이동
        }).sessionManagement(session -> {
            session.maximumSessions(1);
            session.invalidSessionUrl("/");
            
        }).csrf(csrf -> csrf.disable());

        return http.build();

    }

}
