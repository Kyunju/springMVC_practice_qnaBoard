package com.codestates.qnaBoardProject.board.service;

import com.codestates.qnaBoardProject.admin.Admin;
import com.codestates.qnaBoardProject.board.entity.QnaQuestion;
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

    public QnaQuestion createQna(QnaQuestion qnaQuestion) {
        // 회원만 Qna 등록가능
        // 회원이 존재하는지 확인
        Member findMember = memberService.findVerifiedMember(qnaQuestion.getMember().getMemberId());
        qnaQuestion.setMember(findMember);
        return qnaRepository.save(qnaQuestion);
    }

    public QnaQuestion updateQna(QnaQuestion qnaQuestion) {
        // TODO: 비지니스 로직 구현
        // 1. 질문을 작성한 회원 또는 관리자가 맞는지 확인
        QnaQuestion checkedQnaQuestion = checkVerifiedQnaByMember(qnaQuestion);

        // 2.  Question_Answered 상태의 질문은 수정 할 수 없다.
        if (checkedQnaQuestion.getQnaStatus().getStepNumber() == 2)
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);

        // 3. QnaStatus 수정을 Question_Answered로 관리자 이외가 바꾸려 한다면 에러
        Optional.ofNullable(qnaQuestion.getTitle())
                .ifPresent(title -> checkedQnaQuestion.setTitle(title));
        Optional.ofNullable(qnaQuestion.getBody())
                .ifPresent(body -> checkedQnaQuestion.setBody(body));
        Optional.ofNullable(qnaQuestion.getQnaStatus())
                .ifPresent(qnaStatus -> {
                    if (qnaStatus.getStepNumber() == 2) {
                        Member findMember = memberService.findMember(qnaQuestion.getMember().getMemberId());
                        if (!findMember.getEmail().equals(admin.getEMAIL()))
                            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);
                    }
                    if (qnaStatus.getStepNumber() == 3) {
                        throw new BusinessLogicException(ExceptionCode.QNA_NOT_FOUND);
                    }
                    checkedQnaQuestion.setQnaStatus(qnaStatus);
                });
        Optional.ofNullable(qnaQuestion.getQnaVisibility())
                .ifPresent(qnaVisibility -> checkedQnaQuestion.setQnaVisibility(qnaVisibility));
        checkedQnaQuestion.setModifiedAt(LocalDateTime.now());

        return qnaRepository.save(checkedQnaQuestion);
    }

    public QnaQuestion findQna(long qnaId, long memberId) {
        // TODO: 비지니스 로직 구현
        // 질문의 상태가 시크릿이라면 memberId가 일치해야지만 조회 가능
        QnaQuestion findQnaQuestion = findVerifiedByQnaId(qnaId);
        if (findQnaQuestion.getQnaVisibility().getVisibility().equals("비공개")) {
            if (!(findQnaQuestion.getMember().getMemberId() == memberId))
                throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_GET_QUESTION);
        }

        return findQnaQuestion;
    }

    public Page<QnaQuestion> findQnas(int page, int size) {
        // TODO: 비지니스 로직 구현
        return null;
    }

    public void deleteQna(long qnaId, long memberId) {
        // 1. 질문을 등록한 회원만 삭제가능
        // 2. 질문 삭제시 실제로 데이터 삭제가 아닌 질문의 상태 값을 변경
        // 3. 이미 삭제상태의 질문은 삭제 할 수 없음

        // 유효한 질문인지 검증
        QnaQuestion findQnaQuestion = findVerifiedByQnaId(qnaId);

    }

    public QnaQuestion findVerifiedByQnaId(long qnaId) {
        Optional<QnaQuestion> optionalQna = qnaRepository.findById(qnaId);
        QnaQuestion findQnaQuestion = optionalQna.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QNA_NOT_FOUND)
        );
        return findQnaQuestion;
    }

    public QnaQuestion checkVerifiedQnaByMember(QnaQuestion qnaQuestion) {
        QnaQuestion findQnaQuestion = findVerifiedByQnaId(qnaQuestion.getQnaId());
        if (!(findQnaQuestion.getMember().getMemberId() == qnaQuestion.getMember().getMemberId() ||
                memberService.findVerifiedMember(qnaQuestion.getMember().getMemberId())
                        .getEmail().equals(admin.getEMAIL())))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_MODIFY_QUESTION);
        return findQnaQuestion;
    }
}
