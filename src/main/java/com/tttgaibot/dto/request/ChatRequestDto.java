package com.tttgaibot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tttgaibot.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequestDto {
    private static final int DEFAULT_NUMBER_OF_RESPONSES_TO_GENERATE = 1;
    private static final double DEFAULT_RANDOMNESS_OF_THE_RESPONSE = 1;
    private static final String DEFAULT_ROLE_OF_REQUEST = "user";

    private String model;
    private List<Message> messages;
    @JsonProperty("n")
    private int numberOfResponses;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message(DEFAULT_ROLE_OF_REQUEST, prompt));
        this.numberOfResponses = DEFAULT_NUMBER_OF_RESPONSES_TO_GENERATE;
        this.temperature = DEFAULT_RANDOMNESS_OF_THE_RESPONSE;
    }
}

