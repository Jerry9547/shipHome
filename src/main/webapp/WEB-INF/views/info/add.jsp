<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <div id="content">
        <div class="information-box box-sizing">
            <h2 class="info-title"><span class="num">1</span>选择资讯类目：</h2>
            <ul class="info-class clear">
                <li class="fl current">
                    <span class="class-icon icon"></span>
                    <p>大宗商品</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl">
                    <span class="class-icon icon"></span>
                    <p>大宗商品</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl">
                    <span class="class-icon icon"></span>
                    <p>大宗商品</p>
                    <span class="checked icon"></span>
                </li>
                <li class="fl">
                    <span class="class-icon icon"></span>
                    <p>大宗商品</p>
                    <span class="checked icon"></span>
                </li>
            </ul>
            <h2 class="info-title"><span class="num">2</span>填写资讯信息：</h2>
            <div class="info-con pdl30">
                <span class="con-title">请选择大类型：</span>
                <ul class="choice-list clear">
                    <li class="fl current">黄沙<span class="checked icon"></span></li>
                    <li class="fl">水泥<span class="checked icon"></span></li>
                    <li class="fl">石子<span class="checked icon"></span></li>
                    <li class="fl">油品<span class="checked icon"></span></li>
                </ul>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请选择是否出售/求购：</span>
                <ul class="choice-list clear">
                    <li class="fl current">出售<span class="checked icon"></span></li>
                    <li class="fl">求购<span class="checked icon"></span></li>
                    <li class="fl">出售/求购<span class="checked icon"></span></li>
                </ul>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请选择小类型：</span>
                <div class="select big-select">
                    <span class="select-box">类型1</span>
                    <span class="arrow"></span>
                    <ul class="option-list">
                        <li>类型2</li>
                        <li>类型3</li>
                    </ul>
                </div>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入价格：</span>
                <input class="input-price box-sizing" type="text" value="3000">&nbsp;&nbsp;元起
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入联系人：</span>
                <input class="box-sizing" type="text" placeholder="请输入联系人">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入电话：</span>
                <input class="box-sizing" type="text" placeholder="请输入联系人电话">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入地址：</span>
                <input class="box-sizing" type="text" placeholder="请输入地址">
            </div>
            <div class="info-con pdl30">
                <span class="con-title">请输入标题：</span>
                <input class="box-sizing" type="text" placeholder="请输入标题">
            </div>
            <div class="info-con pdl30">
                <span class="con-title ver-top">请输入描述：</span>
                <textarea class="box-sizing" name="" id="" cols="30" rows="10" placeholder="请输入资讯描述"></textarea>
            </div>
            <div class="info-con pdl30">
                <span class="con-title">上传图片：</span>
                <button class="uploading">上传</button>
            </div>
            <ul class="photo-list clear">
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
                </li>
            </ul>
        </div>
        <div class="btn-box">
            <button class="button">发&nbsp;布</button>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
            $(".info-class li").on("click",function(){
                $(".info-class li").removeClass("current");
                $(this).addClass("current");
            });
            $(".choice-list li").on("click",function(){
                $(this).parent().find("li").removeClass("current");
                $(this).addClass("current");
            });
        })
    </script>
</body>
