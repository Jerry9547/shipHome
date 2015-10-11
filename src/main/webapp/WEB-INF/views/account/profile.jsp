<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
	<div class="user-box-item" id="forgot">
    <form action="" class="clear">
        <div class="hd clear">
            <a href="${ctx}/login" class="c_999 go-link go-login">去登录</a>
            <h3>忘记密码</h3>
            <a href="javascript:void(0)" class="gray-btn getCode">获取验证码</a>
            <div class="in-item user-icon icon-phone small">
                <input type="text" placeholder="请输入手机号"/>
            </div>
            <div class="in-item user-icon icon-check small">
                <input type="text" placeholder="请输入验证码"/>
            </div>
            <div class="in-item user-icon icon-password">
                <input type="password" placeholder="请输入密码"/>
            </div>
        </div>
        <a href="" class="btn-user">完成</a>
    </form>
</div>
</body>
