package com.codestates.qnaBoardProject.member.mapper;

import com.codestates.qnaBoardProject.member.dto.MemberPatchDto;
import com.codestates.qnaBoardProject.member.dto.MemberPostDto;
import com.codestates.qnaBoardProject.member.dto.MemberResponseDto;
import com.codestates.qnaBoardProject.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
