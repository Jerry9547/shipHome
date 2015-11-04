/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.InfoTypeOne;

public interface InfoTypeOneDao extends PagingAndSortingRepository<InfoTypeOne, Long> {
	
	List<InfoTypeOne> findByIsDelete(Integer isDelete);
	
	@Query("from InfoTypeOne x2 where x2.isDelete=0 and x2.parentId=?1")
	List<InfoTypeOne> findByParentId(Long parentId);
	
//	/**
//	 * 根据用户名或手机号查询用户
//	 * @param account 用户名或手机号
//	 * @return TUser
//	 */
//	@Query("from TUser u where u.userName=:account or phone=:account")
//	TUser findByAccount(@Param("account")String account);
}
