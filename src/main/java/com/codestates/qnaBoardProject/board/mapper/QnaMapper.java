package com.codestates.qnaBoardProject.board.mapper;

import com.codestates.qnaBoardProject.board.dto.QnaPatchDto;
import com.codestates.qnaBoardProject.board.dto.QnaPostDto;
import com.codestates.qnaBoardProject.board.dto.QnaResponseDto;
import com.codestates.qnaBoardProject.board.entity.Qna;
import org.mapstruct.Mapper;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface QnaMapper {
    Qna qnaPostDtoToQna(QnaPostDto qnaPostDto);
    Qna qnaPatchDtoToQna(QnaPatchDto qnaPatchDto);
    QnaResponseDto qnaToQnaResponseDto(Qna qna);
}
