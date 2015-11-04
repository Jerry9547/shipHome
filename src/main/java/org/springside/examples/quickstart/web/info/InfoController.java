package org.springside.examples.quickstart.web.info;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springside.examples.quickstart.entity.City;
import org.springside.examples.quickstart.entity.InfoType;
import org.springside.examples.quickstart.entity.InfoTypeOne;
import org.springside.examples.quickstart.entity.InfoTypeTwo;
import org.springside.examples.quickstart.entity.Information;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.service.info.CityService;
import org.springside.examples.quickstart.service.info.InfoTypeOneService;
import org.springside.examples.quickstart.service.info.InfoTypeService;
import org.springside.examples.quickstart.service.info.InfoTypeTwoService;
import org.springside.examples.quickstart.service.info.InformationService;
import org.springside.examples.quickstart.utils.ImageUtils;
import org.springside.modules.web.Servlets;

@Controller
@RequestMapping(value = "/info")
public class InfoController {
	
	@Autowired
	private InformationService informationService;
	@Autowired
	private InfoTypeService infoTypeService;
	@Autowired
	private InfoTypeOneService infoTypeOneService;
	@Autowired
	private InfoTypeTwoService infoTypeTwoService;
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		try
		{
			Information info = informationService.getInformation(id);
			informationService.updateView(id);
			model.addAttribute("info", info);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "info/detail";
	}
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Information info, Model model, ServletRequest request) {
//		Page<Information> page = informationService.findByPage(p==null?0:p.intValue());
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Page<Information> page = informationService.findByCondition(info, searchParams, info==null||info.getP()==null ?0:info.getP().intValue()-1);
		List<InfoTypeOne> typeOneList = infoTypeOneService.findAll();
		List<InfoTypeTwo> typeTwoList = infoTypeTwoService.findAll();
		List<City> cityList = cityService.findAll();
		Page<Information> topList = informationService.findByTop(null, searchParams, 0, 5);
		if (info.getInfoTypeOne() != null && info.getInfoTypeOne().getId()!=null && info.getInfoTypeOne().getId().intValue()>0)
		{
			info.setInfoTypeOne(infoTypeOneService.findById(info.getInfoTypeOne().getId()));
		}else{
			InfoTypeOne type1 = new InfoTypeOne();
			type1.setId(Long.valueOf(0));
			info.setInfoTypeOne(type1);
		}
		if (info.getInfoTypeTwo() != null && info.getInfoTypeTwo().getId()!=null && info.getInfoTypeTwo().getId().intValue()>0)
		{
			info.setInfoTypeTwo(infoTypeTwoService.findById(info.getInfoTypeTwo().getId()));
		}else{
			InfoTypeTwo type2 = new InfoTypeTwo();
			type2.setId(Long.valueOf(0));
			info.setInfoTypeTwo(type2);
		}
		if(info.getInfoType() == null || info.getInfoType().getId()==null){
			InfoType type = new InfoType();
			type.setId(Long.valueOf(0));
			info.setInfoType(type);
		}
		model.addAttribute("info", info);
		model.addAttribute("infoList", page.getContent());
		model.addAttribute("topList", topList.getContent());
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("currPage", page.getNumber()+1);
		model.addAttribute("isFirst", page.isFirst());
		model.addAttribute("isLast", page.isLast());
		model.addAttribute("typeOneList", typeOneList);
		model.addAttribute("typeTwoList", typeTwoList);
		model.addAttribute("cityList", cityList);
		return "info/list";
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) {
		TUser user = (TUser)session.getAttribute("user");
		if(user == null){
			return "redirect:/login";
		}else{
			List<InfoTypeOne> typeOneList = infoTypeOneService.findAll();
			List<InfoTypeTwo> typeTwoList = infoTypeTwoService.findAll();
			List<City> cityList = cityService.findAll();
			model.addAttribute("typeOneList", typeOneList);
			model.addAttribute("typeTwoList", typeTwoList);
			model.addAttribute("cityList", cityList);
			return "info/add";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "save" , method = RequestMethod.POST)
	public void save(Information information, HttpSession session, HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			if(information!=null){
				if(StringUtils.isNotBlank(information.getTitle())){
//					Information model = informationService.findByTitle(informationService.getTitle());
//					if(model != null){
//						res.put("code", 405);
//						res.put("msg", "标题名已存在");
//					}else{
					TUser user = (TUser)session.getAttribute("user");
					information.setUserId(user.getId());
					informationService.save(information);
					res.put("code", 200);
					res.put("msg", "success");
//					}
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
	
	@RequestMapping(value="update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id")Long id, HttpSession session, Model model) {
		try
		{
			TUser user = (TUser) session.getAttribute("user");
			if (user == null)
			{
				return "redirect:/login";
			} else
			{
				Information info = informationService.getInformation(id);
				List<InfoTypeOne> typeOneList = infoTypeOneService.findAll();
				List<InfoTypeTwo> typeTwoList = infoTypeTwoService.findAll();
				List<City> cityList = cityService.findAll();
				model.addAttribute("info", info);
				model.addAttribute("cityList", cityList);
				model.addAttribute("typeOneList", typeOneList);
				model.addAttribute("typeTwoList", typeTwoList);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "info/edit";
	}
	
	@ResponseBody
	@RequestMapping(value = "update" , method = RequestMethod.POST)
	public void update(Information information, HttpSession session, HttpServletResponse response){
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			if(information!=null){
				if(StringUtils.isNotBlank(information.getTitle()) && information.getId() != null){
//					Information model = informationService.findByTitle(information.getTitle());
//					if(model != null){
//						res.put("code", 405);
//						res.put("msg", "标题名已存在");
//					}else{
					Information model = informationService.getInformation(information.getId());
					if(model != null){
//						TUser user = (TUser)session.getAttribute("user");
//						information.setUserId(user.getId());
						informationService.update(information, model);
						res.put("code", 200);
						res.put("msg", "success");
					}else{
						res.put("code", 404);
						res.put("msg", "资讯不存在");
					}
//					}
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
	
	@ResponseBody
	@RequestMapping(value="upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject res = new JSONObject();
		try
		{
			writer = response.getWriter();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
		    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		    if(fileMap != null){
		    	String path = null;
		    	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		    		MultipartFile mf = entity.getValue();    
		    		String fileName = mf.getOriginalFilename();  
		    		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
		    		// 重命名文件  
		    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
		    		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		    		path = ImageUtils.saveFile(newFileName, mf, request);
		    	}
		    	res.put("code", 200);
		    	res.put("rst", path);
		    	res.put("msg", "success");
		    }else{
		    	res.put("code", 403);
		    	res.put("msg", "服务繁忙，请稍后再试！");
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
