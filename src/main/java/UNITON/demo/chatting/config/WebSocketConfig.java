package UNITON.demo.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.*;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지를 구독할 prefix (클라이언트 -> 서버)
        config.enableSimpleBroker("/topic");  // 메모리 기반 브로커
        config.setApplicationDestinationPrefixes("/app");  // 요청 prefix
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                if (StompCommand.SEND.equals(accessor.getCommand())) {
                    System.out.println("📨 [SEND 도착]: " + new String((byte[]) message.getPayload(), StandardCharsets.UTF_8));
                }
                return message;
            }


            /*@Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println("📥 수신된 STOMP 메시지: " + message);
                return message;
            }*/
        });
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 연결할 WebSocket endpoint
        registry.addEndpoint("/ws-chat")
                .setAllowedOrigins("http://127.0.0.1:5500", "http://localhost:5500", "https://63a4-106-101-8-197.ngrok-free.app")
                .withSockJS();
    }
}
