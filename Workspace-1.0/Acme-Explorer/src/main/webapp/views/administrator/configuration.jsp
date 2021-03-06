<%--
 * configuration.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="administrator/configuration.do" modelAttribute="configuration">
	
	<security:authorize access="hasRole('ADMIN')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="message" />
	<form:hidden path="catalogueTag" />
	<form:hidden path="treeCategory" />
	<form:hidden path="catalogueText" />
	<form:hidden path="other" />
	
	<form:label path="cache">
		<spring:message code="administrator.cache" />:
	</form:label>
	<form:input path="cache" />
	<form:errors cssClass="error" path="cache" />
	<br />
	
	<form:label path="numberPage">
		<spring:message code="administrator.numberPage" />:
	</form:label>
	<form:input path="numberPage" />
	<form:errors cssClass="error" path="numberPage" />
	<br />
	
	<form:label path="spamWords">
		<spring:message code="administrator.spamWords" />:
	</form:label>
	<form:input path="spamWords" />
	<form:errors cssClass="error" path="spamWords" />
	<br />
	
	<form:label path="tax">
		<spring:message code="administrator.tax" />:
	</form:label>
	<form:input path="tax" />
	<form:errors cssClass="error" path="tax" />
	<br />
	
	<form:label path="countryCode">
		<spring:message code="administrator.countryCode" />:
	</form:label>
	<form:input path="countryCode" />
	<form:errors cssClass="error" path="countryCode" />
	<br />
	
	<form:label path="banner">
		<spring:message code="administrator.banner" />:
	</form:label>
	<form:input path="banner"/>
	<form:errors cssClass="error" path="banner" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />&nbsp; 
	
	
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('/administrator/configuration.do');" />
	<br />

	</security:authorize>
</form:form>