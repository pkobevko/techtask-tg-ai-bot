package com.tttgaibot.controller;

import com.tttgaibot.model.ChatLog;
import com.tttgaibot.service.ChatLogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final ChatLogService chatLogService;

    @GetMapping("/{id}/logs")
    public ResponseEntity<List<ChatLog>> getChatLogsByChatId(@PathVariable("id") Long chatId) {
        List<ChatLog> allByChatId = chatLogService.getAllByChatId(chatId);
        return new ResponseEntity<>(allByChatId, HttpStatus.OK);
    }
}
