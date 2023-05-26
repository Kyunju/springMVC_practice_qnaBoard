package com.codestates.qnaBoardProject.board.mapper;

import com.codestates.qnaBoardProject.board.dto.QnaPatchDto;
import com.codestates.qnaBoardProject.board.dto.QnaPostDto;
import com.codestates.qnaBoardProject.board.dto.QnaResponseDto;
import com.codestates.qnaBoardProject.board.entity.QnaQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QnaMapper {
    QnaQuestion qnaPostDtoToQnaQuestion(QnaPostDto qnaPostDto);
    QnaQuestion qnaPatchDtoToQnaQuestion(QnaPatchDto qnaPatchDto);
    @Mapping(target = "createdBy", source = "member.name")
    QnaResponseDto qnaQuestionToQnaResponseDto(QnaQuestion qnaQuestion);
}
