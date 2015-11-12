<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
<script type="text/javascript" src="${ctx}/static/js/common/sha1.js"></script>
<div class="user-box-item" id="register">
    <form action="register/save" id="register_form" class="clear" method="post" onsubmit="javascript:return false;">
        <div class="hd clear">
            <a href="login" class="c_999 go-link go-login">已有账号？去登录</a>
            <h3>注册</h3>
            <div class="in-item user-icon icon-user">
                <input type="text" name="userName" id="name" maxlength="30" placeholder="请输入用户名" />
            </div>
            <a href="javascript:void(0);" class="gray-btn getCode" opt="0" onclick="sendValidationCode(this)">获取验证码</a>
            <div class="in-item user-icon icon-phone small">
                <input type="text" id="phone" name="phone" placeholder="请输入手机号" maxlength="13" class="tm-number" onchange=""/>
            </div>
            <div class="in-item user-icon icon-check small">
                <input type="text" id="code" name="code" maxlength="6" placeholder="请输入验证码"/>
            </div>
            <div class="in-item user-icon icon-password">
                <input type="password" name="pwd" id="fpwd" maxlength="50" placeholder="请输入密码"/>
            </div>
            <div class="in-item user-icon icon-password">
                <input type="password" name="plainPassword" maxlength="50" id="repwd" placeholder="请确认密码"/>
            </div>
            <div class="in-item">
                <input type="text" id="ship_name" name="shipName" maxlength="50" placeholder="请输入船舶名称（选填）"/>
            </div>
            <div class="in-item">
                <input type="text" id="ship_no" name="shipNo" maxlength="50" placeholder="请输入船舶编号（选填）"/>
            </div>
            <p class="c_999" style="margin-top: -10px">注册即同意 <a href="${ctx}/index/profile" class="c_theme go-agree">《航运宝协议》</a></p>
        </div>
        <a href="javascript:void(0);" class="btn-user" onclick="register()">注册</a>
    </form>
</div>
<script type="text/javascript">
$(function(){
	$("#repwd").blur(function(){
		if($(this).val().trim()!=$("#fpwd").val().trim()){
			$(this).parent().css("border","1px solid red");
		}else{
			$(this).parent().css("border","1px solid #ddd");
		}
	});
	$("#name").keyup(function(){
		var _this = $(this);
		var _inputVal = _this.val().trim();
		if(_inputVal.length<2 && !_this.val().trim().match(/^[a-zA-Z]$/)){
			_this.val(_inputVal.substring(0,_inputVal.length-1));
			return false;
		}else if(_this.val().length>2 && !_this.val().trim().match(/^[a-zA-Z]\w{3,19}$/)){
		//if(_this.val().trim()!='' && !_this.val().trim().match(/^([a-z]|[A-Z])[0-9a-zA-Z_]+$/)){
			_this.val(_inputVal.substring(0,_inputVal.length-1));
			return false;
		}
	});
});
function sendValidationCode(obj){
	var _phone = $("#phone").val();
	var _obj = $(obj);
	if(_phone.trim()==''){
		alert("请输入手机号");
		$("#phone").focus();
	}else if(!_phone.match(/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|17[0-9]{1})+\d{8})$/)){
		alert("手机号格式不正确");
		$("#phone").focus();
	}else if(_obj.attr("opt")==0){
		_obj.attr("opt",1);
		$.ajax( {
			url : "${ctx}/register/sendCode",
			data: {phone:$("#phone").val()},
			type : 'get',
			dataType : 'json',
			success : function(data) {
				if(data.code != 200){
					alert(data.msg);
				}else{
					f_timeout(obj)
				}
			}
		});
	}
}
function f_timeout(obj){  
    $(obj).html('<lable id="timeb2"> 60 </lable>秒后重新获取 ');  
        timer = self.setInterval(addsec,1000);  
}  
function addsec(){  
	var t = $('#timeb2').html();  
	if(t > 0){  
	 $('#timeb2').html(t-1);  
	}else{  
		window.clearInterval(timer);  
	 	$(".getCode").html("获取验证码");
	 	$(".getCode").attr("opt",0);
	}  
} 
function checkV(){
	if($("#name").val().trim()==''){
		$("#name").focus();
		alert("请输入用户名");
		return false;
	}else if(!$("#name").val().match(/^[a-zA-Z][0-9a-zA-Z_]+$/)){
		$("#name").focus();
		alert("用户名格式不正确");
		return false;
	}
	if($("#phone").val().trim()==''){
		$("#phone").focus();
		alert("请输入手机号");
		return false;
	}else if(!$("#phone").val().match(/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|17[0-9]{1})+\d{8})$/)){
		alert("手机号格式不正确");
		$("#phone").focus();
		return false;
	}
	if($("#code").val().trim()==''){
		$("#code").focus();
		alert("请输入验证码");
		return false;
	}else if($("#code").val().trim().length!=6 || !$("#code").val().trim().match(/^[0-9]{6}$/)){
		$("#code").focus();
		alert("验证码格式不正确");
		return false;
	}
	if($("#fpwd").val().trim()==''){
		$("#fpwd").focus();
		alert("请输入密码");
		return false;
	}
	if($("#fpwd").val().trim().length<6){
		alert("密码要长于6位");
		$("#fpwd").focus();
		return false;
	}
	if($("#repwd").val().trim()==''){
		$("#repwd").focus();
		alert("请输入确认密码");
		return false;
	}
	if($("#repwd").val().trim()!=$("#fpwd").val().trim()){
		$("#repwd").focus();
		alert("密码与确认密码不一致");
		return false;
	}
	if($("#ship_name").val().trim()==''){
		$("#ship_name").focus();
		alert("请输入船舶名称");
		return false;
	}
	if($("#ship_no").val().trim()==''){
		$("#ship_no").focus();
		alert("请输入船舶编号");
		return false;
	}
	
	return true;
}
function register(){
	if(checkV()){
		//$("input[type=password]").val(hex_sha1($("#fpwd").val()));
		$.ajax( {
			url : "${ctx}/register/save",
			data: $("#register_form").serialize(),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.code == 401 || data.code == 402 || data.code == 405 || data.code == 406){
					alert(data.msg);
				}else if(data.code != 200){
					alert("服务繁忙，请稍后再试！");
				}else{
					location.href = "login";
				}
			}
		});
	}
}
</script>
</body>
