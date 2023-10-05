package com.efub.community.domain.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest extends BoardDomainEntityTest {

	@Test
	@DisplayName("보드를 업데이트합니다.")
	void updateBoard() {
		// given
		// when
		assertThat(board.getDescription()).isEqualTo(description);
		board.updateBoard(member2, newDescription);
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
				.owner(member1)
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
				.owner(member1)
				.build());
	}
}
