package com.sist.vo;

import lombok.Data;
import java.util.*;
/*
 * 이름      널?       유형             
------- -------- -------------- 
NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(51)   
SUBJECT NOT NULL VARCHAR2(1000) 
CONTENT NOT NULL CLOB           
PWD              VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER 
 */
@Data
public class FreeBoardVO {
private int no,hit;
private String name,subject,content,pwd,dbday;
private Date regdate;
}