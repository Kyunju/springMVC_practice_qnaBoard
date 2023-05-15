package com.codestates.qnaBoardProject.member.service;

import com.codestates.qnaBoardProject.exception.BusinessLogicException;
import com.codestates.qnaBoardProject.exception.ExceptionCode;
import com.codestates.qnaBoardProject.member.entity.Member;
import com.codestates.qnaBoardProject.member.repository.MemberRepository;
import com.codestates.qnaBoardProject.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // 기존 멤버가 존재하는지 조회
        verifyExistEmail(member.getEmail());

        // stamp 추가
        initStamp(member);

        // 기존멤버가 존재하지 않는다면 데이터베이스에 추가
        return memberRepository.save(member);
    }
    public Member updateMember(Member member) {
        // 기존 멤버 가져오기
        Member findMember = findVerifiedMember(member.getMemberId());

        // null 이 아닌 부분만 가져온 멤버에 수정
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));
        findMember.setModifiedAt(LocalDateTime.now());

        // 데이터베이스에 수정된 Member 엔티티 저장
        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {
        // memberId를 통해서 member를 리턴

        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
       return memberRepository.findAll(PageRequest.of(page,size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        // memberID를 통해서 멤버 가져오기
        Member findMember = findVerifiedMember(memberId);
        // 멤버가 존재하면 해당 멤버를 데이터베이스에서 삭제
        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(
                        () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
    private void initStamp(Member member) {
        member.setStamp(new Stamp());
    }
}
