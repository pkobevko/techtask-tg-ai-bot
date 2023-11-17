package com.tttgaibot.repository;

import com.tttgaibot.model.ChatLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLogRepository extends CrudRepository<ChatLog, Long> {
}
