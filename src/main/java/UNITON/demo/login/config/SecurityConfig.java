package UNITON.demo.login.config;

import UNITON.demo.login.jwt.JWTFilter;
import UNITON.demo.login.jwt.JWTUtil;
import UNITON.demo.login.jwt.LoginFilter;
import UNITON.demo.login.repository.UserRepository;
import UNITON.demo.login.service.RefreshTokenService;
import jakarta.persistence.Entity;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userrepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //csrf disable

        http
                .csrf((auth) ->auth.disable())
                .formLogin((auth) ->auth.disable())
                .httpBasic((auth) ->auth.disable()) //체이닝 적용
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers(
                                "/api/join/email","/api/join/oauth","/login","/api/login/oauth/kakao","/api/oauth/refreshtoken"
                                ,"/api/volunteer/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden")
                        )
                ) // 이 설정이 없으면 403에러 반환하게됨...jwt없을때는 401로 반환해야함

                .addFilterBefore(new JWTFilter(jwtUtil,userrepository), LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil,refreshTokenService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
