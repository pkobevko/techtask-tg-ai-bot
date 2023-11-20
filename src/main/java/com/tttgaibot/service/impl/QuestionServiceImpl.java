package com.tttgaibot.service.impl;

import com.tttgaibot.exception.QuestionAlreadyClosedException;
import com.tttgaibot.model.ChatLog;
import com.tttgaibot.model.Question;
import com.tttgaibot.model.User;
import com.tttgaibot.repository.QuestionRepository;
import com.tttgaibot.service.ChatLogService;
import com.tttgaibot.service.MessageSender;
import com.tttgaibot.service.QuestionService;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private static final ZoneId KYIV_ZONE_ID = ZoneId.of("Europe/Kyiv");
    private static final String QUESTION_PREFIX = "/askAdmin ";
    private static final String EMPTY_STRING = "";
    private final QuestionRepository questionRepository;
    private final MessageSender messageSender;
    private final ChatLogService chatLogService;

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

    @Override
    public List<Question> getAllByStatus(Question.Status status) {
        return questionRepository.findAllByStatus(status);
    }

    @Override
    @Transactional
    public Question answer(Long questionId, String text, User user) {
        Question question = getById(questionId);
        if (question.getStatus() == Question.Status.CLOSED) {
            throw new QuestionAlreadyClosedException("Question with id " + questionId + " already "
                    + "closed!");
        }
        question.setClosureTime(ZonedDateTime.now(KYIV_ZONE_ID));
        question.setStatus(Question.Status.CLOSED);
        question.setAdminId(user.getId());
        question.setAnswer(text);
        String answerText = String.format("Your question: \"%s\"%sAnswer from admin %s %s: \"%s\"",
                question.getText(), System.lineSeparator(),
                user.getFirstName(), user.getLastName(), text);
        SendMessage sendMessage = SendMessage.builder()
                .chatId(question.getChatId().toString())
                .text(answerText).build();
        messageSender.sendMessage(sendMessage);
        chatLogService.log(text, question.getChatId(), ChatLog.Type.ADMINS_ANSWER);
        return question;
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found question with id: " + id)
        );
    }
}
