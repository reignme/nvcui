<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


	<option value="0">미정</option>
	
	<c:forEach items="${groupList}" var="grp" varStatus="index"> 
		<option value="<c:out value="${grp.groupId}"/>"><c:out value="${grp.name}" /></option>
	</c:forEach>


