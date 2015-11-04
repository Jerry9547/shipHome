/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.InfoType;
import org.springside.examples.quickstart.entity.InfoTypeOne;

public interface InfoTypeDao extends PagingAndSortingRepository<InfoType, Long> {
	
	List<InfoType> findByIsDelete(Integer isDelete);
}
