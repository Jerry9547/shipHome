/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.service.account.UserService;
import org.springside.examples.quickstart.utils.SMSUtils;

/**
 * 用户注册
 * @author zhf
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}
	
	@ResponseBody
	@RequestMapping(value = "sendCode" , method = RequestMethod.GET)
	public void sendCode(String phone, HttpSession session,HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		res.put("code", 503);
		try
		{
			writer = response.getWriter();
			if(StringUtils.isNotBlank(phone)){
				String codeStr = SMSUtils.createRandom(true,6);
				String sendRes = SMSUtils.sendMessage(phone, codeStr);
				String[] resStr = sendRes.split(",");
				if("提交成功".equals(resStr[resStr.length-1])){
					String nowStr = new Date().getTime() + "";
					session.setAttribute(phone, nowStr + "-" + codeStr);
					res.put("code", 200);
				}else{
					res.put("code", 505);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			res.put("code", 501);
		}finally{
			writer.print(res.toString());
			writer.flush();
			writer.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "save" , method = RequestMethod.POST)
	public void save(TUser user, HttpSession session,HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			if(user!=null){
				if(StringUtils.isNotBlank(user.getPhone())){
					String codeStr = session.getAttribute(user.getPhone()).toString();
					String[] resStr = codeStr.split("-");
					long nowTime = new Date().getTime();
					long validTime = Long.valueOf(resStr[0]);
					if(nowTime-validTime<=300000){
						if(resStr[1].equals(user.getCode())){
							TUser model = userService.findUserByUserName(user.getUserName());
							if(model != null){
								res.put("code", 405);
								res.put("msg", "用户名已存在");
							}else{
								model = userService.findUserByPhone(user.getPhone());
								if(model != null){
									res.put("code", 406);
									res.put("msg", "该手机号已存在");
								}else{
									userService.registerUser(user);
									session.removeAttribute(user.getPhone());
									res.put("code", 200);
									res.put("msg", "success");
								}
							}
						}else{
							res.put("code", 402);
							res.put("msg", "验证码错误");
						}
					}else{
						res.put("code", 401);
						res.put("msg", "验证码超时，请重新获取验证码！");
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
