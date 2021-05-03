<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manager.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="manager.task.list.label.startTime" path="startTime" width="20%"/>
	<acme:list-column code="manager.task.list.label.endTime" path="endTime" width="20%"/>
	<acme:list-column code="manager.task.list.label.workload" path="workload" width="20%"/>
</acme:list>


