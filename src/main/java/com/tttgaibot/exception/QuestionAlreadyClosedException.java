package com.tttgaibot.exception;

public class QuestionAlreadyClosedException extends RuntimeException {
    public QuestionAlreadyClosedException(String message) {
        super(message);
    }
}
