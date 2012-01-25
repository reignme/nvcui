<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>


<div id="groupInfo">
	<span>목장 이름: </span><span><c:out value="${group.name}" /></span><br/>
</div>

<table id="groupList" class="nvcList">	
	
	<tr class="nvcListHeader">
		<th>이름</th>
		<th>전화번호</th>
		<th>성별</th>
		<th>직분</th>
	</tr>

	<%
		int familyId = 0; 
		String cssClass="family_even";
	%>
	
	<c:set value="0" var="familyId" scope="request" />
	<c:set value="family_even" var="cssClass" scope="request" />
	
	<c:forEach items="${members}" var="member" varStatus="index">
		
		<c:if test="${member.familyId != familyId }">
			<c:choose>
				<c:when test="${cssClass == 'family_even'}">
					<c:set value="family_odd" var="cssClass" scope="request" />
				</c:when>
				<c:otherwise>
					<c:set value="family_even" var="cssClass" scope="request" />
				</c:otherwise>
			</c:choose>
		</c:if>
		 
		<tr class='nvcListRow <c:out value="${cssClass}" />'>
			<td><a href="/nvcui/member/edit/<c:out value="${member.memberId}" />"><c:out value="${member.koreanName}" /></a></td>
			<td><c:out value="${member.homePhone}" /></td>
			<td><c:out value="${member.genderStr}" /></td>
			<td><c:out value="${member.roleStr}" /></td>
		</tr>
		
		<c:set value="${member.familyId}" var="familyId" scope="request" />
		
	</c:forEach>
	
</table>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />	