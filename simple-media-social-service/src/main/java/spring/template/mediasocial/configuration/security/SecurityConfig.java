package spring.template.mediasocial.configuration.security;

import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import spring.template.mediasocial.service.jwt.JwtService;
import spring.template.mediasocial.service.user.UserProviderService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserProviderService userProviderService;

    private final JwtService jwtService;

    @Autowired
    public SecurityConfig(UserProviderService userProviderService, JwtService jwtService) {
        this.userProviderService = userProviderService;
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    // DISABLE CSRF
                    .csrf(AbstractHttpConfigurer::disable)

                    // AUTH RULES
                    .authorizeHttpRequests(
                            authorizationManagerRequestMatcherRegistry ->
                                    authorizationManagerRequestMatcherRegistry
                                            .requestMatchers(
                                                    "/v1/admin-credential/**",
                                                    "/v3/signups/**",
                                                    "/swagger-ui/**",
                                                    "/v3/api-docs/**",
                                                    "/v1/jwt/example/**",
                                                    "/actuator",
                                                    "/actuator/**"
                                            )
                                            .permitAll()
                                            .anyRequest()
                                            .authenticated()
                    )

                    // SESSION MANAGEMENT
                    .sessionManagement(httpSecuritySessionManagementConfigurer ->
                            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    );

                    // Filter
                    //.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
