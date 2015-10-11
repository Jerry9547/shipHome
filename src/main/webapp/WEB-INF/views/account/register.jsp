<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
<div class="user-box-item" id="register">
    <form action="" class="clear">
        <div class="hd clear">
            <a href="javascript:void(0)" class="c_999 go-link go-login">已有账号？去登录</a>
            <h3>注册</h3>
            <div class="in-item user-icon icon-user">
                <input type="text" placeholder="请输入用户名/手机"/>
            </div>
            <a href="javascript:void(0)" class="gray-btn getCode">获取验证码</a>
            <div class="in-item user-icon icon-phone small">
                <input type="text" placeholder="请输入手机号"/>
            </div>
            <div class="in-item user-icon icon-check small">
                <input type="text" placeholder="请输入验证码"/>
            </div>
            <div class="in-item">
                <input type="password" placeholder="请输入船舶名称（选填）"/>
            </div>
            <div class="in-item">
                <input type="password" placeholder="请输入船舶编号（选填）"/>
            </div>
            <p class="c_999" style="margin-top: -10px">注册即同意 <a href="${ctx}/index/profile" class="c_theme go-agree">《航运宝协议》</a></p>
        </div>
        <a href="" class="btn-user">注册</a>
    </form>
</div>
</body>
