package web.karrot.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.karrot.service.ChatRoomService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/ws")
public class WebSocketTestController {

    private final ChatRoomService chatroomService;

    @GetMapping()
    public String rooms(Model model) {
        return "room";
    }

    @GetMapping("/enter/{productId}")
    public String roomDetail(Model model, @PathVariable String productId) {
        model.addAttribute("productId", productId);
        return "roomDetail";
    }

}
