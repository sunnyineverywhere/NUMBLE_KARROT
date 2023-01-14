package web.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import web.karrot.config.jwt.JwtTokenProvider;
import web.karrot.controller.dto.MessageRequestDTO;
import web.karrot.domain.entity.ChatMessage;
import web.karrot.domain.entity.ChatRoom;
import web.karrot.domain.entity.Member;
import web.karrot.domain.repository.ChatMessageRepository;
import web.karrot.domain.repository.ChatRoomRepository;
import web.karrot.domain.repository.MemberRepository;

@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;


    @MessageMapping("/message")
    public void enter(@Payload MessageRequestDTO requestDTO, @Header String Authorization) {

        Member member = memberRepository.findByEmail(jwtTokenProvider.getMemberInfo(Authorization))
                .orElseThrow(() -> new IllegalArgumentException("토큰으로 유저를 찾을 수 없습니다. 다시 시도해 주세요."));

        ChatRoom chatRoom = chatRoomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요청입니다."));

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(member)
                .chatRoom(chatRoom)
                .contents(requestDTO.getMessage())
                .build();
        chatMessageRepository.save(chatMessage);

        sendingOperations.convertAndSend("/topic/chat/room/" + requestDTO.getRoomId(), chatMessage);
    }
}