<%--
 * message.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="folder/edit.do" modelAttribute="folder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="systemFolder"/>
	<form:hidden path="messages"/>
	
	<form:label path="name">
		<spring:message code="folder.name"/>
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br/>
	
	<form:label path="customFolder">
		<spring:message code="folder.customFolder" />:
	</form:label>
	
	<form:select path="customFolder">
		<jstl:forEach var="datos" items="${folders}">
			<form:option value="${datos.id}">
				<jstl:out value="${datos.name}"/>
			</form:option>
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="customFolder" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="folder.save" />" />&nbsp; 
	<jstl:if test="${folder.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="folder.delete" />"
			onclick="return confirm('<spring:message code="folder.confirm.delete" />')" />
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="folder.cancel" />"
		onclick="javascript: relativeRedir('/folder/list.do');" />
	<br />
</form:form>