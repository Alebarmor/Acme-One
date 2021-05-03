<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="manager.workPlan.form.label.startTime" path="startTime"/>
	<acme:form-moment code="manager.workPlan.form.label.endTime" path="endTime"/>
	<acme:form-checkbox code="manager.workPlan.form.label.isPublic" path="isPublic"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="manager.workPlan.form.button.create" action="/manager/task/create"/>
  	<acme:form-return code="manager.workPlan.form.button.return"/>
</acme:form>
