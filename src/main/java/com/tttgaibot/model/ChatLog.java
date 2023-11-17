package com.tttgaibot.model;

import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "chats_logs")
public class ChatLog {
    private static final int MAXIMUM_CHATGPT_ANSWER_LENGTH = 2048;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = MAXIMUM_CHATGPT_ANSWER_LENGTH)
    private String text;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "timestamp", columnDefinition = "timestamp with time zone")
    private ZonedDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        QUESTION_TO_AI,
        QUESTION_TO_ADMIN,
        ADMINS_ANSWER,
        AIS_ANSWER
    }
}
