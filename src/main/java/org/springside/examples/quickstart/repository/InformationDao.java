/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.Information;

public interface InformationDao extends PagingAndSortingRepository<Information, Long>,JpaSpecificationExecutor<Information> {
	
	@Query("from Information where title =?1 ")
//	@Query("from Information where status=0 and title =?1 ")
	Information findByTitle(String title);
	
	/**
	 * 
	 * @param status
	 * @param id
	 * @return int
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Information set status=?1 where id=?2 ")
	int updateStatus(int status, Long id);
	
	/**
	 * 
	 * @param id
	 * @return int
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Information set reviewCount=reviewCount+1 where id=?1 ")
	int addView(Long id);
	
	
}
