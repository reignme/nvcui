<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>

<div id="formPanel">
	<div id="formFieldPanel">
		<form:form commandName="user" action="${pageContext.request.contextPath}/user/save">
			<div class="display_error"><form:errors path="username"/><!-- --></div>
			<div class="form_lable">User Name:</div>
			<div><form:input path="username" cssClass="input_text"/></div>
			
			<div class="display_error"><form:errors path="password"/><!-- --></div>
			<div class="form_lable">Password:</div>
			<div><form:password path="password" cssClass="input_text"/></div>
			
			<div class="display_error"></div>
			<div class="form_lable">Confirm Password:</div>
			<div><input type="password" name="confirm_password" class="input_text"/></div>
									
			<div class="form_lable">Status:</div>
			<div>
				<form:select path="status">
					<form:option value="1">Enable</form:option>
					<form:option value="0">Disable</form:option>
				</form:select>
			</div>
			
			<div class="form_lable">Role:</div>
			<div>
				<form:select path="authority">
					<form:option value="ROLE_USER">ROLE_USER</form:option>
					<form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>
				</form:select>
			</div>									
			<form:hidden path="memberId" />									
			<input name="submit" type="submit" title="submit" value="Submit"/>
		</form:form>
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    