package com.tttgaibot.dto.response;

import com.tttgaibot.model.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto {
    private List<Choice> choices;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}
