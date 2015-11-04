/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_user")
public class TUser extends IdEntity {
	private String userName;
	private String phone;
	private String plainPassword;
	private String pwd;
	private String code;
	private String shipName;
	private String shipNo;
	private Integer status;
	private Date createTime;

	public TUser() {
	}

	public TUser(Long id) {
		this.id = id;
	}

	@NotBlank
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NotBlank
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonIgnore
	public String getPlainPassword()
	{
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword)
	{
		this.plainPassword = plainPassword;
	}
	
	@NotBlank
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String password) {
		this.pwd = password;
	}

	@NotBlank
	public String getShipName()
	{
		return shipName;
	}

	public void setShipName(String shipName)
	{
		this.shipName = shipName;
	}
	
	@NotBlank
	public String getShipNo()
	{
		return shipNo;
	}

	public void setShipNo(String shipNo)
	{
		this.shipNo = shipNo;
	}

	@Transient
	@JsonIgnore
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}