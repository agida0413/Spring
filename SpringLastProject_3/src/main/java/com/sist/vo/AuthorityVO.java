package com.sist.vo;

import lombok.Data;

/*
 * 시큐리티 =>id (userid,username,userpwd)
 * =>1.비밀번호 암호화 
 * 2.권한부여
 * 3.자동로그인
 * 4.id저장
 * 메소드 보안 
 * 
 */
@Data
public class AuthorityVO {
private String userid,autority;

}
