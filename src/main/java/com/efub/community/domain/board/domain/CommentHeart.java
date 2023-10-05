package com.efub.community.domain.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.efub.community.domain.member.domain.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_like_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "댓글은 필수로 입력되어야 합니다.")
	@JoinColumn(name = "comment_id", updatable = false)
	private Comment comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "작성자는 필수로 입력되어야 합니다.")
	@JoinColumn(name = "member_id", updatable = false)
	private Member writer;

	@Builder
	public CommentHeart(Comment comment, Member member) {
		this.comment = comment;
		this.writer = member;
	}
}
