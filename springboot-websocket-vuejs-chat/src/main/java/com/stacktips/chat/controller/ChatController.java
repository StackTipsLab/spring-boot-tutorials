package com.stacktips.chat.controller;

import com.stacktips.chat.dto.ChatMessage;
import com.stacktips.chat.model.Message;
import com.stacktips.chat.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@Controller
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesForUser() {
        List<Message> messages = messageService.findAllMessages();
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {

        Message message = new Message();
        message.setSenderEmail(chatMessage.getSenderEmail());
        message.setSenderName(chatMessage.getSenderName());
//        message.setReceiverId(chatMessage.getReceiver());
        message.setContent(chatMessage.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageService.save(message);
        return chatMessage;
    }
}
