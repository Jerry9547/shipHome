<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
    <div id="content">
        <div class="focus">
            <div class="device">
                <a class="arrow-left" href="#"></a>
                <a class="arrow-right" href="#"></a>
                <div class="swiper-container banner-focus">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide"> <img src="${ctx}/static/images/photo/banner.jpg"> </div>
                        <div class="swiper-slide"> <img src="${ctx}/static/images/photo/banner.jpg"> </div>
                        <div class="swiper-slide"> <img src="${ctx}/static/images/photo/banner.jpg"> </div>
                    </div>
                </div>
                <div class="pagination banner-pagination"></div>
            </div>
        </div>
        <div class="bg_f1">
            <p class="mess-box ellipsis auto c_666"><span>欢迎您！最新资讯：资讯网即将上线...</span></p>
        </div>
        <div class="index-main clear">
            <div class="goods mt30 br_e3">
                <div class="left">
                    <div class="device">
                        <div class="swiper-container middle-focus">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide"> <img src="${ctx}/static/images/photo/index-middle.jpg"> <div class="title"><p class="ellipsis">这边有一个货舱的船上用品需要出售，价……</p></div></div>
                                <div class="swiper-slide"> <img src="${ctx}/static/images/photo/index-middle.jpg"> <div class="title"><p class="ellipsis">这边有一个货舱的船上用品需要出售，价……</p></div></div>
                            </div>
                        </div>
                        <div class="pagination middle-pagination"></div>
                    </div>
                </div>
                <div class="right">
                    <h3><span>这边有一个货舱的船上用品需要出售，价格优可到货舱看货</span> <a class="btn-look" href="${ctx}/info/detail">查看详情</a></h3>
                    <ul class="list">
                        <li>
                            <a href="">
                                <p class="ellipsis">这边有一个货舱的船上用品需要出售，价船上用品需要出售</p>
                                <span class="c_999">2014-09-12</span>
                            </a>
                        </li><li>
                            <a href="">
                                <p class="ellipsis">这边有一个货舱的船上用品需要出售，价船上用品需要出售</p>
                                <span class="c_999">2014-09-12</span>
                            </a>
                        </li><li>
                            <a href="">
                                <p class="ellipsis">这边有一个货舱的船上用品需要出售，价船上用品需要出售</p>
                                <span class="c_999">2014-09-12</span>
                            </a>
                        </li><li>
                            <a href="">
                                <p class="ellipsis">这边有一个货舱的船上用品需要出售，价船上用品需要出售</p>
                                <span class="c_999">2014-09-12</span>
                            </a>
                        </li>

                    </ul>
                    <div class="tc mt20">
                        <a href="${ctx}/info/list" class="btn-more">查看更多资讯 ></a>
                    </div>
                </div>
            </div>
            <div class="news mtb30 br_e3">
                <div class="tc title-metro mb20"><span>资讯图片</span></div>
                <div class="left">
                    <ul class="list">
                        <li>
                            <img src="${ctx}/static/images/photo/index-b-item.jpg" alt=""/>
                            <div class="more">
                                <div>
                                    <p>这边有一个货舱的船上用品需要出售，价格优可到货舱看货。     </p>
                                </div>
                            </div>
                        </li><li>
                            <img src="${ctx}/static/images/photo/index-b-item.jpg" alt=""/>
                            <div class="more">
                                <div>
                                    <p>这边有一个货舱的船上用品需要出售，价格优可到货舱看货。     </p>
                                </div>
                            </div>
                        </li><li class="mt20">
                            <img src="${ctx}/static/images/photo/index-b-item.jpg" alt=""/>
                            <div class="more">
                                <div>
                                    <p>这边有一个货舱的船上用品需要出售，价格优可到货舱看货。     </p>
                                </div>
                            </div>
                        </li><li class="mt20">
                            <img src="${ctx}/static/images/photo/index-b-item.jpg" alt=""/>
                            <div class="more">
                                <div>
                                    <p>这边有一个货舱的船上用品需要出售，价格优可到货舱看货。     </p>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="right">
                    <img src="${ctx}/static/images/photo/index-b.jpg" alt=""/>
                </div>
            </div>
        </div>
    </div>
    <div id="footer"></div>
    <script>
        /*banner focus*/
        var bannerSwiper = new Swiper('.banner-focus',{
            pagination: '.banner-pagination',
            loop:true,
            grabCursor: true,
            paginationClickable: true
        });
        $('.arrow-left').on('click', function(e){
            e.preventDefault();
            bannerSwiper.swipePrev()
        });
        $('.arrow-right').on('click', function(e){
            e.preventDefault();
            bannerSwiper.swipeNext()
        })

        /*middle focus*/
        var middleSwiper = new Swiper('.middle-focus',{
            pagination: '.middle-pagination',
//            loop:true,
            grabCursor: true,
            paginationClickable: true
        });
        $(".middle-pagination span").each(function (index,item) {
            $(item).html(index+1)
        })
    </script>
</body>
