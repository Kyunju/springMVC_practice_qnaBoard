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
public class QnaQuestion {
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaVisibility qnaVisibility = QnaVisibility.QUESTION_PUBLIC;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // TODO: 답변 엔티티랑 연관관계 맵핑
    @OneToOne(mappedBy = "qnaQuestion")
    private QnaAnswered qnaAnswered;

    public void setQnaAnswered(QnaAnswered qnaAnswered) {
        this.qnaAnswered = qnaAnswered;
        if (qnaAnswered.getQnaQuestion() != this){
            qnaAnswered.setQnaQuestion(this);
        }
    }

    public enum QnaStatus {
        QUESTION_REGISTERED(1, "질문 등록"),
        QUESTION_ANSWERED(2, "답변 완료"),
        QUESTION_DELETED(3, "질문 삭제"),
        QUESTION_DEACTIVATED(4, "질문 비활성화");
        @Getter
        private int stepNumber;
        @Getter
        private String status;

        QnaStatus(int stepNumber, String status) {
            this.stepNumber = stepNumber;
            this.status = status;
        }
    }

    public enum QnaVisibility {
        QUESTION_PUBLIC("공개"),
        QUESTION_SECRET("비공개");
        @Getter
        private String visibility;

        QnaVisibility(String visibility) {
            this.visibility = visibility;
        }
    }

}
