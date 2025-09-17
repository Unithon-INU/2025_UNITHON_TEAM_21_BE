package UNITON.demo.chatting.controller;

/*import UNITON.demo.chatting.dto.ChatMessageDto;
import UNITON.demo.chatting.service.ChatService;
import UNITON.demo.login.dto.CustomerUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Arrays;

@Controller
public class ChatWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send")
    public void sendMessage(Message<byte[]> message, Principal principal) {
        System.out.println("🔥 Controller 진입 성공!");
        String json = new String(message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("📨 message string: " + json);

        try {
            // LocalDateTime 처리를 위한 Jackson 모듈 등록
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            ChatMessageDto chatMessage = mapper.readValue(json, ChatMessageDto.class);

            String senderEmail;
            if (principal != null && principal.getName() != null) {
                senderEmail = principal.getName();
            } else {
                senderEmail = chatMessage.getSenderEmail();  // fallback
            }

            System.out.println("✅ senderEmail: " + senderEmail);
            System.out.println("📝 content: " + chatMessage.getContent());
            System.out.println("🕒 sentAt: " + chatMessage.getSentAt());

            // 저장 및 브로드캐스트
            ChatMessageDto saved = chatService.handleIncomingMessage(chatMessage, senderEmail);
            messagingTemplate.convertAndSend("/topic/chatroom/" + saved.getChatRoomId(), saved);

        } catch (Exception e) {
            System.err.println("❌ JSON 파싱 실패: " + e.getMessage());
        }
    }

}*/
