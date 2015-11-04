/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.InfoAction;

public interface InfoActionDao extends PagingAndSortingRepository<InfoAction, Long> {
	
	List<InfoAction> findByIsDelete(Integer isDelete);
	
	/**
	 * 根据pid查询information
	 * @param pid
	 * @return Information
	 */
	@Query("from InfoAction x2 where x2.isDelete=0 and x2.parentId=?1")
	List<InfoAction> findByPid(Long pid);
	
}
