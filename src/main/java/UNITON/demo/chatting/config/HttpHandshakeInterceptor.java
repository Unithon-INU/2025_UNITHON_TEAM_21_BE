package UNITON.demo.chatting.config;

/*import UNITON.demo.login.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    private final JWTUtil jwtUtil;

    public HttpHandshakeInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpReq = servletRequest.getServletRequest();
            String token = null;

            // 1. 우선 Authorization 헤더에서 시도
            String header = httpReq.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
            }

            // 2. 못 찾으면 query 파라미터에서 시도 (React Native에서 SockJS 연결 시 유효)
            if (token == null) {
                token = httpReq.getParameter("token");
            }

            System.out.println("🪪 추출된 토큰: " + token);

            if (token != null && !token.isEmpty()) {
                try {
                    String email = jwtUtil.getEmail(token);
                    System.out.println("✅ 토큰 이메일: " + email);
                    attributes.put("userEmail", email);
                } catch (Exception e) {
                    System.out.println("❌ JWT 파싱 실패: " + e.getMessage());
                    return false;
                }
            } else {
                System.out.println("❌ 토큰 없음");
            }
        }

        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
        // Do nothing
    }
}*/
