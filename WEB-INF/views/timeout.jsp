<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="Session Expired"/>
</jsp:include>
	
<div>
	Your session has bee expired.<br/>
	<a href="/nvcui/login">Please login again</a>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />