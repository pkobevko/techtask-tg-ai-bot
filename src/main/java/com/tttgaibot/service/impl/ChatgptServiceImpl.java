package com.tttgaibot.service.impl;

import com.tttgaibot.dto.request.ChatRequestDto;
import com.tttgaibot.dto.response.ChatResponseDto;
import com.tttgaibot.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatgptServiceImpl implements ChatgptService {
    private static final int FIRST_CHOICE_NUMBER = 0;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;
    @Qualifier("chatgptRestTemplate")
    private final RestTemplate restTemplate;

    @Override
    public String ask(String question) {
        ChatRequestDto request = new ChatRequestDto(model, question);
        ChatResponseDto response = restTemplate.postForObject(apiUrl, request,
                ChatResponseDto.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        return response.getChoices().get(FIRST_CHOICE_NUMBER).getMessage().getContent();
    }
}
