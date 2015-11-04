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
@Table(name = "t_info_action")
public class InfoAction extends IdEntity {
	private Integer isDelete;
	private Long parentId;
	private String infoAction;
	private Date createTime;

	public InfoAction() {
	}

	public InfoAction(Long id) {
		this.id = id;
	}

	public Integer getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(Integer isDelete)
	{
		this.isDelete = isDelete;
	}

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long praentId)
	{
		this.parentId = praentId;
	}

	public String getInfoAction()
	{
		return infoAction;
	}

	public void setInfoAction(String infoAction)
	{
		this.infoAction = infoAction;
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