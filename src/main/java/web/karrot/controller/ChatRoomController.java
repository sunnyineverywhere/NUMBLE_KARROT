package web.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.karrot.config.annotation.AuthMember;
import web.karrot.controller.dto.ChatRoomRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.domain.entity.Member;
import web.karrot.service.ChatRoomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/room")
    public CustomResponseEntity<BodyMessage> ChatRoomAdd(@AuthMember Member member, @RequestBody ChatRoomRequestDTO requestDTO){
        return chatRoomService.addChatRoom(member, requestDTO);
    }

    @GetMapping("/room")
    public CustomResponseEntity<BodyMessage> ChatRoomMessageAllFind(@AuthMember Member member, @RequestParam Long id){
        return chatRoomService.findAllChatMessage(member, id);
    }

    @GetMapping("/rooms")
    public CustomResponseEntity<BodyMessage> ChatRoomListFind(@AuthMember Member member){
        return chatRoomService.findChatRoomList(member);
    }

}
