package com.efub.community.domain.member.domain;

import static com.efub.community.domain.member.domain.MemberStatus.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.efub.community.global.common.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id", updatable = false)
	private Long memberId;

	@Column(nullable = false, length = 60)
	private String email;

	@Column(nullable = false)
	private String encodedPassword;

	@Column(nullable = false, length = 16)
	private String nickname;

	@Column(nullable = false, updatable = false)
	private Integer studentNo;

	@Column(nullable = false, updatable = false)
	private String university;

	@Enumerated(EnumType.STRING)
	private MemberStatus status;

	@Builder
	public Member(String email, String encodedPassword, String nickname, Integer studentNo, String university) {
		verifyEmail(email);
		this.email = email;
		this.encodedPassword = encodedPassword;
		this.nickname = nickname;
		this.studentNo = studentNo;
		this.university = university;
		this.status = REGISTERED;
	}

	private void verifyEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		if (!email.matches(regex)) {
			throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
		}
	}

	public void updateMember(String nickname) {
		verifyStatus();
		this.nickname = nickname;
	}

	public void withdraw() {
		verifyStatus();
		this.status = UNREGISTERED;
	}

	private void verifyStatus() {
		if (status == UNREGISTERED) {
			throw new IllegalArgumentException("비활성화된 사용자입니다.");
		}
	}
}
