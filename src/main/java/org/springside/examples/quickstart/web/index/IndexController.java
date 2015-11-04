/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.index;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.domain.Page;
import org.springside.examples.quickstart.entity.Advert;
import org.springside.examples.quickstart.entity.Information;
import org.springside.examples.quickstart.service.info.AdvertService;
import org.springside.examples.quickstart.service.info.InformationService;
import org.springside.modules.web.Servlets;


/**
 * @author zhf
 * 首页
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@Autowired
	private AdvertService advertService;
	
	@Autowired
	private InformationService informationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model, ServletRequest request) {
		List<Advert> advertList = advertService.findAllAdvert();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Information> infoList = informationService.findByTop(null, searchParams, 0, 7);
		model.addAttribute("advertList", advertList);
		model.addAttribute("infoList", infoList.getContent());
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
