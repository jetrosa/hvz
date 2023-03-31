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

/**
 * Service for caching failed authentication attempts and determining if the request IP is temporarily blocked based on
 * the number of maximum allowed attempts set in the constructor.
 */
@Service
public class LoginAttemptService {
    //number of failed attempts allowed before temporary IP block
    public static final int MAX_ATTEMPT = 10;
    //Cache of failed attempts: <IP,attempts>
    private final LoadingCache<String, Integer> attemptsCache;
    private final Logger logger = LoggerFactory.getLogger(LoginAttemptService.class);

    /**
     * Builds a cache that expires after the expiry time set in the method.
     */
    public LoginAttemptService() {
        int expireHours = 1; //cache expiry time, IP unblocked/authentication possible again after that
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(expireHours, TimeUnit.HOURS).build(new CacheLoader<>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    /**
     * Increases and logs the cached number of authentication failures for the IP address.
     *
     * @param key cache key (IP address)
     */
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

    /**
     * Checks whether the request IP address is blocked.
     *
     * @param request HTTP request
     * @return true if blocked
     */
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
