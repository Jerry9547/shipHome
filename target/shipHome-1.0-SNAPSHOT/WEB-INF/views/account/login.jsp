<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
<script type="text/javascript" src="${ctx}/static/js/common/sha1.js"></script>
	<div class="user-box-item" id="login">
    <form action="" class="clear" id="login_form">
        <div class="hd clear">
            <a href="${ctx}/register" class="c_999 go-link go-register">还没账号？去注册</a>
            <h3>登录</h3>
            <div class="in-item user-icon icon-user">
                <input type="text" name="userName" id="name" placeholder="请输入用户名/手机"/>
            </div>
            <div class="in-item user-icon icon-password">
                <a href="${ctx}/profile" class="tle c_999 text-hover go-forgot" tabIndex="-1">忘记密码？</a>
                <input type="password" name="pwd" id="pwd" placeholder="请输入密码" />
            </div>
            <div>
                <label class="remember active" for="remember-me">记住我</label>
                <input type="checkbox" class="hide" id="remember-me"/>
            </div>

        </div>
        <a href="javascript:void(0);" class="btn-user" onclick="login()">登录</a>
    </form>
</div>
<script type="text/javascript">
$(function(){
	$("#name").keyup(function(){
		var _this = $(this);
		var _inputVal = _this.val().trim();
		if(_inputVal.length<2 && !_this.val().trim().match(/^[a-zA-Z0-9]$/)){
			_this.val(_inputVal.substring(0,_inputVal.length-1));
			return false;
		}else if(_this.val().length>2 && !_this.val().trim().match(/^[a-zA-Z0-9][0-9a-zA-Z_]+$/)){
			_this.val(_inputVal.substring(0,_inputVal.length-1));
			return false;
		}
	});
});
function checkV(){
	if($("#name").val().trim()=='' || !$("#name").val().match(/^[a-zA-Z0-9][0-9a-zA-Z_]+$/)){
		$("#name").focus();
		return false;
	}
	if($("#pwd").val().trim()==''){
		$("#pwd").focus();
		return false;
	}
	return true;
}
function login(){
	if(checkV()){
		//$("input[type=password]").val(hex_sha1($("#pwd").val()));
		$.ajax( {
			url : "${ctx}/login/loginToken",
			data: $("#login_form").serialize(),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.code != 200){
					alert(data.msg);
				}else{
					location.href = "index";
				}
			}
		});
	}
}
</script>
</body>
