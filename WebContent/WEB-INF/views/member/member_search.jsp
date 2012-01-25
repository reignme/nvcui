<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title }"/>
</jsp:include>

<div id="searchPanel">
	<div id="searchFieldPanel">
		<form:form commandName="memberSearch" action="${pageContext.request.contextPath}/member/search/process">
			<div class="display_error"><form:errors path="name"/><!-- --></div>
			<div class="form_lable"><form:radiobutton path="searchField" value="name"/>이름(name):</div>
			<div><form:input path="name" cssClass="input_text"/></div>
			<br>
			<div class="form_lable"><form:radiobutton path="searchField" value="registered"/>미등록 교인</div>
			<br>
			<input name="submit" type="submit" title="submit" value="Submit"/>
		</form:form>
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    