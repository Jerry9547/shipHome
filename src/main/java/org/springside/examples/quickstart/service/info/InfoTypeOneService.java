/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.service.info;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.InfoTypeOne;
import org.springside.examples.quickstart.repository.InfoTypeOneDao;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class InfoTypeOneService {

	private static Logger logger = LoggerFactory.getLogger(InfoTypeOneService.class);

	@Autowired
	private InfoTypeOneDao infoTypeOneDao;

	public List<InfoTypeOne> findAll() {
		return  infoTypeOneDao.findByIsDelete(0);
	}

	public InfoTypeOne findById(Long id) {
		return infoTypeOneDao.findOne(id);
	}

	public List<InfoTypeOne> findByPid(Long pid) {
		return infoTypeOneDao.findByParentId(pid);
	}
	
}
