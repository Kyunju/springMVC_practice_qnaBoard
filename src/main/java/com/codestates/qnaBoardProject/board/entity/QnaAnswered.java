package com.codestates.qnaBoardProject.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QnaAnswered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime modifyAt = LocalDateTime.now();
    @OneToOne
    @JoinColumn(name = "QNA_ID")
    private QnaQuestion qnaQuestion;

    public void setQnaQuestion(QnaQuestion qnaQuestion) {
        this.qnaQuestion = qnaQuestion;
        if (qnaQuestion.getQnaAnswered() != this) {
            qnaQuestion.setQnaAnswered(this);
        }
    }
}
