package com.efub.community.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtToken {

	private static final String TYPE = "Bearer";
	private final String accessToken;
	private final String refreshToken;
}
