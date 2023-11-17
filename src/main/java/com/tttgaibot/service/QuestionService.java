package com.tttgaibot.service;

import com.tttgaibot.model.Question;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface QuestionService {
    Question add(Message message);
}
