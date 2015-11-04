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
import org.springside.examples.quickstart.entity.InfoAction;
import org.springside.examples.quickstart.repository.InfoActionDao;

/**
 * 
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class InfoActionService {

	private static Logger logger = LoggerFactory.getLogger(InfoActionService.class);

	@Autowired
	private InfoActionDao infoActionDao;

	public List<InfoAction> findAll() {
		return  infoActionDao.findByIsDelete(0);
	}

	public InfoAction findById(Long id) {
		return infoActionDao.findOne(id);
	}

	public List<InfoAction> findByPid(Long pid) {
		return infoActionDao.findByPid(pid);
	}


}
