/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.TUser;

public interface TUserDao extends PagingAndSortingRepository<TUser, Long> {
	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return TUser
	 */
	TUser findByUserName(String userName);
	
	/**
	 * 根据手机号码查询用户
	 * @param phone
	 * @return TUser
	 */
	TUser findByPhone(String phone);
	
	/**
	 * 根据用户名或手机号查询用户
	 * @param account 用户名或手机号
	 * @return TUser
	 */
	@Query("from TUser u where u.status=0 and (u.userName=:account or phone=:account)")
	TUser findByAccount(@Param("account")String account);
	
	/**
	 * 修改密码
	 * @param phone 用户手机号
	 * @param pwd 用户密码
	 * @return int
	 */
	@Modifying(clearAutomatically = true)
	@Query("update TUser set pwd=?1 where status=0 and id=?2")
	int updatePwd(String pwd, Long id);
}
