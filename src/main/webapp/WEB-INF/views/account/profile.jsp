<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
<script type="text/javascript" src="${ctx}/static/js/common/sha1.js"></script>
	<div class="user-box-item" id="forgot">
    <form action="" id="pu_form" class="clear" onsubmit="javascript:return false;">
        <div class="hd clear">
            <a href="${ctx}/login" class="c_999 go-link go-login">去登录</a>
            <h3>忘记密码</h3>
            <a href="javascript:void(0);" class="gray-btn getCode" opt="0" onclick="sendValidationCode(this)">获取验证码</a>
            <div class="in-item user-icon icon-phone small">
                <input type="text" placeholder="请输入手机号" maxlength="13" minlength="11" id="phone" name="phone"/>
            </div>
            <div class="in-item user-icon icon-check small">
                <input type="text" placeholder="请输入验证码" id="code" name="code" maxlength="6" name="code"/>
            </div>
            <div class="in-item user-icon icon-password">
                <input type="password" id="pwd" name="pwd" placeholder="请输入密码"/>
            </div>
        </div>
        <a href="javascript:void(0);" class="btn-user" onclick="profile()">完成</a>
    </form>
</div>
<script type="text/javascript">
$(function(){
	$("#code").keyup(function(){
		var _this = $(this);
		var _inputVal = _this.val().trim();
		if($.isNumeric($(this).val())){
			$(this).val(parseInt($(this).val()));
		}
	});
});
function sendValidationCode(obj){
	var _phone = $("#phone").val();
	var _obj = $(obj);
	if(_phone.trim()==''){
		alert("请输入手机号");
		$("#phone").focus();
		return false;
	}else if(!_phone.match(/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|17[0-9]{1})+\d{8})$/)){
		alert("手机号格式不正确");
		$("#phone").focus();
		return false;
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
	if($("#pwd").val().trim()==''){
		$("#pwd").focus();
		alert("请输入密码");
		return false;
	}
	if($("#pwd").val().trim().length<6){
		alert("密码要长于6位");
		$("#pwd").focus();
		return false;
	}
	return true;
}
function profile(){
	if(checkV()){
		//$("input[type=password]").val(hex_sha1($("#pwd").val()));
		$.ajax( {
			url : "${ctx}/user/profile",
			data: $("#pu_form").serialize(),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if(data.code == 401 || data.code == 402 || data.code == 406){
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
