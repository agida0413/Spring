package com.sist.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sist.service.MemberService;
import com.sist.vo.MemberVO;

// xml <mvc:intercepter>
/*
 *                                           | => preHandle(자동 로그인 , ID저장)
 *                                           login.do
 *   main.do ======== DispatcherServlet  ========= HandlerMapping
 *                                                 url주소를 이용해서 해당 메소드 찾기
 *                                                 @GetMapping("main/main.do")
 *                                                 public String main_main()
 *                                                   |
 *                                                   |  ==> return ""
 *                                                   |  -- postHandle()
 *                                                 ViewReolver
 *                                                   |
 *                                                   |  ==> Model(request)
 *                                                   |  -- afterCompletion
 *                                                  JSP
 */
public class AutoLoginIntercepter extends HandlerInterceptorAdapter{
    @Autowired
    private MemberService mService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// "food_"+no
		Cookie[] cookies=request.getCookies();
		HttpSession session=request.getSession();
		if(cookies!=null)
		{
			for(int i=0;i<cookies.length;i++)
			{
			    String key=cookies[i].getName();
			    if(key.equals("userId"))
			    {
			    	String id=cookies[i].getValue();
			    	MemberVO vo=mService.memberInfo(id);
			    	session.setAttribute("userId", vo.getUserId());
					session.setAttribute("enabled", vo.getEnabled());
					session.setAttribute("authority", vo.getAuthority());
					session.setAttribute("userName", vo.getUserName());
			    	break;
			    }
			}
			//response.sendRedirect("../main/main.do");
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
    
}