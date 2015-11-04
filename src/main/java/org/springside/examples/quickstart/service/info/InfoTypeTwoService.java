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
import org.springside.examples.quickstart.entity.InfoTypeTwo;
import org.springside.examples.quickstart.repository.InfoTypeTwoDao;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class InfoTypeTwoService {

	private static Logger logger = LoggerFactory.getLogger(InfoTypeTwoService.class);

	@Autowired
	private InfoTypeTwoDao infoTypeTwoDao;

	public List<InfoTypeTwo> findAll() {
		return  infoTypeTwoDao.findByIsDelete(0);
	}

	public InfoTypeTwo findById(Long id) {
		return infoTypeTwoDao.findOne(id);
	}

	public List<InfoTypeTwo> findByPid(Long pid) {
		return infoTypeTwoDao.findByParentId(pid);
	}
}
