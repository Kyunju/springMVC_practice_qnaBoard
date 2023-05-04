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
public class Reply {
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
    private Qna qna;

    public void setQna(Qna qna) {
        this.qna = qna;
        if (qna.getReply() != this) {
            qna.setReply(this);
        }
    }
}
