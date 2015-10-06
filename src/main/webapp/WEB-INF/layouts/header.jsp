<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="header-bd">
    <div class="mainhead">
        <span id="logo"></span>
        <div class="user-box">
            <div class="noUser"><span class="clickItem" id="hd-login-btn">登录</span>|<span class="clickItem" id="hd-register-btn">注册</span></div>
            <div class="user hide"><span>小欣</span>|<span class="clickItem hd-close">退出</span></div>
        </div>
        <div class="nav">
            <ul class="clear">
                <li><a href="${ctx}/index" class="active">首页</a></li>
                <li><a href="">个人中心</a></li>
                <li id="download">
                    <a href="javascript:void(0);">手机客户端</a>
                    <div class="pop">
                        <i class="s"></i><i class="s bottom"></i>
                        <h2 class="c_b3">下载资讯APP</h2>
                        <img src="${ctx}/static/images/photo/qrcode.png" alt=""/>
                        <a href=""></a><a href="" class="android"></a>
                    </div>
                </li>
                <li><a href="${ctx}/index/aboutUs">关于我们</a></li>
            </ul>
        </div>
    </div>
    <div class="subhead-bd bg_theme">
        <div class="control">
            <div class="search-box">
                <input type="text" placeholder="搜索...."/>
                <a href="javascript:void(0);"></a>
            </div>
            <a href="" class="release-box">发布资讯</a>
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
</div>