package com.efub.community.domain.member.auth.dto.response;

import lombok.Getter;

@Getter
public class JwtResponseDto {

	private final String type = "Bearer";
	private final String accessToken;

	public JwtResponseDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
