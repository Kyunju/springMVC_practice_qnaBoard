package com.codestates.qnaBoardProject.member.service;

import com.codestates.qnaBoardProject.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public Member createMember(Member member) {
        // TODO: 기존 멤버가 존재하는지 조회

        // 기존멤버가 존재하지 않는다면 데이터베이스에 추가
        return null;
    }
    public Member updateMember(Member member) {
        // TODO: 기존 멤버 가져오기

        // null 이 아닌 부분만 가져온 멤버에 수정

        // 데이터베이스에 수정된 Member 엔티티 저장
        return null;
    }

    public Member findMember(long memberId) {
        // TODO: memberId를 통해서 member 가져오기

        // 가져온 멤버를 리턴
        return null;
    }

    public void deleteMember(long memberId) {
        // TODO : memberID를 통해서 멤버 가져오기
        // 멤버가 존재하면 해당 멤버를 데이터베이스에서 삭제
    }
}
