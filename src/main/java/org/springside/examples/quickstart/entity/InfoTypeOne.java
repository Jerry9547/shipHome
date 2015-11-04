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

@Entity
@Table(name = "t_info_type_one")
public class InfoTypeOne extends IdEntity {
	private String infoTypeOne;
	private Long parentId;
	private Integer isDelete;
	private Date createTime;

	public InfoTypeOne() {
	}

	public InfoTypeOne(Long id) {
		this.id = id;
	}

	public String getInfoTypeOne()
	{
		return infoTypeOne;
	}

	public void setInfoTypeOne(String infoTypeOne)
	{
		this.infoTypeOne = infoTypeOne;
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