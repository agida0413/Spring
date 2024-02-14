package com.sist.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {

private int enabled;
private String userid,username,userpwd,sex,birthday,email,post,addr1,addr2,phone,content,reg_dbday,mod_dbday,last_dbday;
private Date regdate,modifydate,lastlogin;
}
