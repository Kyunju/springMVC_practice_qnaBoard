package com.codestates.qnaBoardProject.board.entity;

import com.codestates.qnaBoardProject.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaStatus qnaStatus = QnaStatus.QUESTION_REGISTERED;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // TODO: 답변 엔티티랑 연관관계 맵핑

    public enum QnaStatus {
        QUESTION_REGISTERED("질문 등록"),
        QUESTION_ANSWERED("답변 완료"),
        QUESTION_DELETED("질문 삭제"),
        QUESTION_DEACTIVATED("질문 비활성화");
        private String status;

        QnaStatus(String status) {
            this.status = status;
        }
    }

}
