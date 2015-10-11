/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户注册
 * @author zhf
 * 
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}
	
}
