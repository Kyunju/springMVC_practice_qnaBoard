package com.codestates.qnaBoardProject.member.dto;

import com.codestates.qnaBoardProject.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
    private Member.MemberStatus memberStatus;
    public String getMemberStatus() {return memberStatus.getStatus();}
}
