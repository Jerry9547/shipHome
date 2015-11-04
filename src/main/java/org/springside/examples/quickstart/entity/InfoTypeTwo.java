/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_info_type_two")
public class InfoTypeTwo extends IdEntity {
	private String infoTypeTwo;
	private Long parentId;
	private Integer isDelete;
	private Date createTime;

	public InfoTypeTwo() {
	}

	public InfoTypeTwo(Long id) {
		this.id = id;
	}


	public String getInfoTypeTwo()
	{
		return infoTypeTwo;
	}

	public void setInfoTypeTwo(String infoTypeTwo)
	{
		this.infoTypeTwo = infoTypeTwo;
	}

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Integer getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(Integer isDelete)
	{
		this.isDelete = isDelete;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}