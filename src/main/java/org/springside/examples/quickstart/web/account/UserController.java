/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.service.account.UserService;
import org.springside.examples.quickstart.utils.CookieUtils;
import org.springside.examples.quickstart.utils.EncryptionUtil;

/**
 * 用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="info", method = RequestMethod.GET)
	public String info( Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TUser user = (TUser)session.getAttribute("user");
//		TUser user = userService.findUserById(id);
//		model.addAttribute("userInfo", user);
		
		Cookie token = CookieUtils.getCookieByName(request, "user_token");
		if(user == null && token != null && token.getValue()!=null){
			String[] strings = EncryptionUtil.getInstance().getDesString(token.getValue()).split("@&@");
			user = userService.findUserByAccount(strings[0]);
			if(user!=null && user.getPwd().equalsIgnoreCase(strings[1])){
				session.setAttribute("user", user);
			}
		}else if(user != null && token == null){
			CookieUtils.addCookie(response, "user_token", EncryptionUtil.getInstance().getEncString(user.getUserName()+"@&@"+user.getPwd()) , 10 * 60 * 60);
		}
		if(user == null){
			return "redirect:/login";
		}else{
			return "account/info";
		}
	}

	@ResponseBody
	@RequestMapping(value = "profile" , method = RequestMethod.POST)
	public void save(TUser user, HttpSession session,HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			if(user!=null){
				if(StringUtils.isNotBlank(user.getPhone())){
					Object codeObj = session.getAttribute(user.getPhone());
					if(codeObj != null){
						String[] resStr = codeObj.toString().split("-");
						long nowTime = new Date().getTime();
						long validTime = Long.valueOf(resStr[0]);
						if(nowTime-validTime<=300000){
							if(resStr[1].equals(user.getCode())){
								TUser model = userService.findUserByPhone(user.getPhone());
								if(model == null || model.getStatus()==1){
									res.put("code", 406);
									res.put("msg", "用户不存在");
								}else{
									userService.updatePwd(model.getId(), user.getPwd());
									session.removeAttribute(user.getPhone());
									res.put("code", 200);
									res.put("msg", "success");
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
						res.put("code", 402);
						res.put("msg", "验证码错误");
					}
				}else{
					res.put("code", 501);
					res.put("msg", "无效的参数");
				}
			}else{
				res.put("code", 501);
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
	
//	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
//	public String updateForm(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("user", accountService.getUser(id));
//		return "account/adminUserForm";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String update(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
//		accountService.updateUser(user);
//		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
//		return "redirect:/admin/user";
//	}
//
//	
//	@RequestMapping(value = "delete/{id}")
//	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//		User user = accountService.getUser(id);
//		accountService.deleteUser(id);
//		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
//		return "redirect:/admin/user";
//	}
//
//	/**
//	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
//	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
//	 */
//	@ModelAttribute
//	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
//		if (id != -1) {
//			model.addAttribute("user", accountService.getUser(id));
//		}
//	}
}
