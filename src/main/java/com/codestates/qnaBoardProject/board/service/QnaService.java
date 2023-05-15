package com.codestates.qnaBoardProject.board.service;

import com.codestates.qnaBoardProject.admin.Admin;
import com.codestates.qnaBoardProject.board.entity.Qna;
import com.codestates.qnaBoardProject.board.repository.QnaRepository;
import com.codestates.qnaBoardProject.exception.BusinessLogicException;
import com.codestates.qnaBoardProject.exception.ExceptionCode;
import com.codestates.qnaBoardProject.member.entity.Member;
import com.codestates.qnaBoardProject.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QnaService {
    private final QnaRepository qnaRepository;
    private final MemberService memberService;
    private final Admin admin;

    public QnaService(QnaRepository qnaRepository, MemberService memberService, Admin admin) {
        this.qnaRepository = qnaRepository;
        this.memberService = memberService;
        this.admin = admin;
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
        // 1. 질문을 작성한 회원 또는 관리자가 맞는지 확인
        Qna checkedQna = checkVerifiedQnaByMember(qna);

        // 2.  Question_Answered 상태의 질문은 수정 할 수 없다.
        if (checkedQna.getQnaStatus().getStepNumber() == 2)
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);

        // 3. QnaStatus 수정을 Question_Answered로 관리자 이외가 바꾸려 한다면 에러
        Optional.ofNullable(qna.getTitle())
                .ifPresent(title -> checkedQna.setTitle(title));
        Optional.ofNullable(qna.getBody())
                .ifPresent(body -> checkedQna.setBody(body));
        Optional.ofNullable(qna.getQnaStatus())
                .ifPresent(qnaStatus -> {
                    if (qnaStatus.getStepNumber() == 2) {
                        Member findMember = memberService.findMember(qna.getMember().getMemberId());
                        if (!findMember.getEmail().equals(admin.getEMAIL()))
                            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);
                    }
                    if (qnaStatus.getStepNumber() == 3) {
                        throw new BusinessLogicException(ExceptionCode.QNA_NOT_FOUND);
                    }
                    checkedQna.setQnaStatus(qnaStatus);
                });
        Optional.ofNullable(qna.getQnaVisibility())
                .ifPresent(qnaVisibility -> checkedQna.setQnaVisibility(qnaVisibility));
        checkedQna.setModifiedAt(LocalDateTime.now());

        return qnaRepository.save(checkedQna);
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
        Qna findQna = findVerifiedByQnaId(qnaId);

    }

    public Qna findVerifiedByQnaId(long qnaId) {
        Optional<Qna> optionalQna = qnaRepository.findById(qnaId);
        Qna findQna = optionalQna.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QNA_NOT_FOUND)
        );
        return findQna;
    }

    public Qna checkVerifiedQnaByMember(Qna qna) {
        Qna findQna = findVerifiedByQnaId(qna.getQnaId());
        if (!(findQna.getMember().getMemberId() == qna.getMember().getMemberId() ||
                memberService.findVerifiedMember(qna.getMember().getMemberId())
                        .getEmail().equals(admin.getEMAIL())))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);
        return findQna;
    }
}
