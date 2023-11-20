package com.tttgaibot.strategy.impl;

import com.tttgaibot.strategy.UpdateHandler;
import com.tttgaibot.strategy.UpdateHandlerStrategy;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateHandlerStrategyImpl implements UpdateHandlerStrategy {
    private final List<UpdateHandler> updateHandlers;

    @Override
    public UpdateHandler getUpdateHandler(Update update) {
        return updateHandlers.stream()
                .filter(updateHandler -> updateHandler.isApplicable(update))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find handler for update: "
                        + update));
    }
}
