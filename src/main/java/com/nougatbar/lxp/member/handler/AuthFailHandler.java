package com.nougatbar.lxp.member.handler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.View;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private final View error;

    public AuthFailHandler(View error) {
        this.error = error;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String errorMessage;

        // 비밀번호가 맞지 않는 경우
        if (exception instanceof BadCredentialsException) {
            errorMessage = "ID가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
            // 서버 단에서 오류
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = " 서버에서 오류가 발행되었습니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 이메일 입니다.";
            // 인증 정보가 없을 때
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "인증 요청이 거부되었습니다.";
        } else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }

        String encoded = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        saveException(request, exception);

        getRedirectStrategy().sendRedirect(request, response, "/auth/fail?message=" + encoded);

    }
}
