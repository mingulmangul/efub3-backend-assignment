package com.efub.community.domain.member.auth.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efub.community.domain.member.auth.dto.request.LoginRequestDto;
import com.efub.community.domain.member.auth.dto.request.SignUpRequestDto;
import com.efub.community.domain.member.auth.dto.response.SignUpResponseDto;
import com.efub.community.domain.member.auth.service.AuthService;
import com.efub.community.domain.member.domain.Member;
import com.efub.community.global.jwt.JwtToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	@ResponseStatus(value = HttpStatus.CREATED)
	public SignUpResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto) {
		Member member = authService.signUp(requestDto);
		return new SignUpResponseDto(member);
	}

	@PostMapping("/login")
	@ResponseStatus(value = HttpStatus.OK)
	public JwtToken login(@RequestBody final LoginRequestDto requestDto) {
		return authService.login(requestDto.getEmail(), requestDto.getPassword());
	}
}
