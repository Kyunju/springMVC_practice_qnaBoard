package com.codestates.qnaBoardProject.board.dto;

import com.codestates.qnaBoardProject.board.entity.Qna;
import com.codestates.qnaBoardProject.member.entity.Member;
import com.codestates.qnaBoardProject.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    @NotSpace(message = "질문 상태는 정해진 규범을 따라야 합니다.")
    private Qna.QnaStatus qnaStatus;
    @NotSpace(message = "공개, 비공개 상태로만 전환이 가능합니다.")
    private Qna.QnaVisibility qnaVisibility;

    public void setQnaId(long qnaId) {
        this.qnaId = qnaId;
    }
    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
