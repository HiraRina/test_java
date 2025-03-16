package jp.co.sss.test.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		
		//ログインしていない場合はログインページに遷移
		if(session.getAttribute("loginUser") == null) {
			response.sendRedirect("/test/login");
			return false;
		}
		return true;
	}
}
