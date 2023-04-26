package com.codestates.qnaBoardProject.member.controller;

import com.codestates.qnaBoardProject.member.dto.MemberPatchDto;
import com.codestates.qnaBoardProject.member.dto.MemberPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v11/members")
@Validated
@Slf4j
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        // TODO : 추가 로직 구현
        return null;
    }
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        // TODO : 수정 로직 구현
        return null;
    }
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        // TODO : 단일 멤버 검색 로직 구현
        return null;
    }
    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        // TODO : 전체 멤버 검색 로직 구현 (페이지네이션)
        return null;
    }
    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") @Positive long memberId) {
        // TODO : 삭제 로직 구현


    }
}
