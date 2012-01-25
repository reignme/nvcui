<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>


<div id="formPanel">
	<div id="formFieldPanel">
		<form:form commandName="group" action="${pageContext.request.contextPath}/group/save">
			<div class="display_error"><form:errors path="name"/><!-- --></div>
			<div class="form_lable">Name(이름):</div>
			<div><form:input path="name" cssClass="input_text"/></div>
			<br>
			<div class="form_lable">마을:</div>
			<form:select path="communityId" cssClass="input_text">
        		<form:options items="${communityList}" itemValue="communityId" itemLabel="name"/>
        	</form:select>
        	<br>
        	<br>
			<div class="display_error"><form:errors path="roomNumber"/><!-- --></div>
			<div class="form_lable">교실:</div>
			<div><form:input path="roomNumber" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="ageGroup"/><!-- --></div>
			<div class="form_lable">서비스 시간:</div>
			<div>
				<form:select path="serviceTime" cssClass="input_text">
					<form:option  value="1" label="1부" />
					<form:option  value="2" label="2부" />
					<form:option  value="3" label="3부" />
				</form:select>
			</div>
			<br>
			<form:hidden path="groupId" />
			
			<input name="submit" type="submit" title="submit" value="Submit"/>
		</form:form>
	</div>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />	