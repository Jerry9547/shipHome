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
import org.springside.examples.quickstart.entity.City;
import org.springside.examples.quickstart.repository.CityDao;

/**
 * 广告管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class CityService {


	private static Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	private CityDao cityDao;

	public List<City> findAll() {
		return (List<City>) cityDao.findAll();
	}



}
