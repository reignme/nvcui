<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page import="org.nvc.ui.util.SecurityUtil;" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="Content-Script-Type" content="text/javascript" /> 
	<meta http-equiv="Content-Style-Type" content="text/css" /> 
	
	<head>
		<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/js/jquery/jquery-1.4.2.min.js"/>"></script>
		<title>${param.title}</title>
	</head>
	
	<body>
		<div id="bodyWrapper">
			<header>
				<nav>
					<h1>
						<a href="/nvcui"><img src="<%= request.getContextPath() %>/img/nvc_logo.gif" style="position:relative; top:-15px;" /></a>
					</h1>
					<ul>
						<li>
							<a href="/nvcui/member/new">교인입력</a>
						</li>
						
						<li>
							<a href="/nvcui/member/search">교인찾기</a>
						</li>
						<% 
						if(!SecurityUtil.isUserInRole("ROLE_USER")) 
						{
						%>
							<li>
								<a href="/nvcui/group/list">목장리스트</a>
							</li>
						<%
						} 
						else
						{
						%>
							<li>
								<a href="/nvcui/group/view/myGroup">내목장</a>
							</li>
							<li>
								<a href="/nvcui/group/myAttendance">출석 체크</a>
							</li>
						<%
						} 
						%>
						
						
						
						<li>
							<a href="/nvcui/j_spring_security_logout">Logout</a>
						</li>
					</ul>
				</nav>
			</header>
			<div id="main">