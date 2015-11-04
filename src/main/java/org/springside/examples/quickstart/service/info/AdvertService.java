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
import org.springside.examples.quickstart.entity.Advert;
import org.springside.examples.quickstart.repository.AdvertDao;

/**
 * 广告管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AdvertService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AdvertService.class);

	private AdvertDao advertDao;

	public List<Advert> findAllAdvert() {
		return (List<Advert>) advertDao.findAll();
	}


	@Autowired
	public void setAdvertDao(AdvertDao advertDao)
	{
		this.advertDao = advertDao;
	}

}
