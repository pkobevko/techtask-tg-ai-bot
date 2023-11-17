package com.tttgaibot.strategy;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandlerStrategy {
    UpdateHandler getUpdateHandler(Update update);
}
