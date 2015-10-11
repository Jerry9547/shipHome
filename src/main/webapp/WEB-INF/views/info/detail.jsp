<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                            <li><a href="javascript:void(0);"><img src="${ctx}/static/images/photo/show-img01.jpg"></a></li>
                        </ul>
                    </div>
                </div>
                <div class="hd little-img">
                    <ul class="clear">
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                        <li class=""><img src="${ctx}/static/images/photo/show-img01.jpg"></li>
                    </ul>
                </div>
                <span class="prev clickObj"><em class="icon"></em></span>
                <span class="next clickObj"><em class="icon"></em></span>
            </div>
            <div class="right-con fr">
                <h1>这边有一个货舱的船上用品需要出售，价格优可到货舱看货</h1>
                <p class="time-eyes">
                    <span class="time icon"></span>
                    <span>发布时间：7天前发布</span>
                    <span class="eyes icon"></span>
                    <span>浏览人数：335人</span>
                </p>
                <p>价格：<strong>3000</strong>元起</p>
                <p>地点：上海</p>
                <p>类型：石子</p>
                <p>联系人：张先生</p>
                <p>联系电话：<strong>15221038689</strong></p>
            </div>
        </div>
        <div class="details-list box-sizing mt0 w1000">
            <h1>描述</h1>
            <pre>
                <p>文本文本文本文本文本文本文本文本文本文本文本文本文本文本文本文本文本文本文本文啊所发生发送到发射点发送法撒旦法撒旦发射点发射点发射点发送法撒旦发射点发首发身份本文本文本文本文本文本文本文本文本文本文本文本文本文本</p></pre>
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
