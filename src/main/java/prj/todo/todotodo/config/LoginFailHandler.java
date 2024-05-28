package prj.todo.todotodo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

// SimpleUrlAuthenticationFailureHandler: AuthenticationFailureHandler 인터페이스를 구현한 클래스
// setDefaultFailureUrl()를 제공해준다.
@Slf4j
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.info("Authentication failed: " + exception.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}
