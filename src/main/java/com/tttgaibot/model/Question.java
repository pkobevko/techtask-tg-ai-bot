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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "creation_time", columnDefinition = "timestamp with time zone")
    private ZonedDateTime creationTime;
    @Column(name = "closure_time", columnDefinition = "timestamp with time zone")
    private ZonedDateTime closureTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "admin_id")
    private Long adminId;
    private String answer;

    public Question() {
    }

    public enum Status {
        ACTIVE,
        CLOSED
    }
}
