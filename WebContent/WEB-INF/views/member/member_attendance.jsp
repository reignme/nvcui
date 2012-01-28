<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.nvc.ui.util.SecurityUtil;" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>

<div>
	<a href="/nvcui/group/attendance/<c:out value="${group.groupId}"/>/?date=<c:out value="${previousSunday}"/>"> << 저번주로 가기</a>
	
	<a href="/nvcui/group/attendance/<c:out value="${group.groupId}"/>/?date=<c:out value="${nextSunday}"/>"> 다음주로 가기 >></a>
	
</div>

<div id="searchResult">
	<table id="groupList" class="nvcList">
		<tr class="nvcListHeader">
			<th>이름</th>			
			<th>
				출석 날짜: <c:out value="${targetDate}" />
			</th>			
		</tr>
		<c:set value="0" var="familyId" scope="request" />
		<c:set value="family_even" var="cssClass" scope="request" />
		<form:form commandName="members" action="${pageContext.request.contextPath}/group/attendance/save">
			<input type="hidden" name="groupId" value="${group.groupId}" />
			<input type="hidden" name="date" value="${targetDate}" />
			<c:forEach items="${members}" var="member" varStatus="index">				
				
			 	<tr class='nvcListRow'>
					<td><a href="/nvcui/member/edit/<c:out value="${member.memberId}" />"><c:out value="${member.memberName}" /></a></td>					
					<td>
						출석 <input type="radio" name="attendanceDate_${member.memberId}"  <c:out value="${(member.attendanceDate != null)?'checked':''}" />  value="yes"/>
						결석 <input type="radio"  name="attendanceDate_${member.memberId}" <c:out value="${(member.attendanceDate == null)?'checked':''}" />  value="no" />
					</td>				
				</tr>
			</c:forEach>
			<input name="submit" type="submit" title="submit" value="Submit"/>
		</form:form>
	</table>
</div>