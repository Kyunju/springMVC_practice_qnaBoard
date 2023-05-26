package com.codestates.qnaBoardProject.board.controller;

import com.codestates.qnaBoardProject.board.dto.QnaPatchDto;
import com.codestates.qnaBoardProject.board.dto.QnaPostDto;
import com.codestates.qnaBoardProject.board.dto.QnaResponseDto;
import com.codestates.qnaBoardProject.board.entity.QnaQuestion;
import com.codestates.qnaBoardProject.board.mapper.QnaMapper;
import com.codestates.qnaBoardProject.board.service.QnaService;
import com.codestates.qnaBoardProject.response.SingleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v11/qnas")
@Validated
@Slf4j
public class QnaBoardController {
    private final QnaMapper mapper;
    private final QnaService qnaService;

    public QnaBoardController(QnaMapper mapper, QnaService qnaService) {
        this.mapper = mapper;
        this.qnaService = qnaService;
    }

    @PostMapping
    public ResponseEntity postQnA(@RequestBody QnaPostDto qnaPostDto){
        // TODO : 내부 로직 구현
        QnaQuestion qnaQuestion = qnaService.createQna(mapper.qnaPostDtoToQnaQuestion(qnaPostDto));

        QnaResponseDto response = mapper.qnaQuestionToQnaResponseDto(qnaQuestion);
//        URI location = UriCreator.createUri("/v11/qnas/", 1L);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PatchMapping("/{qna-id}")
    public ResponseEntity patchQna(@PathVariable("qna-id") @Positive long qnaId,
                                   @RequestBody QnaPatchDto qnaPatchDto) {
        // TODO : 내부 로직 구현
        qnaPatchDto.setQnaId(qnaId);
        QnaQuestion updatedQnaQuestion = qnaService.updateQna(mapper.qnaPatchDtoToQnaQuestion(qnaPatchDto));
        QnaResponseDto responseDto = mapper.qnaQuestionToQnaResponseDto(updatedQnaQuestion);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/{qna-id}")
    public ResponseEntity getQna(@PathVariable("qna-id") @Positive long qnaId,
                                 @Positive @RequestParam long memberId) {
        // TODO : 내부 로직 구현 , 하나의 질문만 찾아 리턴
        QnaQuestion findQnaQuestion = qnaService.findQna(qnaId, memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(
                mapper.qnaQuestionToQnaResponseDto(findQnaQuestion)), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getQnas(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
        // TODO : 내부 로직 구현, 페이지네이션 된 질문들 리턴
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{qna-id}")
    public ResponseEntity deleteQna(@PathVariable("qna-id") @Positive long qnaId) {
        // TODO : 내부 로직 구현

        return ResponseEntity.noContent().build();
    }
}
