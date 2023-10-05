package com.efub.community.domain.board.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.efub.community.domain.member.domain.Member;

class PostTest {

	Member member;
	Post post;
	Board board;
	String content = "올바른 게시글의 내용입니다. (5자 초과)";

	@BeforeEach
	void setUp() {
		member = Member.builder()
			.email("writer@email.com")
			.nickname("writer")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(1)
			.build();
		board = Board.builder()
			.name("퍼비들의 게시판")
			.description("펍이들을 위한 게시판입니다.")
			.owner(member)
			.build();
		post = Post.builder()
			.content(content)
			.writer(member)
			.anonymous(true)
			.board(board)
			.build();
	}

	@Test
	@DisplayName("포스트를 업데이트합니다.")
	void update() {
		// given
		String newContent = "새로 업데이트되는 게시글의 내용입니다.";
		// when
		assertThat(post.getContent()).isEqualTo(content);
		post.updatePost(newContent);
		// then
		assertThat(post.getContent()).isEqualTo(newContent);
	}

	@Test
	@DisplayName("포스트 생성 시 게시글의 내용이 5자 미만이면, 예외가 발생합니다.")
	void createShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Post.builder()
				.content("게시글")
				.writer(member)
				.anonymous(true)
				.board(board)
				.build());
	}

	@Test
	@DisplayName("포스트 업데이트 시 게시글의 내용이 5자 미만이면, 예외가 발생합니다.")
	void updateShortPost() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> post.updatePost("게시글"));
	}

}
