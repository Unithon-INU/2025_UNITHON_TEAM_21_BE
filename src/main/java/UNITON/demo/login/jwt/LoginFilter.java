package UNITON.demo.login.jwt;

import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.dto.ErrorResponseDto;
import UNITON.demo.login.dto.LoginRequestDto;
import UNITON.demo.login.dto.LoginResponseDto;
import UNITON.demo.login.entity.RefreshToken;
import UNITON.demo.login.service.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    public LoginFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil,RefreshTokenService refreshTokenService){
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.refreshTokenService=refreshTokenService;
    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("=== LoginFilter 진입 ===");
        //클라이언트 요청에서 email, password 추출
        LoginRequestDto loginDTO = null;
        try {
            loginDTO = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (IOException e) {
            throw new RuntimeException("로그인 JSON 파싱 실패", e);
        }

        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        log.info(email);


        //스프링 시큐리티에서 email과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(email,password,null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("❌ 로그인 실패: " + failed.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorResponseDto errorDto = ErrorResponseDto.builder()
                .status(401)
                .error("이메일 또는 비밀번호가 일치하지 않습니다.")
                .timestamp(LocalDateTime.now().toString())
                .build();

        // 응답 JSON 출력
        new ObjectMapper().writeValue(response.getWriter(), errorDto);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authentication) throws IOException {
        CustomerUserDetails customerUserDetails=(CustomerUserDetails) authentication.getPrincipal();

        String email=customerUserDetails.getUsername();
        Integer userID= customerUserDetails.getUserId();

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        Iterator<? extends  GrantedAuthority> iterator=authorities.iterator();
        GrantedAuthority auth= iterator.next();

        String role=auth.getAuthority();

        String accessToken=jwtUtil.createJwt(email,role,60*60*1000L);
        RefreshToken refreshToken = refreshTokenService.createorUpdateRefreshToken(userID);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // (2) JSON으로 내려줄 데이터 구성
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .email(email)
                .nickname(customerUserDetails.getNickname())
                .message("로그인에 성공했습니다.")
                .build();

        // (3) JSON 변환 후 응답에 쓰기
        new ObjectMapper().writeValue(response.getWriter(), responseDto); //필터안에서 JSON을 내려준다

    }
}
