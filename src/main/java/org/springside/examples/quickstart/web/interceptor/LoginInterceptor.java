package org.springside.examples.quickstart.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.service.account.UserService;
import org.springside.examples.quickstart.utils.CookieUtils;
import org.springside.examples.quickstart.utils.EncryptionUtil;

public class LoginInterceptor implements HandlerInterceptor
{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		HttpSession session = request.getSession();
		//获取session
		TUser user = (TUser)session.getAttribute("user");
		//获取cookie
		Cookie token = CookieUtils.getCookieByName(request, "user_token");
		//存在cookie但是session过期的话重新设置session
		if(user == null && token != null && token.getValue()!=null){
			String[] strings = EncryptionUtil.getInstance().getDesString(token.getValue()).split("@&@");
			user = userService.findUserByAccount(strings[0]);
			if(user!=null && user.getPwd().equalsIgnoreCase(strings[1])){
				session.setAttribute("user", user);
			}
		}else if(user != null && token == null){
			CookieUtils.addCookie(response, "user_token", EncryptionUtil.getInstance().getEncString(user.getUserName()+"@&@"+user.getPwd()) , 10 * 60 * 60);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		// TODO Auto-generated method stub

	}

}
