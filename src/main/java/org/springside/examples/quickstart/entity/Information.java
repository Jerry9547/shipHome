/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "t_information")
public class Information extends IdEntity {
	private InfoType infoType;
	private InfoTypeOne infoTypeOne;
	private InfoTypeTwo infoTypeTwo;
	private InfoAction infoAction;
	private Integer isDelete;
	private Integer reviewCount;
	private Integer p;
	private Long userId;
	private String title;
	private String price;
	private Integer sprice = 0;
	private String address;
	private String phone;
	private String linkman;
	private String photo;
	private String descri;
	private String tag;
	private String city;
	private String createTime;
	private String[] photoArr;

	public Information() {
	}

	public Information(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "info_type")
	public InfoType getInfoType()
	{
		return infoType;
	}
	
	public void setInfoType(InfoType infoType)
	{
		this.infoType = infoType;
	}
	
	@ManyToOne
	@JoinColumn(name = "info_type_one")
	public InfoTypeOne getInfoTypeOne()
	{
		return infoTypeOne;
	}
	
	public void setInfoTypeOne(InfoTypeOne infoTypeOne)
	{
		this.infoTypeOne = infoTypeOne;
	}
	
	@ManyToOne
	@JoinColumn(name = "info_type_two")
	public InfoTypeTwo getInfoTypeTwo()
	{
		return infoTypeTwo;
	}
	
	public void setInfoTypeTwo(InfoTypeTwo infoTypeTwo)
	{
		this.infoTypeTwo = infoTypeTwo;
	}
	
	@ManyToOne
	@JoinColumn(name = "info_action")
	public InfoAction getInfoAction()
	{
		return infoAction;
	}
	
	public void setInfoAction(InfoAction infoAction)
	{
		this.infoAction = infoAction;
	}
	
	public Integer getIsDelete()
	{
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete)
	{
		this.isDelete = isDelete;
	}
	
	public Integer getReviewCount()
	{
		return reviewCount;
	}
	
	public void setReviewCount(Integer reviewCount)
	{
		this.reviewCount = reviewCount;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getPrice()
	{
		return price;
	}
	
	public void setPrice(String price)
	{
		this.price = price;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getLinkman()
	{
		return linkman;
	}
	
	public void setLinkman(String linkman)
	{
		this.linkman = linkman;
	}
	
	public String getPhoto()
	{
		return photo;
	}
	
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	
	public String getDescri()
	{
		return descri;
	}
	
	public void setDescri(String descri)
	{
		this.descri = descri;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}
	
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Transient
	public Integer getSprice()
	{
		return sprice;
	}

	public void setSprice(Integer sprice)
	{
		this.sprice = sprice;
	}

	@Transient
	public Integer getP()
	{
		return p;
	}

	public void setP(Integer p)
	{
		this.p = p;
	}

	@Transient
	public String[] getPhotoArr()
	{
		if(StringUtils.isNotBlank(photo) && photo.indexOf(',')>0){
			photoArr = photo.split(",");
		}else{
			photoArr = new String[]{photo};
		}
		return photoArr;
	}

	public void setPhotoArr(String[] photoArr)
	{
		this.photoArr = photoArr;
	}
	
}