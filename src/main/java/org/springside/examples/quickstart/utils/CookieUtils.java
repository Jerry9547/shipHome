package org.springside.examples.quickstart.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
@author Jerry
@date 2015年11月14日
 */
public class CookieUtils
{

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
		{
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie（接口方法）
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name)
	{
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name))
		{
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else
		{
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request)
	{
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
		{
			for (Cookie cookie : cookies)
			{
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
//	public static void resetCookie(String name, String defultValue, HttpServletRequest request, HttpServletResponse response){
//		if(user == null && token != null && token.getValue()!=null){
//			String[] strings = EncryptionUtil.getInstance().getDesString(token.getValue()).split("@&@");
//			user = userService.findUserByAccount(strings[0]);
//			if(user!=null && user.getPwd().equalsIgnoreCase(strings[1])){
//				session.setAttribute("user", user);
//			}
//		}else if(user != null && token == null){
//			CookieUtils.addCookie(response, "user_token", EncryptionUtil.getInstance().getEncString(user.getUserName()+"@&@"+user.getPwd()) , 10 * 60 * 60);
//		}
//	}
}
