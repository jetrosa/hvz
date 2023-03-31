package noroff.project.hvz.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PreSecurityFilter implements Filter {
    private final LoginAttemptService loginAttemptService;

    public PreSecurityFilter(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        if (loginAttemptService.isBlocked(request)) {
            // Return suitable response message
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            // Only valid requests is allowed through the filter
            filterChain.doFilter(request, response);
        }

    }
}
