package com.tttgaibot.strategy.impl;

import com.tttgaibot.strategy.UpdateHandler;
import com.tttgaibot.strategy.UpdateHandlerStrategy;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

@Component
public class UpdateHandlerStrategyImpl implements UpdateHandlerStrategy {
    private final List<UpdateHandler> updateHandlers;

    public UpdateHandlerStrategyImpl(List<UpdateHandler> updateHandlers) {
        this.updateHandlers = updateHandlers;
    }

    @Override
    public UpdateHandler getUpdateHandler(Update update) {
        return updateHandlers.stream()
                .filter(updateHandler -> updateHandler.isApplicable(update))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find handler for update: "
                        + update));
    }
}
