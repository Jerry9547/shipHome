<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="header-bd">
    <div class="mainhead">
        <span id="logo"></span>
        <div class="user-box">
            <c:if test="${user==null}">
	            <div class="noUser"><span class="clickItem" id="hd-login-btn"><a href="${ctx}/login">登录</a></span>|<span class="clickItem" id="hd-register-btn"><a href="${ctx}/register">注册</a></span></div>
            </c:if>
            <c:if test="${user!=null}">
            	<div class="user"><span>${user.userName }</span>|<span class="clickItem hd-close"><a href="${ctx}/login/loginout">退出</a></span></div>
            </c:if>
        </div>
        <div class="nav">
            <ul class="clear">
                <li><a href="${ctx}/index" class="active">首页</a></li>
                <li><a href="${ctx}/user/info">个人中心</a></li>
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
</div>