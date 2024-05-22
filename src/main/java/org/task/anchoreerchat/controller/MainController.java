package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class MainController {

        @GetMapping("/")
        public String index() {
            return "index";
        }

        @GetMapping("/chat/{chatRoomId}")
        public String chat(@PathVariable String chatRoomId, Model model) {
            model.addAttribute("chatRoomId", chatRoomId);
            return "chat";
        }
}
