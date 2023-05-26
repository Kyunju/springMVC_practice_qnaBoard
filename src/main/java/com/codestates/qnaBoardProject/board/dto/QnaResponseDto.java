package com.codestates.qnaBoardProject.board.dto;

import com.codestates.qnaBoardProject.board.entity.QnaQuestion;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Builder
@Getter
public class QnaResponseDto {
    private long qnaId;
    private String title;
    private String body;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private QnaQuestion.QnaStatus qnaStatus;
    private QnaQuestion.QnaVisibility qnaVisibility;

    public String  getQnaStatus() {
        return qnaStatus.getStatus();
    }

    public String  getQnaVisibility() {
        return qnaVisibility.getVisibility();
    }
    // TODO : 답변이 있다면 답변도 리턴

}
