package com.efub.community.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efub.community.domain.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Optional<Board> findById(Long boardId);

	List<Board> findAllByOrderByBoardIdDesc();

	boolean existsByName(String name);
}
