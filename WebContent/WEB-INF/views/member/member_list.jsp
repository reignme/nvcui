<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.nvc.ui.util.SecurityUtil;" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>

<div id="searchResult">
	<table id="groupList" class="nvcList">
		<tr class="nvcListHeader">
			<th>한글 이름</th>
			<th>영문 이름</th>
			<th>전화번호</th>
			<th>생년월일</th>
			<th>성별</th>
			<th>직분</th>
			<th>등록 상황</th>
			<% 
			if(!SecurityUtil.isUserInRole("ROLE_USER")) 
			{
			%>
			<th>관리자 option</th>
			<%
			}
			%>
		</tr>
		<c:set value="0" var="familyId" scope="request" />
		<c:set value="family_even" var="cssClass" scope="request" />
		
		<c:forEach items="${memberList}" var="member" varStatus="index">
			
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
				<td><c:out value="${member.firstName}" />&nbsp;<c:out value="${member.lastName}" /></td>
				<td><c:out value="${member.homePhone}" /></td>
				<td><c:out value="${member.birthDate}" /></td>
				<td><c:out value="${member.genderStr}" /></td>
				<td><c:out value="${member.roleStr}" /></td>
				<td><c:out value="${member.statusStr}" /></td>
				
				
				<% 
				if(!SecurityUtil.isUserInRole("ROLE_USER")) 
				{
				%>
					<c:if test="${member.roleId != 4}">
						<td> <a href="/nvcui/user/<c:out value="${member.memberId}" />">로그인 관리 </a></td>
					</c:if>
					<c:if test="${member.roleId == 4}">
						<td> -- </td>
					</c:if>
					
				<%
				}
				%>
				
			</tr>
			<c:set value="${member.familyId}" var="familyId" scope="request" />
		</c:forEach>
	</table>
</div>