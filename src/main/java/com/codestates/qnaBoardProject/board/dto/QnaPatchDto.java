package com.codestates.qnaBoardProject.board.dto;

import com.codestates.qnaBoardProject.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QnaPatchDto {
    private long qnaId;
    @NotBlank(message = "회원정보는 공백이 아니어야 합니다.")
    private long memberId;
    @NotSpace(message = "제목은 공백이 아니어야 합니다.")
    private String title;
    @NotSpace(message = "질문은 공백이 아니어야 합니다.")
    private String body;

    public void setQnaId(long qnaId) {
        this.qnaId = qnaId;
    }
}
