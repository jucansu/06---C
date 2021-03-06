<%--
 * register_Sponsor.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="administrator/register_Sponsor.do"
	modelAttribute="sponsor">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities"/>
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="socialId"/>
	<form:hidden path="suspicious" />

	<form:hidden path="sponsorship" />

	<form:label path="name">
		<spring:message code="sponsor.name" />:
		</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="sponsor.surname" />:
		</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="sponsor.email" />:
		</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phoneNumber">
		<spring:message code="sponsor.phoneNumber" />:
		</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />

	<form:label path="address">
		<spring:message code="sponsor.address" />:
		</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="userAccount.username">
		<spring:message code="sponsor.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors class="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="sponsor.password" />
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors class="error" path="userAccount.password" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
</form:form>