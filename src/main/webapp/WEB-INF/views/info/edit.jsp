<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <div id="content">
    <!-- <form action="save" id="info_form" name="info_form" method="post" onsubmit="javascript:return false;"> -->
    	<input type="hidden" name="id" id="_id" value="${info.id }" />
        <div class="information-box box-sizing">
            <h2 class="info-title"><span class="num">1</span>选择资讯类目：</h2>
            <input type="hidden" name="infoType.id" value="${info.infoType.id }" id="ty_id"/>
            <ul class="info-class clear">
                <li class="fl <c:if test="${info.infoType.id==1 }">current</c:if> it" data-id="1">
                    <span class="class-icon icon"></span>
                    <p>大宗商品</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl <c:if test="${info.infoType.id==2 }">current</c:if> it" data-id="2">
                    <span class="class-icon icon"></span>
                    <p>船舶服务</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl <c:if test="${info.infoType.id==3 }">current</c:if> it" data-id="3">
                    <span class="class-icon icon"></span>
                    <p>海事服务</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl <c:if test="${info.infoType.id==4 }">current</c:if> it" data-id="4">
                    <span class="class-icon icon"></span>
                    <p>配货信息</p>
                    <span class="checked icon"></span>
                </li>
            </ul>
            <h2 class="info-title"><span class="num">2</span>填写资讯信息：</h2>
            <!-- <div class="info-con pdl30">
                <span class="con-title">请选择大类型：</span>
                <ul class="choice-list clear">
                    <li class="fl current">黄沙<span class="checked icon"></span></li>
                    <li class="fl">水泥<span class="checked icon"></span></li>
                    <li class="fl">石子<span class="checked icon"></span></li>
                    <li class="fl">油品<span class="checked icon"></span></li>
                </ul>
            </div> -->
            <div class="info-con pdl30">
                <span class="con-title">请选择是否出售/求购：</span>
                <input type="hidden" name="infoAction.id" value="${info.infoAction.id }" id="iac_id"/>
                <ul class="choice-list clear" id="iac">
                    <li class="fl <c:if test="${info.infoAction.id==2 }">current</c:if>" data-id="2">出售<span class="checked icon"></span></li>
                    <li class="fl <c:if test="${info.infoAction.id==3 }">current</c:if>" data-id="3">求购<span class="checked icon"></span></li>
                    <li class="fl <c:if test="${info.infoAction.id==1 }">current</c:if>" data-id="1">出售/求购<span class="checked icon"></span></li>
                </ul>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请选择大类型：</span>
               	<input type="hidden" name="infoTypeOne.id" value="${info.infoTypeOne.id }" id="ty1_id"/>
                <div class="select big-select">
                    <span class="select-box" data-id="${info.infoTypeOne.id }">${info.infoTypeOne.infoTypeOne }</span>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ty1">
                   <c:forEach items="${typeOneList }" var="typeOne" varStatus="">
                   		<c:if test="${typeOne.parentId==info.infoType.id }">
	                        <li class="tp_${typeOne.parentId }" data-id="${typeOne.id }">${typeOne.infoTypeOne }</li>
                   		</c:if>
                   		<c:if test="${typeOne.parentId!=info.infoType.id }">
	                        <li class="tp_${typeOne.parentId } none" data-id="${typeOne.id }">${typeOne.infoTypeOne }</li>
                   		</c:if>
                    </c:forEach>
                        <!-- <li>类型2</li>
                        <li>类型3</li> -->
                    </ul>
                </div>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请选择小类型：</span>
               	<input type="hidden" name="infoTypeTwo.id" value="${info.infoTypeTwo.id }" id="ty2_id"/>
                <div class="select big-select">
                    <span class="select-box" data-id="${info.infoTypeTwo.id }" style="z-index:auto;">${info.infoTypeTwo.infoTypeTwo }</span>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ty2">
                    <c:forEach items="${typeTwoList }" var="typeTwo" varStatus="">
                    	<c:if test="${typeTwo.parentId==info.infoTypeOne.id }">
                        	<li class="tp_${typeTwo.parentId }" data-id="${typeTwo.id }">${typeTwo.infoTypeTwo }</li>
                    	</c:if>
                    	<c:if test="${typeTwo.parentId!=info.infoTypeOne.id }">
                        	<li class="tp_${typeTwo.parentId } none" data-id="${typeTwo.id }">${typeTwo.infoTypeTwo }</li>
                    	</c:if>
                    </c:forEach>
                        <!-- <li>类型2</li>
                        <li>类型3</li> -->
                    </ul>
                </div>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请选择地区：</span>
               	<input type="hidden" name="city" value="${info.city }" id="city"/>
                <div class="select big-select">
                    <span class="select-box" style="z-index:auto;">${empty info.city ? "请选择" : info.city }</span>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ctl">
                    <c:forEach items="${cityList }" var="city" varStatus="">
                        <li>${city.name }</li>
                    </c:forEach>
                        <!-- <li>类型2</li>
                        <li>类型3</li> -->
                    </ul>
                </div>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入价格：</span>
                <input class="input-price box-sizing" id="price" maxlength="8" name="price" type="text" value="${info.price }" >&nbsp;&nbsp;元起
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入联系人：</span>
                <input class="box-sizing" type="text" id="linkman" maxlength="20" name="linkman" value="${info.linkman }" placeholder="请输入联系人">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入电话：</span>
                <input class="box-sizing" type="text" id="phone" name="phone" maxlength="13" minlength="11" value="${info.phone }" placeholder="请输入联系人电话">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入地址：</span>
                <input class="box-sizing" type="text" id="address" maxlength="200" name="address" value="${info.address }" placeholder="请输入地址">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入标题：</span>
                <input class="box-sizing" type="text" id="title" name="title" maxlength="200" value="${info.title }" placeholder="请输入标题">
            </div>
            <div class="info-con pdl30">
                <span class="con-title ver-top">请输入描述：</span>
                <textarea class="box-sizing" name="descri" id="desc" cols="30" rows="10" placeholder="请输入资讯描述">${info.descri }</textarea>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">上传图片：</span>
                <!-- <button class="uploading">上传</button> -->
                <div style="display:inline-block;">
					<iframe name="upFile" id="upFile" style="display:none;" onload="_iload()"></iframe>
					<form action="${ctx}/info/upload" id="up_form" name="up_form" method="post" enctype="multipart/form-data" target="upFile">
					<div style="display: inline-block; position: relative;">
						<span style="position: relative; cursor: pointer; height: 25px; line-height: 25px; zoom: 1; background-position: 0 0;">
						<button type="button" class="btn uploading" style="padding:3px;" id="file" value="上传" >上传</button></span>
						<input type="file" title="选择图片" class="uploadimg" accept="image/bmp,image/jpeg,image/jpg,image/png,image/gif" id="imgFile" name="file" onchange="_up_img()">
					</div>
					</form>
				</div>
            </div>
             <input type="hidden" name="photos" id="photos" value="" />
            <ul class="photo-list clear">
            	<c:forEach items="${info.photoArr }" var="photo">
	                <li class="fl">
	                    <img src="${photo }" alt=""/>
	                    <span class="del icon"></span>
	                </li>
            	</c:forEach>
                <%-- 
                <li class="fl">
                    <img src="${ctx}/static/images/photo/show-img03.jpg" alt=""/>
                    <span class="del icon"></span>
                </li>
                <li class="fl">
                    <img src="${ctx}/static/images/photo/show-img03.jpg" alt=""/>
                    <span class="del icon"></span>
                </li>
                <li class="fl">
                    <img src="${ctx}/static/images/photo/show-img03.jpg" alt=""/>
                    <span class="del icon"></span>
                </li>
                <li class="fl">
                    <img src="${ctx}/static/images/photo/show-img03.jpg" alt=""/>
                    <span class="del icon"></span>
                </li>
                <li class="fl">
                    <img src="${ctx}/static/images/photo/show-img03.jpg" alt=""/>
                    <span class="del icon"></span>
                </li> --%>
            </ul>
        </div>
        <div class="btn-box">
            <button class="button" onclick="update()">保&nbsp;存</button>
        </div>
        <!-- </form> -->
    </div>
    <script type="text/javascript">
        $(function(){
        	    $(".select .select-box").on("click",function(){
        	        if($(this).hasClass("current")){
        	            $(this).removeClass("current").nextAll(".option-list").slideUp();
        	        }else{
        	            $(".select .select-box.current").removeClass("current").nextAll(".option-list").slideUp();
        	            $(this).addClass("current").nextAll(".option-list").slideDown();
        	        }
        	        return false;
        	    }).nextAll(".option-list").on("click",function(e){
        	            var txt = $(e.target).text();
        	            $(this).slideUp().prevAll(".select-box").removeClass("current").text(txt);
        	            if($(this).attr("id")=="ty1"){
        	            	var _tyd = $(e.target).data("id");
        	            	$("#ty1_id").val(_tyd);
        	        		$("#ty2").find("li").hide();
        	        		$("#ty2 .tp_"+$(e.target).data("id")).show();
        	        		$("#ty2").slideUp().prevAll(".select-box").removeClass("current").text("请选择");
        	        	}else if($(this).attr("id")=="ty2"){
        	        		var _tyd = $(e.target).data("id");
        	            	$("#ty2_id").val(_tyd);
        	        	}else if($(this).attr("id")=="ctl"){
           	        		var _tex = $(e.target).text();
           	        		$(this).parent().prev().val(_tex);
        	        	}
        	            return false;
        	        });
        	    $(document).on("click",function(){
        	        $(".select .select-box.current").removeClass("current").nextAll(".option-list").slideUp();
        	    });
        	
            $(".info-class li").on("click",function(){
                $(".info-class li").removeClass("current");
                $(this).addClass("current");
                $("#ty_id").val($(this).data("id"));
                $("#ty1").find("li").hide();
                $("#ty1 .tp_" + $(this).data("id")).show();
                $("#ty1").slideUp().prevAll(".select-box").removeClass("current").text("请选择");
                $("#ty2").find("li").hide();
                $("#ty2").slideUp().prevAll(".select-box").removeClass("current").text("请选择");
            });
            $(".choice-list li").on("click",function(){
                $(this).parent().find("li").removeClass("current");
                $(this).addClass("current");
                $("#iac_id").val($(this).data("id"));
            });
            $("#price").keyup(function(){
        		var _this = $(this);
        		var _inputVal = _this.val().trim();
        		if($.isNumeric($(this).val())){
        			$(this).val(parseInt($(this).val()));
        		}
        		/* if(_inputVal.parentInt()){
        			_this.val(_inputVal.substring(0,_inputVal.length-1));
        			return false;
        		}else if(_this.val().length>2 && !_this.val().trim().match(/^[a-zA-Z]\w{3,19}$/)){
        		//if(_this.val().trim()!='' && !_this.val().trim().match(/^([a-z]|[A-Z])[0-9a-zA-Z_]+$/)){
        			_this.val(_inputVal.substring(0,_inputVal.length-1));
        			return false;
        		} */
        	});
            $("body").on("click",".del",function(){
            	$(this).parent().remove();
            });
        })
        function checkV(){
        	if($("#ty1_id").val().trim()==''){
        		alert("请选择大类型");
        		return false;
        	}
        	if($("#ty2_id").val().trim()==''){
        		alert("请选择小类型");
        		return false;
        	}
        	if($("#city").val().trim()==''){
        		alert("请选择地区");
        		return false;
        	}
        	if($("#price").val().trim()==''){
        		alert("请输入价格");
        		$("#price").focus();
        		return false;
        	}
        	if($("#linkman").val().trim()==''){
        		alert("请输入联系人");
        		$("#linkman").focus();
        		return false;
        	}
        	var _phone = $("#phone");
        	if(_phone.val().trim()==''){
        		alert("请输入手机号");
        		_phone.focus();
        		return false;
        	}else if(!_phone.val().match(/^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|17[0-9]{1})+\d{8})$/)){
        		alert("手机号格式不正确");
        		_phone.focus();
        		return false;
        	}
        	if($("#address").val().trim()==''){
        		alert("请输入地址");
        		$("#address").focus();
        		return false;
        	}
        	if($("#title").val().trim()==''){
        		alert("请输入标题");
        		$("#title").focus();
        		return false;
        	}
        	if($("#desc").val().trim()==''){
        		alert("请输入描述");
        		$("#desc").focus();
        		return false;
        	}
        	if($(".photo-list img").length<1){
        		alert("请上传图片");
        		return false;
        	}
        	return true;
        }
        function update(){
			if(checkV()){
				var imgs = "";
				$(".photo-list img").each(function(idx){
					if(idx>0){
						imgs += ",";
					}
					imgs += $(this).attr("src");
				});
				$.ajax( {
					url : "${ctx}/info/update",
					/* data: $("#info_form").serialize(), */
					data: {
						"id":$("#_id").val(),
						"infoType.id":$("#ty_id").val(),
						"infoTypeOne.id":$("#ty1_id").val(),
						"infoTypeTwo.id":$("#ty2_id").val(),
						"infoAction.id":$("#iac_id").val(),
						"city":$("#city").val(),
						"price":$("#price").val(),
						"linkman":$("#linkman").val(),
						"phone":$("#phone").val(),
						"address":$("#address").val(),
						"title":$("#title").val(),
						"descri":$("#desc").val(),
						"photo":imgs
						//"photo":$("#photos").val()
					},
					type : 'post',
					dataType : 'json',
					success : function(data) {
						if(data.code != 200){
							alert("服务繁忙，请稍后再试！");
						}else{
							location.href = "${ctx}/info/list";
						}
					}
				});
			}
		}
        
    </script>
</body>
