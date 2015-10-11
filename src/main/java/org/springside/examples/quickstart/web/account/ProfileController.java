/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户修改自己资料的Controller.
 * 
 * @author zhf
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public String updateForm(Model model) {
		return "account/profile";
	}

}
