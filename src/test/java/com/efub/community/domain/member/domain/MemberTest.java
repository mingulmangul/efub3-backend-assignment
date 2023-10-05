package com.efub.community.domain.member.domain;

import static com.efub.community.domain.member.domain.MemberStatus.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

	String email = "test@gmail.com";
	String nickname = "테스트계정";
	String encodedPassword = "encodedPassword";
	String university = "이화여자대학교";
	Integer studentNo = 1989001;
	MemberStatus unregistered = UNREGISTERED;
	MemberStatus registered = REGISTERED;

	@Test
	@DisplayName("계정을 생성합니다.")
	void create() {
		// given
		// when
		Member member = createMember();
		// then
		verifyMember(member);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getStatus()).isEqualTo(registered);
	}

	@Test
	@DisplayName("계정 생성 시 잘못된 형식의 이메일을 입력하면, 예외가 발생합니다.")
	void createWithInvalidEmail() {
		String invalidEmail = "email";
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(() -> Member.builder()
				.email(invalidEmail)
				.nickname(nickname)
				.encodedPassword(encodedPassword)
				.university(university)
				.studentNo(studentNo)
				.build());
	}

	@Test
	@DisplayName("계정을 업데이트합니다.")
	void update() {
		// given
		String newNickname = "새테스트계정";
		Member member = createMember();
		// when
		member.updateMember(newNickname);
		// then
		verifyMember(member);
		assertThat(member.getNickname()).isEqualTo(newNickname);
		assertThat(member.getStatus()).isEqualTo(registered);
	}

	@Test
	@DisplayName("비활성화된 계정을 업데이트하면, 예외가 발생합니다.")
	void updateUnregisteredMember() {
		Member member = createMember();
		member.withdraw();
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(() -> member.updateMember("새 닉네임"));
	}

	@Test
	@DisplayName("계정을 삭제하면, status가 UNREGISTERED로 변경됩니다.")
	void withdraw() {
		// given
		Member member = createMember();
		// when
		member.withdraw();
		// then
		verifyMember(member);
		assertThat(member.getNickname()).isEqualTo(nickname);
		assertThat(member.getStatus()).isEqualTo(unregistered);
	}
	
	@Test
	@DisplayName("비활성화된 계정을 삭제하면, 예외가 발생합니다.")
	void withdrawUnregisteredMember() {
		Member member = createMember();
		member.withdraw();
		Assertions.assertThatIllegalArgumentException()
			.isThrownBy(member::withdraw);
	}

	private Member createMember() {
		return Member.builder()
			.email(email)
			.nickname(nickname)
			.encodedPassword(encodedPassword)
			.university(university)
			.studentNo(studentNo)
			.build();
	}

	private void verifyMember(Member member) {
		assertThat(member.getEmail()).isEqualTo(email);
		assertThat(member.getEncodedPassword()).isEqualTo(encodedPassword);
		assertThat(member.getUniversity()).isEqualTo(university);
		assertThat(member.getStudentNo()).isEqualTo(studentNo);
	}
}
