package org.springside.examples.quickstart.web.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/info")
public class InfoController {
	
	@RequestMapping(value="detail",method = RequestMethod.GET)
	public String detail() {
		return "info/detail";
	}
	@RequestMapping(value="list",method = RequestMethod.GET)
	public String list() {
		return "info/list";
	}
	@RequestMapping(value="add",method = RequestMethod.GET)
	public String add() {
		return "info/add";
	}
}
