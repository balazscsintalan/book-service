package hu.danubius.bookservice.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CustomWebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails baseUser = User.withDefaultPasswordEncoder()
            .username("base-user")
            .password("password")
            .roles("BASE_USER")
            .build();

        UserDetails adminUser = User.withDefaultPasswordEncoder()
            .username("admin-user")
            .password("password")
            .roles("ADMIN_USER")
            .build();

        return new InMemoryUserDetailsManager(baseUser, adminUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(Customizer.withDefaults())
            .httpBasic(httpSecurityHttpBasicConfigurer ->
                httpSecurityHttpBasicConfigurer
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .csrf(httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer
                    .ignoringRequestMatchers(PathRequest.toH2Console()))
            .headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .authorizeHttpRequests(registry ->
                registry
                    .requestMatchers(PathRequest.toH2Console())
                    .permitAll()
//                    .requestMatchers(
//                        "/authors",
//                        "/authors/**"
//                    )
//                    .hasRole("ADMIN_USER")
                    .anyRequest()
                    .authenticated()
            )
            .build();
    }
}
