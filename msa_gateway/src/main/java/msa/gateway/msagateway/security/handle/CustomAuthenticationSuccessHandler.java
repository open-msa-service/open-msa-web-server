package msa.gateway.msagateway.security.handle;

import lombok.extern.slf4j.Slf4j;
import msa.gateway.msagateway.security.inter.ExceptionProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인 성공시 행동을 재정의할 클래스(추상 클래스가 아닌 인터페이스를 구현해도 된다.)
 * Or Interface - AuthenticationSuccessHandler
 */
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements ExceptionProcessor {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        log.info("CustomAuthenticationSuccessHandler.onAuthenticationSuccess ::::");
        /*
         * 쿠키에 인증 토큰을 넣어준다.
         */
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    public void makeExceptionResponse(HttpServletRequest request, HttpServletResponse response,
                                      Exception exception) {
    }

}