<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/comm.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
<script type="text/javascript" src="${ctx}/static/js/frame/jquery.page.js"></script>
<%-- <c:set value="{ '全部价格','3000-5000','1万-3万','3万~10万','10万~30万','30万~50万','50万~100万','100万~ ' }" var="priceArr"></c:set> --%>
<c:set value="综合,高到低,低到高" var="parrayvalue"></c:set>
<c:set value="全部价格,出售/求购,出售,求购 " var="iacarrvalue"></c:set>
<c:set var="delim" value=","/> 
<c:set var="priceArr" value="${fc:split(parrayvalue, delim)}"/>
<c:set var="iacArr" value="${fc:split(iacarrvalue, delim)}"/>
	<div class="subhead-bd bg_theme">
        <div class="control">
            <div class="search-box">
                <input type="text" id="search" name="" value="<c:if test="${not empty info.title}">${info.title }</c:if>" placeholder="搜索...."/>
                <a href="javascript:void(0);" class="_serach"></a>
            </div>
            <a href="${ctx}/info/add" class="release-box">发布资讯</a>
        </div>
        <div class="nav">
        	<input type="hidden" name="infoType.id" value="<c:if test="${empty info.infoType }">0</c:if><c:if test="${not empty info.infoType }">${info.infoType.id }</c:if>" id="ty_id"/>
            <ul class="clear">
                <li><a href="javascript:void(0);" class="<c:if test="${empty info.infoType or info.infoType.id==0 }">active</c:if> tag_it" data-it="0">全部资讯</a></li>
                <li><a href="javascript:void(0);" class="<c:if test="${not empty info.infoType and info.infoType.id==1 }">active</c:if> tag_it" data-it="1">大宗商品</a></li>
                <li><a href="javascript:void(0);" class="<c:if test="${not empty info.infoType and info.infoType.id==2 }">active</c:if> tag_it" data-it="2">船舶服务</a></li>
                <li><a href="javascript:void(0);" class="<c:if test="${not empty info.infoType and info.infoType.id==3 }">active</c:if> tag_it" data-it="3">海事服务</a></li>
                <li><a href="javascript:void(0);" class="<c:if test="${not empty info.infoType and info.infoType.id==4 }">active</c:if> tag_it" data-it="4">配货信息</a></li>
            </ul>
        </div>
    </div>
    <div id="content">
        <div class="screen-column">
            <div class="section">
                <span class="select-title">地区：</span>
	        	<input type="hidden" name="city" value="<c:if test="${not empty info.city }">${info.city }</c:if>" id="city"/>
                <div class="select">
                	<c:if test="${not empty info.city }">
                    	<span class="select-box">${info.city }</span>
                	</c:if>
                	<c:if test="${empty info.city }">
                    	<span class="select-box">全部地区</span>
                	</c:if>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ctl">
                    	<c:forEach items="${cityList }" var="city" >
                        	<li >${city.name }</li>
                    	</c:forEach>
                        <!-- <li>南京</li>
                        <li>仪征</li> -->
                    </ul>
                </div>
                <span class="select-title">大类型：</span>
                <input type="hidden" name="infoTypeOne.id" value="<c:if test="${empty info.infoTypeOne }">0</c:if><c:if test="${not empty info.infoTypeOne }">${info.infoTypeOne.id }</c:if>" id="ty1_id"/>
                <div class="select">
                	<c:if test="${empty info.infoTypeOne or info.infoTypeOne.id==0 }">
                    <span class="select-box ty_all" data-id="0">全部类型</span>
                	</c:if>
                	<c:if test="${not empty info.infoTypeOne and  info.infoTypeOne.id>0}">
                    <span class="select-box ty_all" data-id="${info.infoTypeOne.id }">${info.infoTypeOne.infoTypeOne }</span>
                	</c:if>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ty1">
                    <c:forEach items="${typeOneList }" var="typeOne" varStatus="">
                    	<c:if test="${empty info.infoType or info.infoType.id==0 or info.infoType.id==typeOne.parentId}">
                        	<li class="tp_${typeOne.parentId }" data-id="${typeOne.id }">${typeOne.infoTypeOne }</li>
                    	</c:if>
                    	<c:if test="${not empty info.infoType and  info.infoType.id!=typeOne.parentId}">
	                        <li class="tp_${typeOne.parentId } none" data-id="${typeOne.id }">${typeOne.infoTypeOne }</li>
                    	</c:if>
                    </c:forEach>
                       <!--  <li>南京</li>
                        <li>仪征</li> -->
                    </ul>
                </div>
                <span class="select-title">小类型：</span>
                <input type="hidden" name="infoTypeTwo.id" value="<c:if test="${empty info.infoTypeTwo }">0</c:if><c:if test="${not empty info.infoTypeTwo }">${info.infoTypeTwo.id }</c:if>" id="ty2_id"/>
                <div class="select">
                    <c:if test="${empty info.infoTypeTwo or info.infoTypeTwo.id==0 }">
                    <span class="select-box ty_all" data-id="0">全部类型</span>
                	</c:if>
                	<c:if test="${not empty info.infoTypeTwo and  info.infoTypeTwo.id>0}">
                    <span class="select-box ty_all" data-id="${info.infoTypeTwo.id }">${info.infoTypeTwo.infoTypeTwo }</span>
                	</c:if>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ty2">
                    <c:forEach items="${typeTwoList }" var="typeTwo" varStatus="">
	                    <c:if test="${not empty info.infoTypeOne and info.infoTypeOne.id==typeTwo.parentId }">
                        	<li class="tp_${typeTwo.parentId } " data-id="${typeTwo.id }">${typeTwo.infoTypeTwo }</li>
	                	</c:if>
	                	<c:if test="${empty info.infoTypeOne or  info.infoTypeOne.id!=typeTwo.parentId}">
                        	<li class="tp_${typeTwo.parentId } none" data-id="${typeTwo.id }">${typeTwo.infoTypeTwo }</li>
	                	</c:if>
                    </c:forEach>
                        <!-- <li>南京</li>
                        <li>仪征</li> -->
                    </ul>
                </div>
                <span class="select-title">价格：</span>
                <input type="hidden" name="sprice" id="price" value="${info.sprice }" />
                <div class="select">
                	<c:if test="${empty info.sprice or info.sprice==0}">
	                    <span class="select-box">综合</span>
                	</c:if>
                	<c:if test="${not empty info.sprice and info.sprice > 0}">
	                    <span class="select-box">${priceArr[info.sprice] }</span>
                	</c:if>
                    <span class="arrow"></span>
                    <ul class="option-list" id="prc">
                        <li data-min="0" data-max="3000" data-id="0">综合</li>
                        <li data-min="3000" data-max="5000" data-id="1">高到低</li>
                        <li data-min="5000" data-max="10000" data-id="2">低到高</li>
                    </ul>
                </div>
                <span class="select-title">出售/求购：</span>
                <input type="hidden" name="infoAction.id" id="iac_id" value="<c:if test="${empty info.infoAction }">0</c:if><c:if test="${not empty info.infoAction }">${info.infoAction.id }</c:if>" />
                <div class="select">
                	<c:if test="${empty info.infoAction or empty info.infoAction.id or info.infoAction.id==0}">
                    	<span class="select-box" data-id="0">全部</span>
                	</c:if>
                	<c:if test="${not empty info.infoAction and info.infoAction.id>0}">
                    	<span class="select-box" data-id="0">${iacArr[info.infoAction.id] }</span>
                	</c:if>
                    <span class="arrow"></span>
                    <ul class="option-list" id="ia">
                        <li data-id="1">出售/求购</li>
                        <li data-id="2">出售</li>
                        <li data-id="3">求购</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="commodity-list">
        	<c:if test="${empty infoList }">
        	<div>暂无数据</div>
        	</c:if>
        	<c:if test="${not empty infoList }">
            <div class="section">
                <div class="clear">
                    <ul class="list-info fl">
                    <c:forEach items="${infoList }" var="info" varStatus="">
                        <li class="clear">
                            <img class="show-img fl" src="${info.photoArr[0]}" onerror="javascript:this.src='${ctx}/static/images/photo/show-img02.jpg';" alt="" />
                            <a href="${ctx}/info/detail/${info.id}" >
                            <p class="list-title nowrap">${info.title }</p>
                            </a>
                            <p class="city">${info.city }</p>
                            <p class="price"><span>${info.price }</span>元起</p>
                            <span class="time">发布时间：${fn:timeFormat(info.createTime) }</span>
                        </li>
                    </c:forEach>
                        <%-- <li class="clear">
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
                        </li> --%>
                    </ul>
                    <ul class="img-list fr">
                    	<c:forEach items="${topList }" var="info" varStatus="idx">
	                    	<c:if test="${idx.index<3 }">
		                        <li>
			                        <a href="${ctx}/info/detail/${info.id}" >
			                            <img src="${info.photoArr[0]}" alt="" style="width:310px;height:240px;" onerror="javascript:this.src='${ctx}/static/images/photo/show-img02.jpg';"/>
			                            <p class="ellipsis box-sizing">${info.title }</p>
			                        </a>
		                        </li>
	                    	</c:if>
                    	</c:forEach>
                        <%-- 
                        <li>
                            <img src="${ctx}/static/images/photo/show-img02.jpg" alt=""/>
                            <p class="ellipsis box-sizing">这边有个货舱的船上用品需要出售，价格红啊好收复失地</p>
                        </li>
                        <li>
                            <img src="${ctx}/static/images/photo/show-img02.jpg" alt=""/>
                            <p class="ellipsis box-sizing">这边有个货舱的船上用品需要出售，价格红啊好萨芬是否</p>
                        </li> --%>
                    </ul>
                </div>
               	<input type="hidden" id="currPage" value="${currPage }" />
               	<input type="hidden" id="totalPage" value="${totalPage }" />
                <c:if test="${totalPage>0 }">
                <div class="paging tcdPageCode">
                    <div class="paging-box">
                        <span class="back" data-opt="">上一页</span>
                        <span class="next" data-opt="">下一页</span>
                        <ul class="pages clear" id="plist">
                            <li class="fl topage" data-opt="0">1</li>
                            <li class="fl topage">2</li>
                            <li class="fl topage">3</li>
                            <li class="fl topage">4</li>
                            <li class="fl topage">5</li>
                            <li class="fl topage">6</li>
                            <li class="ell fl">...</li>
                            <li class="fl topage" >9</li>
                        </ul>
                    </div>
                </div>
                </c:if>
            </div>
           </c:if>
        </div>

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
   	            	var _t1 = $("#ty2").find("li");
   	            	var _t2 = $("#ty2 .tp_"+_tyd);
   	        		$("#ty2").find("li").hide();
   	        		$("#ty2 .tp_"+$(e.target).data("id")).show();
   	        		$("#ty2").slideUp().prevAll(".select-box").removeClass("current").text("请选择");
   	            	$("#ty2_id").val("0");
   	        	}else if($(this).attr("id")=="ty2" || $(this).attr("id")=="prc" || $(this).attr("id")=="ia"){
   	        		var _tyd = $(e.target).data("id");
   	        		$(this).parent().prev().val(_tyd);
   	            	//$("#ty2_id").val(_tyd);
   	        	}else if($(this).attr("id")=="ctl"){
   	        		var _tex = $(e.target).text();
   	        		$(this).parent().prev().val(_tex);
   	        	}
   	            $("._serach").trigger("click");
   	            return false;
   	        });
   	    $(document).on("click",function(){
   	        $(".select .select-box.current").removeClass("current").nextAll(".option-list").slideUp();
   	    });
    	
    	$(".tag_it").click(function(){
    		$("#ty_id").val($(this).data("it"));
    		location.href = "${ctx}/info/list?infoType.id="+$(this).data("it");
    	});
    	$("._serach").click(function(){
    		var condi = $("#search").val();
    		//if(condi.trim() != ''){
    			var type = $("#ty_id").val();
    			var type1 = $("#ty1_id").val();
    			var type2 = $("#ty2_id").val();
    			var price = $("#price").val();
    			var iac = $("#iac_id").val();
    			var city = $("#city").val();
    			location.href="${ctx}/info/list?infoType.id="+type+"&infoTypeOne.id="+type1+"&infoTypeTwo.id="+type2+"&title="+condi+"&sprice="+price+"&city="+city+"&infoAction.id="+iac;
    		//}
    	});
    	$(".paging").createPage({
    		pageCount:"${totalPage}",
    		current:"${currPage}",
    		backFn:function(p){
				location.href="${ctx}/info/list?infoType.id=${info.infoType.id}&infoTypeOne.id=${info.infoTypeOne.id}&infoTypeTwo.id=${info.infoTypeTwo.id}&title=${info.title}&sprice=${info.sprice}&city=${info.city}&infoAction.id=${info.infoAction.id}&p="+p;
    		}
    	});
    	
    });
    </script>
</body>
