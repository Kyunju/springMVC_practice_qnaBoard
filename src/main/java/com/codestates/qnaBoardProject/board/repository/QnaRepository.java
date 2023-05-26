package com.codestates.qnaBoardProject.board.repository;

import com.codestates.qnaBoardProject.board.entity.QnaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnaQuestion, Long> {
}
