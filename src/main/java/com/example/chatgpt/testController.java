package com.example.chatgpt;

import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class testController {
    private final ChatService chatService;
    private final ChatgptService chatgptService;

    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("")
    public String test(@RequestBody String question){
        return chatService.getChatResponse(question);
        //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }
    //chat-gpt 가 알아서 이미지 생성해주는 소스.
    @PostMapping("/image")
    public String testImage(String question){
        String imageUrl = chatgptService.imageGenerate("A cute baby sea otter");
        return imageUrl; //https://oaidalleapip........
    }
}
