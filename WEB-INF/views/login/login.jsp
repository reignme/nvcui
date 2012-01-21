<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header2.jsp" >
	<jsp:param name="title" value="${title }"/>
</jsp:include>

<div id="loginpanel">
	
	<form name='f' action='/nvcui/j_spring_security_check' method='POST'>
		<c:if test="${not empty param.login_error}">
  	    <div id="error_div">
       	<P style="color:red;">Your login attempt was not successful, please try again.<br/>
       	Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.</P>
    	</div>
 		</c:if>
		<div class="form_lable">Login:</div>
		<div>
			<input type='text' name='j_username' value=''/>
		</div>
		<div class="form_lable">Password:</div>
		<div>
			<input type='password' name='j_password'/>
		</div>
		<div>
			<input type='checkbox' name='_spring_security_remember_me'/>로그인 유지
		</div>
    	<input name="submit" type="submit" title="submit" value="Submit"/>
    </form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />