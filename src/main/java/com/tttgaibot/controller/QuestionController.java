package com.tttgaibot.controller;

import com.tttgaibot.dto.request.AnswerRequestDto;
import com.tttgaibot.model.Question;
import com.tttgaibot.model.User;
import com.tttgaibot.service.QuestionService;
import com.tttgaibot.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestionsByStatus(
            @RequestParam Question.Status status) {
        List<Question> allByStatus = questionService.getAllByStatus(status);
        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
    }

    @PostMapping("/{id}/answer")
    public ResponseEntity<Question> answerTheQuestion(
            Authentication authentication,
            @PathVariable("id") Long questionId,
            @RequestBody AnswerRequestDto answerRequestDto) {
        User user = userService.getByEmail(authentication.getName()).get();
        Question question = questionService.answer(questionId, answerRequestDto.getText(), user);
        return new ResponseEntity<>(question, HttpStatus.ACCEPTED);
    }
}
