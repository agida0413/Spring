package com.sist.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class ProgramVO {
private int vno, int_runtime,collect_num,apply_num,hit,stack_apply;
private String title,major_field,minor_field,runtime,rundate,volunteer_type,centername,si,gu,address,target,active_type;
private Date collect_start,collect_end,v_start,v_end;
private String dbCollect_start,dbCollect_end,dbV_start,dbV_end,collect_state;




}
