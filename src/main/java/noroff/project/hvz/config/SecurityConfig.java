package noroff.project.hvz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                // Sessions will not be used
                .sessionManagement().disable()
                // Disable CSRF -- not necessary when there are no sessions
                .csrf().disable()
                // Enable security for http requests
                .authorizeHttpRequests(authorize -> authorize

                        //Security matchers for game-controller
                        .requestMatchers(HttpMethod.GET,"/api/v1/game").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/game").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/game/{gameId}").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/game/{gameId}").hasRole("hvz_admin")

                        //Security matchers for squad-controller
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/game/{gameId}/squad/**").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/game/{gameId}/squad/**").hasRole("hvz_admin")

                        //Security matchers for game-player-controller
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/game/{gameId}/player/**").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/game/{gameId}/player/**").hasRole("hvz_admin")

                        //Security matchers for mission-controller
                        .requestMatchers(HttpMethod.POST,"/api/v1/game/{gameId}/mission/**").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/game/{gameId}/mission/**").hasRole("hvz_admin")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/game/{gameId}/mission/**").hasRole("hvz_admin")

                        //Security matchers for bite-controller
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/game/{gameId}/bite/**").hasRole("hvz_admin")

                        //Security matchers for swagger
                        .requestMatchers("/swagger-ui/").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/**").permitAll()

                        // All other endpoints are protected
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtRoleAuthenticationConverter());
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtRoleAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Use roles claim as authorities
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        // Add the ROLE_ prefix - for hasRole
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}



