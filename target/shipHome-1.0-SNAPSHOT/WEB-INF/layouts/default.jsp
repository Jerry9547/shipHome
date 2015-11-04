<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>航运宝<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link href="${ctx}/static/css/base.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/main.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/js/frame/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/frame/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
<script src="${ctx}/static/js/frame/idangerous.swiper.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common/hyb.js" type="text/javascript"></script>

<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
</body>
</html>