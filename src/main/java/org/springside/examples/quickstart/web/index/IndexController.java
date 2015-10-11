/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author zhf
 * 首页
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index/index";
	}
	@RequestMapping(value="aboutUs",method = RequestMethod.GET)
	public String aboutUs() {
		return "index/aboutUs";
	}
	@RequestMapping(value="profile",method = RequestMethod.GET)
	public String profile() {
		return "index/profile";
	}
}
