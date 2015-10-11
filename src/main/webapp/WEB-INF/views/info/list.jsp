<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<div class="subhead-bd bg_theme">
        <div class="control">
            <div class="search-box">
                <input type="text" placeholder="搜索...."/>
                <a href="javascript:void(0);"></a>
            </div>
            <a href="${ctx}/info/add" class="release-box">发布资讯</a>
        </div>
        <div class="nav">
            <ul class="clear">
                <li><a href="" class="active">全部资讯</a></li>
                <li><a href="">大宗商品</a></li>
                <li><a href="">船舶服务</a></li>
                <li><a href="">海事服务</a></li>
                <li><a href="">配货信息</a></li>
            </ul>
        </div>
    </div>
    <div id="content">
        <div class="screen-column">
            <div class="section">
                <span class="select-title">地区：</span>
                <div class="select">
                    <span class="select-box">全部地区</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>南京</li>
                        <li>仪征</li>
                    </ul>
                </div>
                <span class="select-title">大类型：</span>
                <div class="select">
                    <span class="select-box">全部地区</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>南京</li>
                        <li>仪征</li>
                    </ul>
                </div>
                <span class="select-title">小类型：</span>
                <div class="select">
                    <span class="select-box">全部地区</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>南京</li>
                        <li>仪征</li>
                    </ul>
                </div>
                <span class="select-title">价格：</span>
                <div class="select">
                    <span class="select-box">全部地区</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>南京</li>
                        <li>仪征</li>
                    </ul>
                </div>
                <span class="select-title">出售/求购：</span>
                <div class="select">
                    <span class="select-box">全部地区</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>南京</li>
                        <li>仪征</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="commodity-list">
            <div class="section">
                <div class="clear">
                    <ul class="list-info fl">
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                        <li class="clear">
                            <img class="show-img fl" src="${ctx}/static/images/photo/show-img01.jpg" alt=""/>
                            <p class="list-title">这边有一个水泥100吨需要出售，需要联系</p>
                            <p class="city">南京</p>
                            <p class="price"><span>2500</span>元起</p>
                            <span class="time">发布时间：2015-09-06</span>
                        </li>
                    </ul>
                    <ul class="img-list fr">
                        <li>
                            <img src="${ctx}/static/images/photo/show-img02.jpg" alt=""/>
                            <p class="ellipsis box-sizing">这边有个货舱的船上用品需要出售，价格红啊好收复失地</p>
                        </li>
                        <li>
                            <img src="${ctx}/static/images/photo/show-img02.jpg" alt=""/>
                            <p class="ellipsis box-sizing">这边有个货舱的船上用品需要出售，价格红啊好萨芬是否</p>
                        </li>
                    </ul>
                </div>
                <div class="paging">
                    <div class="paging-box">
                        <span class="back">上一页</span>
                        <span class="next">下一页</span>
                        <ul class="pages clear">
                            <li class="fl">1</li>
                            <li class="fl">2</li>
                            <li class="fl">3</li>
                            <li class="fl">4</li>
                            <li class="fl">5</li>
                            <li class="fl">6</li>
                            <li class="ell fl">...</li>
                            <li class="fl">18</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
