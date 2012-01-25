<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
	<jsp:param name="title" value="${title}"/>
</jsp:include>

<c:if test="${member.memberId != 0}">
	<div style="width:100%; height:40px;">
		<a class="button" href="/nvcui/member/new?<c:out value="${familyAttribute}" />">
		<span>가족 더하기</span>
		</a>
		<a class="button" href="/nvcui/member/family/<c:out value="${member.familyId}" />">
			<span>가족 보기</span>
		</a>
		<br/>
	</div>
</c:if>

<script type="text/javascript">

$(document).ready(function()
{
	$("#serviceTime").val(<c:out value="${serviceTime}" />);
	
	$("#serviceTime").change(function()
	{
		$.ajax
		({
			type : 'get',
			url : '/nvcui/group/groupAjax/'+ $(this).val(),
			dataType : 'html',
			async: false,
			success : function(html)
			{
				$("#groupId").html(html);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown)
			{
				alert("error");
			}
		});

		return false;
	});
});

function validate()
{
	var failed = false;
	if(document.forms[0].lastName.value == "")
	{
		alert("영문 성(Last Name)을 입력해 주세요");
		failed = true;
	}
	else if(document.forms[0].firstName.value == "")
	{
		alert("영문 이름(First Name)을 입력해 주세요");
		failed = true;
	}
	else if(document.forms[0].koreanName.value == "")
	{
		alert("한글 이름을 입력해 주세요");
		failed = true;
	}
	else if(document.forms[0].emailAddress.value == "")
	{
		alert("이메일을 입력해 주세요");
		failed = true;
	}
	else if(document.forms[0].birthYear.value == "")
	{
		alert("생일 연도를 입력해 주세요");
		failed = true;
	}
	else if(!IsNumeric(document.forms[0].birthYear.value))
	{
		alert("생일 연도는 숫자만 가능 합니다");
		failed = true;
	}
	else if(document.forms[0].homeArea.value == "")
	{
		alert("집 지역번호를 입력해 주세요");
		failed = true;
	}
	else if(!IsNumeric(document.forms[0].homeArea.value))
	{
		alert("집 지역번호는 숫자만 가능 합니다");
		failed = true;
	}
	else if(document.forms[0].homeNum1.value == "" || document.forms[0].homeNum2.value == "")
	{
		alert("집 전화번호를 입력해 주세요");
		failed = true;
	}
	else if(!IsNumeric(document.forms[0].homeNum1.value) || !IsNumeric(document.forms[0].homeNum2.value))
	{
		alert("집 전화번호는 숫자만 가능 합니다");
		failed = true;
	}
	else if(document.forms[0].cellArea.value == "")
	{
		alert("휴대폰 지역번호를 입력해 주세요");
		failed = true;
	}
	else if(!IsNumeric(document.forms[0].cellArea.value))
	{
		alert("휴대폰 지역번호는 숫자만 가능 합니다");
		failed = true;
	}
	else if(document.forms[0].cellNum1.value == "" || document.forms[0].cellNum2.value == "")
	{
		alert("휴대폰 전화번호를 입력해 주세요");
		failed = true;
	}
	else if(!IsNumeric(document.forms[0].cellNum1.value) || !IsNumeric(document.forms[0].cellNum2.value))
	{
		alert("휴대폰 전화번호는 숫자만 가능 합니다");
		failed = true;
	}
	else if($("#streetAddress").val() == "")
	{
		alert("주소  입력해 주세요.");
		failed = true;
	}
	else if($("#city").val() == "")
	{
		alert("도시를  입력해 주세요.");
		failed = true;
	}
	else if($("#zipcode").val() == "")
	{
		alert("Zipcode를 입력해 주세요.");
		failed = true;
	}
	
	return !failed;
}

</script>

