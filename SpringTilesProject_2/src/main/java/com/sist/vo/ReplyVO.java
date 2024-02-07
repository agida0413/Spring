package com.sist.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class ReplyVO {
private int rno,fno;
private String name,id,msg,dbday;
private Date regdate;
}
