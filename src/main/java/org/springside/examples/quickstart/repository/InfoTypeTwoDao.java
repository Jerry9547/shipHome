/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.InfoTypeTwo;

public interface InfoTypeTwoDao extends PagingAndSortingRepository<InfoTypeTwo, Long> {
	
	List<InfoTypeTwo> findByIsDelete(Integer isDelete);
	
	@Query("from InfoTypeTwo x2 where x2.isDelete=0 and x2.parentId=?1")
	List<InfoTypeTwo> findByParentId(Long parentId);
}
