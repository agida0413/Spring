package com.sist.vo;
/*
 * no int AI PK 
name varchar(50) 
subject varchar(1000) 
content text 
pwd varchar(10) 
regdate datetime 
hit int
 * 
 */

import java.sql.Date;

import lombok.Data;
@Data
public class BoardVO {
private int no,hit;
private String name,subject,content,pwd,dbday;
private Date regdate;

}
