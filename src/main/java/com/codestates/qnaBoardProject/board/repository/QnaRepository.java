package com.codestates.qnaBoardProject.board.repository;

import com.codestates.qnaBoardProject.board.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
}
