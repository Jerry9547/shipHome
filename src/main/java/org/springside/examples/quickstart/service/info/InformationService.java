/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.service.info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.Information;
import org.springside.examples.quickstart.repository.InformationDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;
import org.springside.modules.utils.Clock;

/**
 * 资讯管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class InformationService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(InformationService.class);

	@Autowired
	private InformationDao informationDao;
	private Clock clock = Clock.DEFAULT;

	public List<Information> getAllInformation() {
		return (List<Information>) informationDao.findAll();
	}

	public Information getInformation(Long id) {
		return informationDao.findOne(id);
	}
	
	public Information findByTitle(String title){
		return informationDao.findByTitle(title);
	}
	
	public int updateView(Long id) {
		return informationDao.addView(id);
	}

	public Page<Information> findByCondition(Information condition, Map<String, Object> searchParams, int pageNumber) {
//		if(info == null){
//			return findByPage(pageNumber, pageSize);
			Sort sort = null;
			if(condition != null && condition.getSprice() != null && condition.getSprice().intValue() > 0){
				if (condition.getSprice().intValue()==1)
				{
					sort = new Sort(Direction.DESC, "price");
				}else{
					sort = new Sort(Direction.ASC, "price");
				}
			}else{
				sort = new Sort(Direction.DESC, "createTime");
			}
			PageRequest pageRequest = new PageRequest(pageNumber, 10, sort);
			Specification<Information> spec = buildSpecification(condition, searchParams);
			return informationDao.findAll(spec, pageRequest);
//		}
//		if(){
//			
//		}
//		Sort sort = new Sort(Direction.DESC, "reviewCount");
//		Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
//		Specification<Information> spec = buildSpecification(searchParams);
//		return informationDao.findAll(spec, pageable);
	}
	
	public Page<Information> findByPage(int pageNumber) {
		Sort sort = new Sort(Direction.DESC, "reviewCount");
		Pageable pageable = new PageRequest(pageNumber, 10, sort);
		return informationDao.findAll(pageable);
	}
	
	public Page<Information> findByTop(Information condition, Map<String, Object> searchParams, int pageNumber, int pageSize) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<Information> spec = buildSpecification(condition, searchParams);
		return informationDao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		Sort sort = new Sort(Direction.DESC, "reviewCount");

		return new PageRequest(pageNumber, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Information> buildSpecification(Information condition, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//		filters.put("isDelete", new SearchFilter("isDelete", Operator.EQ, 0));
		if(condition != null){
			try
			{
				if(StringUtils.isNotBlank(condition.getTitle())){
					condition.setTitle(new String(condition.getTitle().getBytes("ISO8859-1"),"utf-8"));
					filters.put("title", new SearchFilter("title", Operator.LIKE, condition.getTitle()));
				}
				if(condition.getInfoAction()!=null && condition.getInfoAction().getId()!=null && condition.getInfoAction().getId().intValue()>0){
					filters.put("infoAction.id", new SearchFilter("infoAction.id", Operator.EQ, condition.getInfoAction().getId().intValue()));
				}
				if(condition.getInfoType()!=null && condition.getInfoType().getId()!=null && condition.getInfoType().getId().intValue()>0){
					filters.put("infoType.id", new SearchFilter("infoType.id", Operator.EQ, condition.getInfoType().getId().intValue()));
				}
				if(condition.getInfoTypeOne()!=null && condition.getInfoTypeOne().getId()!=null && condition.getInfoTypeOne().getId().intValue()>0){
					filters.put("infoTypeOne.id", new SearchFilter("infoTypeOne.id", Operator.EQ, condition.getInfoTypeOne().getId().intValue()));
				}
				if(condition.getInfoTypeTwo()!=null && condition.getInfoTypeTwo().getId()!=null && condition.getInfoTypeTwo().getId().intValue()>0){
					filters.put("infoTypeTwo.id", new SearchFilter("infoTypeTwo.id", Operator.EQ, condition.getInfoTypeTwo().getId().intValue()));
				}
				if(StringUtils.isNotBlank(condition.getCity())){
					condition.setCity(new String(condition.getCity().getBytes("ISO8859-1"),"utf-8"));
					filters.put("city", new SearchFilter("city", Operator.EQ, condition.getCity()));
				}
				if (condition.getUserId() != null && condition.getUserId().intValue()>0)
				{
					filters.put("userId", new SearchFilter("userId", Operator.EQ, condition.getUserId().intValue()));
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		Specification<Information> spec = DynamicSpecifications.bySearchFilter(filters.values(), Information.class);
		return spec;
	}
	
	public void save(Information model) {
		model.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		model.setIsDelete(0);
		model.setReviewCount(0);
		model.setUserId(model.getUserId());
		informationDao.save(model);
	}

	/**
	 * 修改资讯信息
	 * @param param 修改参数
	 * @param model	原始参数
	 */
	public void update(Information param, Information model) {
		param.setCreateTime(model.getCreateTime());
		param.setIsDelete(model.getIsDelete());
		param.setReviewCount(model.getReviewCount());
		param.setUserId(model.getUserId());
		informationDao.save(param);
	}

	public void delete(Long id) {
		informationDao.updateStatus(1, id);
	}


//	@Autowired
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}


	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
