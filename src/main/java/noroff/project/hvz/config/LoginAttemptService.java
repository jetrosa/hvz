package noroff.project.hvz.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//Implementation based on  Eugen Paraschiv @ https://www.baeldung.com/spring-security-block-brute-force-authentication-attempts
@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPT = 10;
    private final LoadingCache<String, Integer> attemptsCache;
    private final Logger logger = LoggerFactory.getLogger(LoginAttemptService.class);

    public LoginAttemptService() {
        super();
        int expireHours = 1;
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(expireHours, TimeUnit.HOURS).build(new CacheLoader<>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void loginFailed(final String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
        logger.warn(String.format("Failed authentication attempts - %s: %d", key, attempts));
    }

    public boolean isBlocked(HttpServletRequest request) {
        try {
            return attemptsCache.get(getClientIP(request)) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
