package UNITON.demo.login.jwt;

import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        System.out.println(">> 요청 경로: " + path);
        // ✅ 예외 경로 추가
        if (path.startsWith("/api/join") || path.equals("/login") ) {
            filterChain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/api/login/oauth/kakao")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization=request.getHeader("Authorization");
        if(authorization ==null || !authorization.startsWith("Bearer ")){
            System.out.println("token null");
            filterChain.doFilter(request,response);

            return;
        }

        String token=authorization.split(" ")[1];

        if(jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request,response);

            return;
        }

        String email=jwtUtil.getEmail(token);

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 없음"));



        CustomerUserDetails customerUserDetails=new CustomerUserDetails(userEntity);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(customerUserDetails, null, customerUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
