<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<div class="user-box-item" id="login">
    <form action="" class="clear">
        <div class="hd clear">
            <a href="${ctx}/register" class="c_999 go-link go-register">还没账号？去注册</a>
            <h3>登录</h3>
            <div class="in-item user-icon icon-user">
                <input type="text" placeholder="请输入用户名/手机"/>
            </div>
            <div class="in-item user-icon icon-password">
                <a href="${ctx}/profile" class="tle c_999 text-hover go-forgot">忘记密码？</a>
                <input type="password" placeholder="请输入密码"/>
            </div>
            <div>
                <label class="remember active" for="remember-me">记住我</label>
                <input type="checkbox" class="hide" id="remember-me"/>
            </div>

        </div>
        <a href="" class="btn-user">登录</a>
    </form>
</div>
</body>
