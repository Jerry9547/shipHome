/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.service.account.UserService;

/**
 * 登陆
 * @author zhf
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "account/login";
	}
	
	@RequestMapping(value = "loginout", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/index";
	}

	@ResponseBody
	@RequestMapping(value = "loginToken" , method = RequestMethod.POST)
	public void loginToken(TUser user, HttpSession session,HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			if(user!=null){
				if(StringUtils.isNotBlank(user.getUserName())){
					TUser model = userService.findUserByAccount(user.getUserName());
					if(model!=null && model.getPwd().equalsIgnoreCase(user.getPwd())){
						session.setAttribute("user", model);
						res.put("code", 200);
						res.put("msg", "success");
					}else{
						res.put("code", 401);
						res.put("msg", "用户名或密码错误");
					}
				}else{
					res.put("code", 403);
					res.put("msg", "无效的参数");
				}
			}else{
				res.put("code", 403);
				res.put("msg", "无效的参数");
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			res.put("code", 503);
			res.put("msg", "服务繁忙，请稍后再试！");
		}finally{
			writer.print(res.toString());
			writer.flush();
			writer.close();
		}
	}
}
