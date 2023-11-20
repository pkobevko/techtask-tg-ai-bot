package com.tttgaibot.service;

import com.tttgaibot.model.Question;
import com.tttgaibot.model.User;
import java.util.List;
import java.util.Map;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface QuestionService {
    Question add(Message message);

    List<Question> getAllByStatus(Question.Status status);

    Question answer(Long questionId, String text, User user);

    Question getById(Long id);
}
