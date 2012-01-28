<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>

<div style="margin-bottom:10px;">
	<a href="/nvcui/group/new">새로운 목장 만들기</a>
</div>

<table id="groupList" class="nvcList">

	<tr class="nvcListHeader">
		<th>목장이름</th>
		<th>마을장</th>
		<th>교실</th>
		<th>인원</th>
		<th>나이Group</th>
		<th>목자</th>
		<th>목자 전화번호</th>
		<th>이메일</th>
		<th>목원 리스트</th>
		<th>출석 체크하기</th>
	</tr>
	
<c:forEach items="${groupList}" var="grp" varStatus="index"> 
	<tr class="nvcListRow">
		<td><a href="/nvcui/group/edit/<c:out value="${grp.groupId}"/>"><c:out value="${grp.name}" /></a></td>
		<td><c:out value="${grp.communityHeadName}" /></td>
		<td><c:out value="${grp.roomNumber}" /></td>
		<td><c:out value="${grp.numberOfMember}" /></td>
		<td><c:out value="${grp.ageGroup}" /></td>
		<td><a href="/nvcui/member/edit/<c:out value="${grp.shepherdId}"/>"><c:out value="${grp.shepherdName}" /></a></td>
		<td><c:out value="${grp.shepherdPhoneNumber}" /></td>
		<td><c:out value="${grp.shepherdEmailAddress}" /></td>
		<td><a href="/nvcui/group/view/<c:out value="${grp.groupId}" />">목원들 보기</a></td>
		<td><a href="/nvcui/group/attendance/<c:out value="${grp.groupId}" />"/>출석</a></td>
	</tr>
</c:forEach>

</table>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />