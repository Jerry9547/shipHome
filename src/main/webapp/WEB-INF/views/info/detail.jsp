<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/comm.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <div id="content">
       <!-- <div class="current-location">
            <div class="section">
                <span class="location-icon icon"></span>
                <span>您当前的位置：</span>
                <span>网站首页 > 全部资讯 > </span>
                <a href="javascript:void(0);">资讯详情</a>
            </div>
        </div>
         -->
        <div class="details-module box-sizing clear">
            <div class="picFocus fl">
                <div class="bd">
                    <div class="tempWrap">
                        <ul>
                        <c:forEach items="${info.photoArr }" var="photo" varStatus="status">
                            <li><a href="javascript:void(0);"><img src="${photo }" style="width:446px;height:342px;" onerror="javascript:this.src='${ctx}/static/images/photo/show-img02.jpg';"></a></li>
                        </c:forEach>
                            <%-- <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li> --%>
                        </ul>
                    </div>
                </div>
                <div class="hd little-img">
                    <ul class="clear">
                    <c:forEach items="${info.photoArr }" var="photo" varStatus="status">
                        <li class=""><img src="${photo }" style="width: 109px;height: 65px;" onerror="javascript:this.src='${ctx}/static/images/photo/show-img02.jpg';"></li>
                    </c:forEach>
                       <%--  <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li> --%>
                    </ul>
                </div>
                <span class="prev clickObj"><em class="icon"></em></span>
                <span class="next clickObj"><em class="icon"></em></span>
            </div>
            <div class="right-con fr">
                <h1>${info.title }</h1>
                <p class="time-eyes">
                    <span class="time icon"></span>
                    <span>发布时间：${fn:getDateDiff(info.createTime,"yyyy-MM-dd HH:mm:ss") }发布</span>
                    <span class="eyes icon"></span>
                    <span>浏览人数：${info.reviewCount }人</span>
                </p>
                <p style="display:inline-block;">价格：<strong>${info.price }</strong>元起</p>
                <c:if test="${info.userId==user.id }">
                	<a href="${ctx }/info/update/${info.id }" style="display:inline-block;float:right;margin-top: 32px;"><span class="glyphicon glyphicon-edit">修改</span></a>
                </c:if>
                <p>地点：${info.address }</p>
                <p>类型：${info.infoType.type } > ${info.infoTypeOne.infoTypeOne } > ${info.infoTypeTwo.infoTypeTwo }</p>
                <p>联系人：${info.linkman }</p>
                <p>联系电话：<strong>${info.phone }</strong></p>
            </div>
        </div>
        <div class="details-list box-sizing mt0 w1000">
            <h1>描述</h1>
            <pre>
                <p>${info.descri }</p></pre>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
            var leftNum = 0;
            var $li = $(".little-img li");
            var length = $li.length;
            var lastLeft = ($li.eq(0).outerWidth() + 5)*(length - 4);
            var $clickObj;
            $(".picFocus .prev,.picFocus .next").on("click",function(){
                $clickObj = $(this);
            });
            $(".picFocus").slide({ mainCell:".bd ul",effect:"left",autoPlay:true ,startFun:function(){
                var mark = $clickObj ? $clickObj.hasClass("clickObj") : false;
                var num = $(".little-img .on").index();
                if(mark){
                    num = $clickObj.hasClass("prev") ? num - 1 < 0 ? length - 1 : num -1 : num + 1 == length ? 0 : num + 1;
                }else{
                    num = num + 1 == length ? 0 : num + 1;
                }
                leftNum = animateFun(num,length,lastLeft,leftNum,$clickObj);
            }});
        });

        function animateFun(num,length,lastLeft,leftNum,$clickObj){
            var mark = $clickObj ? $clickObj.hasClass("clickObj") : false;
            if(num > 3){
                if(mark){
                    $clickObj.hasClass("prev") ? num + 1 == length ? leftNum = -lastLeft : leftNum += 101 : leftNum -= 101;
                }else{
                    leftNum -= 101;
                }
                $(".little-img ul").animate({marginLeft:leftNum});
            }else if(num <= 3 && num > 0){
                if(mark){
                    if($clickObj.hasClass("prev")){
                        leftNum = 0;
                        $(".little-img ul").animate({marginLeft:leftNum});
                    }
                }else{
                    leftNum = 0;
                    $(".little-img ul").animate({marginLeft:leftNum});
                }
            }else if(num == 0){
                if(mark){
                    if($clickObj.hasClass("next")){
                        leftNum = 0;
                        $(".little-img ul").animate({marginLeft:leftNum});
                    }
                }else{
                    leftNum = 0;
                    $(".little-img ul").animate({marginLeft:leftNum});
                }
            }
            return leftNum;
        }
    </script>
</body>
