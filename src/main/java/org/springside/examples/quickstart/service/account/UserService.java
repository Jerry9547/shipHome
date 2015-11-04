/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.TUser;
import org.springside.examples.quickstart.repository.TUserDao;
import org.springside.examples.quickstart.service.ServiceException;
import org.springside.examples.quickstart.service.account.ShiroDbRealm.ShiroUser;
import org.springside.modules.utils.Clock;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class UserService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	private TUserDao tuserDao;
	private Clock clock = Clock.DEFAULT;

	public List<TUser> getAllUser() {
		return (List<TUser>) tuserDao.findAll();
	}

	public TUser getUser(Long id) {
		return tuserDao.findOne(id);
	}

	public TUser findUserByUserName(String loginName) {
		return tuserDao.findByUserName(loginName);
	}
	
	public TUser findUserByPhone(String phone) {
		return tuserDao.findByPhone(phone);
	}
	
	public TUser findUserByAccount(String account) {
		return tuserDao.findByAccount(account);
	}
	
	public TUser findUserById(Long id) {
		return tuserDao.findOne(id);
	}

	public int registerUser(TUser user) {
		
//		entryptPassword(user);
		user.setCreateTime(clock.getCurrentDate());
		user.setStatus(0);

		tuserDao.save(user);
		return 1;
	}

	public int updatePwd(Long id, String pwd){
		return tuserDao.updatePwd(pwd, id);
	}
	
	public void updateUser(TUser user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		tuserDao.save(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		tuserDao.delete(id);
//		taskDao.deleteByUserId(id);

	}
	
	public boolean checkPwd(TUser user,String orgpwd){
		user.setPlainPassword(user.getPwd());
		entryptPassword(user);
		System.out.println(orgpwd+"==>"+user.getPwd());
		if(orgpwd.equalsIgnoreCase(user.getPwd())){
			return true;
		}
		return false;
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(TUser user) {
//		byte[] salt = Digests.generateSalt(SALT_SIZE);
////		user.setSalt(Encodes.encodeHex(salt));
//
//		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
//		user.setPwd(Encodes.encodeHex(hashPassword));
//		user.setPwd(MD5Builder.getMD5(user.getPlainPassword()));
	}

	@Autowired
	public void setTuserDao(TUserDao tuserDao)
	{
		this.tuserDao = tuserDao;
	}


	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
