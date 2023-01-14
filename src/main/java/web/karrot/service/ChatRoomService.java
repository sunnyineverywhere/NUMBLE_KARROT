package web.karrot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import web.karrot.controller.dto.ChatRoomRequestDTO;
import web.karrot.controller.dto.response.BodyMessage;
import web.karrot.controller.dto.response.CustomResponseEntity;
import web.karrot.controller.dto.response.ResponseMessage;
import web.karrot.controller.dto.response.StatusEnum;
import web.karrot.domain.entity.ChatRoom;
import web.karrot.domain.entity.Member;
import web.karrot.domain.entity.Product;
import web.karrot.domain.repository.ChatRoomRepository;
import web.karrot.domain.repository.MemberRepository;
import web.karrot.domain.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ProductRepository productRepository;

    public CustomResponseEntity<BodyMessage> addChatRoom(Member member, ChatRoomRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        if(product.getMember().equals(member)){
            return new CustomResponseEntity<>(BodyMessage.builder()
                    .status(StatusEnum.BAD_REQUEST)
                    .message("잘못된 요청입니다.")
                    .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        ChatRoom chatRoom = ChatRoom.builder()
                .subscriber(member)
                .product(product)
                .build();
        return new CustomResponseEntity<>(BodyMessage.builder()
                .status(StatusEnum.OK)
                .message("채팅방 생성 성공")
                .data(chatRoomRepository.save(chatRoom))
                .build()
                , HttpStatus.OK);
    }

    public CustomResponseEntity<BodyMessage> findChatRoomList(Member member) {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllBySubscriberOrProduct_Member(member, member);
        return new CustomResponseEntity<>(BodyMessage.builder()
                .status(StatusEnum.OK)
                .message("채팅방 생성 성공")
                .data(chatRoomList)
                .build()
                , HttpStatus.OK);
    }
}
