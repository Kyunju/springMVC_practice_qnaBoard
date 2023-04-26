package com.codestates.qnaBoardProject.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v11/qnas")
@Validated
@Slf4j
public class QnaBoardController {
    @PostMapping
    public ResponseEntity postQnA(@RequestBody QnaPostDto qnaPostDto){
        // TODO : 내부 로직 구현
        return null;
    }
    @PatchMapping("/{qna-id}")
    public ResponseEntity patchQna(@PathVariable("qna-id") @Positive long qnaId,
                                   @RequestBody QnaPatchDto qnaPatchDto) {
        // TODO : 내부 로직 구현
        return null;
    }
    @GetMapping("/{qna-id}")
    public ResponseEntity getQna(@PathVariable("qna-id") @Positive long qnaId) {
        // TODO : 내부 로직 구현 , 하나의 질문만 찾아 리턴
        return null;
    }
    @GetMapping
    public ResponseEntity getQnas(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size) {
        // TODO : 내부 로직 구현, 페이지네이션 된 질문들 리턴
        return null;
    }
    @DeleteMapping("/{qna-id}")
    public void deleteQna(@PathVariable("qna-id") @Positive long qnaId) {
        // TODO : 삭제 로직 구현
    }
}
