package com.tripor.member.controller;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tripor.member.model.dto.MemberDto;
import com.tripor.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@Tag(name="회원관리")
public class MemberController {
	@Autowired
	MemberService memberService;

	@Hidden
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	// ============================== REST API =================================
	@Operation(summary="로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw,
			@RequestParam(name = "saveid", required = false) String saveid) {
		log.debug("login Form memberId : {}, memberPw : {}", memberId, memberPw);
		try {
			
			Map<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("memberPw", memberPw);
			
			MemberDto memberDto = memberService.loginMember(map);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(memberDto);
//		return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
//		return ResponseEntity.status(HttpStatus.CREATED).body(cnt);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary="회원가입")
	@PostMapping(value = "")
	public ResponseEntity<?> join(
		@RequestBody(required = true, content = @Content(schema = @Schema(implementation = MemberDto.class))) @org.springframework.web.bind.annotation.RequestBody MemberDto memberDto) {
		log.debug("userRegister memberDto : {}", memberDto);
		try {
			System.out.println(memberDto);
			memberService.joinMember(memberDto);
//			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(memberDto);
//			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
//			return ResponseEntity.status(HttpStatus.CREATED).body(cnt);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary="회원정보 수정")
	@PutMapping(value = "")
	public ResponseEntity<?> modify(@org.springframework.web.bind.annotation.RequestBody MemberDto memberDto) {
		log.debug("userModify memberDto : {}", memberDto);
		try {
			// form에서 hidden 필드 memberId도 전송 추가 해야함
			if (memberDto.getMemberId() == null)
				throw new Exception("ID 없음");
			memberService.updateMember(memberDto);

			MemberDto modifiedUser = memberService.getMember(memberDto.getMemberId());
			Map<String, Object> map = new HashMap<>();
			map.put("result", "ok");
			map.put("modifiedUser", modifiedUser);

//			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary="회원탈퇴")
	@DeleteMapping(value = "/{memberid}")
	public ResponseEntity<?> delete(@PathVariable("memberid") String memberId) {
		log.debug("delete memberid : {}", memberId);
		try {
			memberService.deleteMember(memberId);
//			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(Collections.singletonMap("resultCode", "ok"));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary="회원정보 조회")
	@GetMapping(value = "/{memberid}")
	public ResponseEntity<?> detail(@PathVariable("memberid") String memberId) {
		log.debug("detail memberid : {}", memberId);
		try {
			MemberDto memberDto = memberService.getMember(memberId);

			Map<String, Object> map = new HashMap<>();
			map.put("result", "ok");
			map.put("member", memberDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary="회원 존재여부 확인")
	@GetMapping(value = "/exist/{memberid}")
	public ResponseEntity<?> checkExistence(@PathVariable("memberid") String memberId) {
		log.debug("exist memberid : {}", memberId);
		try {
			int count = memberService.idCheck(memberId);

			Map<String, Object> map = new HashMap<>();
			map.put("result", "ok");
			map.put("cnt", count);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(map);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
}
