package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	
@GetMapping("food/list.do")
public String food_list(Model model) {
	model.addAttribute("login_jsp","../member/login.jsp");
	return "food/list";
}
@GetMapping("food/detail.do")
public String food_detail(int fno,Model model) {
	model.addAttribute("fno",fno);
	model.addAttribute("login_jsp","../member/login.jsp");
	return "food/detail";
}

}
