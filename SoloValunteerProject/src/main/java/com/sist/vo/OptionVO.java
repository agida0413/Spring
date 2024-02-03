package com.sist.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OptionVO {
private String state,city;
private String major_option,minor_option;
private String active_type,target_type,collect_state;

private Date v_start,v_end;
private Date collect_start,collect_end;
private String ss;

}
