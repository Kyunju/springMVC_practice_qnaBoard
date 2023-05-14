package com.codestates.qnaBoardProject.board.dto;

import com.codestates.qnaBoardProject.board.entity.Qna;
import com.codestates.qnaBoardProject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class QnaPostDto {
    @NotBlank(message = "회원은 공백이 아니어야 합니다.")
    private long memberId;
    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    private String title;
    @NotBlank(message = "질문은 공백이 아니어야 합니다.")
    private String body;
    private Qna.QnaVisibility visibility;
    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
