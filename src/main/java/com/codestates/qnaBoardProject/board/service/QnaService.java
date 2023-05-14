package com.codestates.qnaBoardProject.board.service;

import com.codestates.qnaBoardProject.board.entity.Qna;
import com.codestates.qnaBoardProject.board.repository.QnaRepository;
import com.codestates.qnaBoardProject.exception.BusinessLogicException;
import com.codestates.qnaBoardProject.exception.ExceptionCode;
import com.codestates.qnaBoardProject.member.entity.Member;
import com.codestates.qnaBoardProject.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QnaService {
    private final QnaRepository qnaRepository;
    private final MemberService memberService;

    public QnaService(QnaRepository qnaRepository, MemberService memberService) {
        this.qnaRepository = qnaRepository;
        this.memberService = memberService;
    }

    public Qna createQna(Qna qna) {
        // 회원만 Qna 등록가능
        // 회원이 존재하는지 확인
        Member findMember = memberService.findVerifiedMember(qna.getMember().getMemberId());
        qna.setMember(findMember);
        return qnaRepository.save(qna);
    }

    public Qna updateQna(Qna qna) {
        // TODO: 비지니스 로직 구현
        return qna;
    }

    public Qna findQna(long qnaId) {
        // TODO: 비지니스 로직 구현
        return new Qna();
    }

    public Page<Qna> findQnas(int page, int size) {
        // TODO: 비지니스 로직 구현
        return null;
    }

    public void deleteQna(long qnaId, long memberId) {
        // 1. 질문을 등록한 회원만 삭제가능
        // 2. 질문 삭제시 실제로 데이터 삭제가 아닌 질문의 상태 값을 변경
        // 3. 이미 삭제상태의 질문은 삭제 할 수 없음

        // 유효한 질문인지 검증
        Qna findQna = findVerifiedQna(qnaId);

    }

    public Qna findVerifiedQna(long qnaId) {
        Optional<Qna> optionalQna = qnaRepository.findById(qnaId);
        Qna findQna = optionalQna.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QNA_NOT_FOUND)
        );
        return findQna;
    }
}
