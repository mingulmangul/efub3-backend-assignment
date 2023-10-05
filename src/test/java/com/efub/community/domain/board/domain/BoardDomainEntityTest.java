package com.efub.community.domain.board.domain;

import org.junit.jupiter.api.BeforeEach;

import com.efub.community.domain.member.domain.Member;

public abstract class BoardDomainEntityTest {

	Member member1;
	Member member2;
	String description = "펍이들을 위한 게시판입니다.";
	String newDescription = "퍼비들을 위한 게시판입니다.";
	Board board;
	Post post1;
	Post post2;
	String postContent = "올바른 게시글의 내용입니다. (5자 초과)";
	Comment comment;
	String commentContent = "올바른 댓글의 내용입니다. (5자 초과)";

	@BeforeEach
	void setUp() {
		member1 = Member.builder()
			.email("member1@email.com")
			.nickname("member1")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(1)
			.build();
		member2 = Member.builder()
			.email("member2@email.com")
			.nickname("member2")
			.encodedPassword("encodedPassword")
			.university("university")
			.studentNo(2)
			.build();
		board = Board.builder()
			.name("퍼비들의 게시판")
			.description(description)
			.owner(member1)
			.build();
		post1 = Post.builder()
			.content(postContent)
			.writer(member1)
			.anonymous(true)
			.board(board)
			.build();
		post2 = Post.builder()
			.content(postContent)
			.writer(member2)
			.anonymous(false)
			.board(board)
			.build();
		comment = Comment.builder()
			.content(commentContent)
			.writer(member1)
			.post(post1)
			.anonymous(true)
			.build();
	}

}
