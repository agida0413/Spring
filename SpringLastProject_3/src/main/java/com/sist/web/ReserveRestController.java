package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.ReserveDAO;
import com.sist.service.ReserveService;
import com.sist.vo.FoodVO;

@RestController
public class ReserveRestController {
@Autowired
private ReserveService rservice;

@GetMapping(value="reserve/reserve_food_list_vue.do", produces = "text/plain;charset=UTF-8")
public String food_list(String type) throws Exception
{
   List<FoodVO> list=rservice.foodreserveData(type);
   ObjectMapper mapper=new ObjectMapper();
   String json=mapper.writeValueAsString(list);
   return json;
}

}