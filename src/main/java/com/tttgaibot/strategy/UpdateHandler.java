package com.tttgaibot.strategy;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    void handle(Update update);

    boolean isApplicable(Update update);
}
