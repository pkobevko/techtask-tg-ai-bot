package com.tttgaibot.service.impl;

import com.tttgaibot.model.Question;
import com.tttgaibot.repository.QuestionRepository;
import com.tttgaibot.service.QuestionService;
import java.time.Instant;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private static final ZoneId KYIV_ZONE_ID = ZoneId.of("Europe/Kyiv");
    private static final String QUESTION_PREFIX = "/askAdmin ";
    private static final String EMPTY_STRING = "";

    private final QuestionRepository questionRepository;

    @Override
    public Question add(Message message) {
        Question question = new Question();
        question.setText(message.getText().replaceFirst(QUESTION_PREFIX, EMPTY_STRING));
        question.setChatId(message.getChatId());
        question.setUserId(message.getFrom().getId());
        question.setCreationTime(Instant.ofEpochSecond(message.getDate()).atZone(KYIV_ZONE_ID));
        question.setStatus(Question.Status.ACTIVE);
        return questionRepository.save(question);
    }
}
