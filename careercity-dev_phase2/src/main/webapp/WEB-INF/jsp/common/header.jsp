<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 'http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="session"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>iCaN (Career Navigator)</title>
    <!-- Site wide Custom Style -->
    <link rel="stylesheet" href="${ctx}/css/commonStyle.css">
    <!-- Index Custom Style -->
    <link rel="stylesheet" href="${ctx}/css/indexStyle.css">
    <!-- Account Custom Style -->
    <link rel="stylesheet" href="${ctx}/css/accountsStyle.css">
    <!-- Result Custom Style  -->
    <link rel="stylesheet" href="${ctx}/css/resultStyle.css">
	<!-- Header Custom Style -->
	<link rel="stylesheet" href="${ctx}/css/headerStyle.css">
	<!-- Footer Custom Style -->
	<link rel="stylesheet" href="${ctx}/css/footerStyle.css">
	<!-- LIBRARIES -->
	<!-- Bootstrap 4.1.1 -->
	<link rel="stylesheet" href="${ctx}/lib/bootstrap-4.1.1-dist/css/bootstrap.min.css">
	<!-- jQuery 3.3.1  -->
	<script src="${ctx}/lib/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>
	<!-- FontAwesome 5.0.13 -->
	<link rel="stylesheet" href="${ctx}/lib/fontawesome-free-5.0.13/web-fonts-with-css/css/fontawesome-all.min.css">
	<link rel="stylesheet" href="${ctx}/lib/fontawesome-free-5.0.13/web-fonts-with-css/css/fontawesome.min.css">
	<link rel="stylesheet" href="${ctx}/lib/fontawesome-free-5.0.13/web-fonts-with-css/css/fa-brands.min.css">
	<!-- Slick -->
	<link rel="stylesheet" type="text/css" href="${ctx}/lib/slick/slick.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/lib/slick/slick-theme.css"/>
	<script>var ctx = "${ctx}";</script>
	<link rel="icon" href="${ctx}/img/index/favicon.ico">
	<script type="text/JavaScript">
    // Courtesy of BoogieJack.com
    function killCopy(e){
        return false
    }
    function reEnable(){
        return true
    }
    document.onselectstart=new Function ("return false")
        if (window.sidebar){
        document.onmousedown=killCopy
        document.onclick=reEnable
    }
    </script>
</head>
<body>