package com.codestates.qnaBoardProject.member.controller;

import com.codestates.qnaBoardProject.member.dto.MemberPatchDto;
import com.codestates.qnaBoardProject.member.dto.MemberPostDto;
import com.codestates.qnaBoardProject.member.entity.Member;
import com.codestates.qnaBoardProject.member.mapper.MemberMapper;
import com.codestates.qnaBoardProject.member.service.MemberService;
import com.codestates.qnaBoardProject.response.MultiResponseDto;
import com.codestates.qnaBoardProject.response.SingleResponseDto;
import com.codestates.qnaBoardProject.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v11/members")
@Validated
@Slf4j
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/v11/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberService.createMember(mapper.memberPostDtoToMember(memberPostDto));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));
        return new ResponseEntity<>(new SingleResponseDto<>(
                mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
    }
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member findMember = memberService.findMember(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(
                mapper.memberToMemberResponseDto(findMember)), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        // 전체 멤버 검색 로직 구현 (페이지네이션)
        Page<Member> memberPage = memberService.findMembers(page - 1, size);
        List<Member> members = memberPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(
                mapper.membersToMemberResponseDtos(members), memberPage), HttpStatus.OK);
    }
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
