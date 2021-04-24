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

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-workplan-public"/>
		</th>
		<td>
			<acme:print value="${numberOfWorkPlansPublic}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-workplan-private"/>
		</th>
		<td>
			<acme:print value="${numberOfWorkPlansPrivate}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-workplan-finished"/>
		</th>
		<td>
			<acme:print value="${numberOfWorkPlansFinished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-workplan-unfinished"/>
		</th>
		<td>
			<acme:print value="${numberOfWorkPlansUnfinished}"/>
		</td>
	</tr>			
</table>