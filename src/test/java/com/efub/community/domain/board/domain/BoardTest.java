package com.efub.community.domain.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.efub.community.domain.member.domain.Member;

class BoardTest {

	Member owner;
	Member newOwner;
	String description = "펍이들을 위한 게시판입니다.";
	String newDescription = "퍼비들을 위한 게시판입니다.";
	Board board;

	@BeforeEach
	void setUp() {
		owner = Member.builder()
			.email("owner@email.com")
			.nickname("owner")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(1)
			.build();
		newOwner = Member.builder()
			.email("newOwner@email.com")
			.nickname("newOwner")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(2)
			.build();
		board = Board.builder()
			.name("퍼비들의 게시판")
			.description(description)
			.owner(owner)
			.build();
	}

	@Test
	@DisplayName("보드를 업데이트합니다.")
	void updateBoard() {
		// given
		// when
		assertThat(board.getDescription()).isEqualTo(description);
		board.updateBoard(newOwner, newDescription);
		// then
		assertThat(board.getDescription()).isEqualTo(newDescription);
	}

	@Test
	@DisplayName("보드 생성 시 이름이 30자를 초과하면, 예외가 발생합니다.")
	void longName() {
		String longName = "1234567890123456789012345678901234567890";
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Board.builder()
				.name(longName)
				.description(description)
				.owner(owner)
				.build());
	}

	@Test
	@DisplayName("보드 생성 시 이름이 3자 미만이면, 예외가 발생합니다.")
	void shortName() {
		String shortName = "0";
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Board.builder()
				.name(shortName)
				.description(description)
				.owner(owner)
				.build());
	}
}