<div id="formPanel">
	<div id="formFieldPanel">
		<form:form commandName="member" action="${pageContext.request.contextPath}/member/save" onSubmit="return validate();">
			<div class="display_error"><form:errors path="lastName"/><!-- --></div>
			<div class="form_lable">영문 성:</div>
			<div><form:input path="lastName" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="firstName"/><!-- --></div>
			<div class="form_lable">영문 이름:</div>
			<div><form:input path="firstName" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="koreanName"/><!-- --></div>
			<div class="form_lable">한글 이름:</div>
			<div><form:input path="koreanName" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="gender"/><!-- --></div>
			<div class="form_lable">Gender(성별):</div>
			<div>
				<form:select path="gender">
					<form:option value="0">여</form:option>
					<form:option value="1">남</form:option>
				</form:select>
			</div>
			<br>
			<div class="form_lable">가족 Role:</div>
			<div>
				<form:select path="familyRole">
					<form:option value="1">가장</form:option>
					<form:option value="2">배우자</form:option>
					<form:option value="3">자녀</form:option>
				</form:select>
			</div>
			<br>
			<div class="display_error"><form:errors path="emailAddress"/><!-- --></div>
			<div class="form_lable">Email Address:</div>
			<div><form:input path="emailAddress" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="birthDate"/><!-- --></div>
			<div class="form_lable">생일(YYYY-MM-DD):</div>
			<div>
				<form:input path="birthYear" cssClass="input_text" size="4"/>-
				<form:select path="birthMonth">
					<form:option value="01">1월</form:option>
					<form:option value="02">2월</form:option>
					<form:option value="03">3월</form:option>
					<form:option value="04">4월</form:option>
					<form:option value="05">5월</form:option>
					<form:option value="06">6월</form:option>
					<form:option value="07">7월</form:option>
					<form:option value="08">8월</form:option>
					<form:option value="09">9월</form:option>
					<form:option value="10">10월</form:option>
					<form:option value="11">11월</form:option>
					<form:option value="12">12월</form:option>
				</form:select>
				-
				<form:select path="birthDay">
					<form:option value="01">01</form:option>
					<form:option value="02">02</form:option>
					<form:option value="03">03</form:option>
					<form:option value="04">04</form:option>
					<form:option value="05">05</form:option>
					<form:option value="06">06</form:option>
					<form:option value="07">07</form:option>
					<form:option value="08">08</form:option>
					<form:option value="09">09</form:option>
					<form:option value="10">10</form:option>
					<form:option value="11">11</form:option>
					<form:option value="12">12</form:option>
					<form:option value="13">13</form:option>
					<form:option value="14">14</form:option>
					<form:option value="15">15</form:option>
					<form:option value="16">16</form:option>
					<form:option value="17">17</form:option>
					<form:option value="18">18</form:option>
					<form:option value="19">19</form:option>
					<form:option value="20">20</form:option>
					<form:option value="21">21</form:option>
					<form:option value="22">22</form:option>
					<form:option value="23">23</form:option>
					<form:option value="24">24</form:option>
					<form:option value="25">25</form:option>
					<form:option value="26">26</form:option>
					<form:option value="27">27</form:option>
					<form:option value="28">28</form:option>
					<form:option value="29">29</form:option>
					<form:option value="30">30</form:option>
					<form:option value="31">31</form:option>
				</form:select>
			</div>
			<br>
			<div class="form_lable">Home Phone(집 전화번호):</div>
			<div>
				<form:input path="homeArea" cssClass="input_text" size="3"/>-<form:input path="homeNum1" cssClass="input_text"  size="3"/>-<form:input path="homeNum2" cssClass="input_text"  size="4"/>
			</div>
			<br>
			<div class="form_lable">Work Phone(직장):</div>
			<div>
				<form:input path="workArea" cssClass="input_text" size="3"/>-<form:input path="workNum1" cssClass="input_text"  size="3"/>-<form:input path="workNum2" cssClass="input_text"  size="4"/>
			</div>
			<br>
			<div class="form_lable">Cell Phone(휴대폰):</div>
			<div>
				<form:input path="cellArea" cssClass="input_text" size="3"/>-<form:input path="cellNum1" cssClass="input_text"  size="3"/>-<form:input path="cellNum2" cssClass="input_text"  size="4"/>
			</div>
			</div>
			<br>
			<div class="display_error"><form:errors path="address.street"/><!-- --></div>
			<div class="form_lable">Street:</div>
			<div><form:input cssId="streetAddress" path="address.street" cssClass="input_text"/></div>
			<br>
			<div class="display_error"><form:errors path="address.city"/><!-- --></div>
			<div class="form_lable">City:</div>
			<div><form:input cssId="city" path="address.city" cssClass="input_text"/></div>
			<br/>
			<div class="display_error"><form:errors path="address.state"/><!-- --></div>
			<div class="form_lable">State:</div>
			<div>
				<form:select path="address.state">
					<form:option value="AL">Alabama</form:option> 
					<form:option value="AK">Alaska</form:option> 
					<form:option value="AZ">Arizona</form:option> 
					<form:option value="AR">Arkansas</form:option> 
					<form:option value="CA">California</form:option> 
					<form:option value="CO">Colorado</form:option> 
					<form:option value="CT">Connecticut</form:option> 
					<form:option value="DE">Delaware</form:option> 
					<form:option value="DC">District Of Columbia</form:option> 
					<form:option value="FL">Florida</form:option> 
					<form:option value="GA">Georgia</form:option> 
					<form:option value="HI">Hawaii</form:option> 
					<form:option value="ID">Idaho</form:option> 
					<form:option value="IL">Illinois</form:option> 
					<form:option value="IN">Indiana</form:option> 
					<form:option value="IA">Iowa</form:option> 
					<form:option value="KS">Kansas</form:option> 
					<form:option value="KY">Kentucky</form:option> 
					<form:option value="LA">Louisiana</form:option> 
					<form:option value="ME">Maine</form:option> 
					<form:option value="MD">Maryland</form:option> 
					<form:option value="MA">Massachusetts</form:option> 
					<form:option value="MI">Michigan</form:option> 
					<form:option value="MN">Minnesota</form:option> 
					<form:option value="MS">Mississippi</form:option> 
					<form:option value="MO">Missouri</form:option> 
					<form:option value="MT">Montana</form:option> 
					<form:option value="NE">Nebraska</form:option> 
					<form:option value="NV">Nevada</form:option> 
					<form:option value="NH">New Hampshire</form:option> 
					<form:option value="NJ">New Jersey</form:option> 
					<form:option value="NM">New Mexico</form:option> 
					<form:option value="NY">New York</form:option> 
					<form:option value="NC">North Carolina</form:option> 
					<form:option value="ND">North Dakota</form:option> 
					<form:option value="OH">Ohio</form:option> 
					<form:option value="OK">Oklahoma</form:option> 
					<form:option value="OR">Oregon</form:option> 
					<form:option value="PA">Pennsylvania</form:option> 
					<form:option value="RI">Rhode Island</form:option> 
					<form:option value="SC">South Carolina</form:option> 
					<form:option value="SD">South Dakota</form:option> 
					<form:option value="TN">Tennessee</form:option> 
					<form:option value="TX">Texas</form:option> 
					<form:option value="UT">Utah</form:option> 
					<form:option value="VT">Vermont</form:option> 
					<form:option value="VA">Virginia</form:option> 
					<form:option value="WA">Washington</form:option> 
					<form:option value="WV">West Virginia</form:option> 
					<form:option value="WI">Wisconsin</form:option> 
					<form:option value="WY">Wyoming</form:option>
				</form:select>
			</div>
			<br>
			<div class="display_error"><form:errors path="address.zipCode"/><!-- --></div>
			<div class="form_lable">Zipcode:</div>
			<div><form:input cssId="zipcode" path="address.zipCode" cssClass="input_text"/></div>
			<br>
			<div class="form_lable">직분:</div>
			<form:select path="roleId" cssClass="input_text">
        		<form:options items="${roleList}" itemValue="roleId" itemLabel="name"/>
        	</form:select>
        	<br>
        	
        	<div class="form_lable">목장:</div>
        	<select id="serviceTime" name="serviceTime">
        		<option value="1">1부</option>
        		<option value="2">2부</option>
        		<option value="3">3부</option>
        		<option value="4">4부</option>
        	</select>
        	
			<form:select path="groupId" cssClass="input_text">
				<form:option value="0">미정</form:option>
        		<form:options items="${groupList}" itemValue="groupId" itemLabel="name"/>
        	</form:select>
        	<br>
			<br>
			<!-- 등록?  -->
			<div class="form_lable">등록?:</div> 
			<form:radiobutton path="status" value="1" label="Register" /> 
			<form:radiobutton path="status" value="0" label="Not decided" />
			<form:radiobutton path="status" value="2" label="Visiting" />
			<br>
			<br>			
			<!-- 침럐/세레?  -->
			<div class="form_lable">세례를 받았습니까?:</div> 
			<form:radiobutton path="baptised" value="1" label="yes" /> 
			<form:radiobutton path="baptised" value="0" label="no" />
			<br>			
			<br>
			<!-- 신앙의 확신?  -->
			<div class="form_lable">구원의 확신이 있습니까?:</div> 
			<form:radiobutton path="religiousConviction" value="1" label="yes" /> 
			<form:radiobutton path="religiousConviction" value="0" label="no" />
			<br>
			<br>
			<form:hidden path="address.country" value="US"/>
			<form:hidden path="memberId"/>
			<form:hidden path="familyId"/>
			<input type="hidden" value="${type}" />
			
			<input name="submit" type="submit" title="submit" value="Submit"/>
		</form:form>
	</div>
	
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
    