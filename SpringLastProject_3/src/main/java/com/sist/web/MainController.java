package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.service.*;
@Controller
public class MainController {
   @Autowired
   private FoodService fServie;
   
   @Autowired
   private RecipeService rService;
   
   @Autowired
   private MemberService mService;
   
   @GetMapping("main/main.do")
   public String main_main(Model model,Principal p,HttpSession session)
   {	
	   if(p!=null) {
		   
	   }
	   // JSP로 값을 전송 객체 => 전송 객체 ==> Model (HttpServletRequest)
	   List<FoodVO> foodList=fServie.foodHome12();
	   model.addAttribute("foodList", foodList);
	   
	   List<RecipeVO> recipeList=rService.recipeHome12();
	   List<ChefVO> chefList=rService.chefHome12();
	   
	   model.addAttribute("recipeList", recipeList);
	   model.addAttribute("chefList", chefList);
	   /*
	    *    request.setAttribute("nList", nList);
	         request.setAttribute("fList", fList);
	         request.setAttribute("newList", newList);
	    */
	   return "main";
   }
}






