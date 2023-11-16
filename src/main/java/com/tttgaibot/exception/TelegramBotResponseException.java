package com.tttgaibot.exception;

public class TelegramBotResponseException extends RuntimeException {
    public TelegramBotResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
