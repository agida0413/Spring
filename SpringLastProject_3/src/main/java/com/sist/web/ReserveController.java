package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReserveController {
@GetMapping("reserve/reserve_main.do")
public String food_reserve() {
	return "reserve/reserve_main";
}
@PostMapping("reserve/reserve_ok.do")
public String reserve_ok()
{
	return"redirect:../mypage/mypage.do";
}
@GetMapping("mypage/mypage.do")
public String mypage_main() {
	return "mypage/mypage_main";
}

}
