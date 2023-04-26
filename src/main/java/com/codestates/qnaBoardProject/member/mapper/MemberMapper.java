package com.codestates.qnaBoardProject.member.mapper;

import com.codestates.qnaBoardProject.member.dto.MemberPatchDto;
import com.codestates.qnaBoardProject.member.dto.MemberResponseDto;
import com.codestates.qnaBoardProject.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPatchDto memberPatchDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
