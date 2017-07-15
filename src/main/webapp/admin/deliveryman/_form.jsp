<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<spring:bind path="fullName">
	<div class="form-group">
		<label class="control-label">Nome</label>
		<form:input path="fullName" class="form-control" placeholder="Nome" autofocus="true"></form:input>
		<form:errors path="fullName"></form:errors>
	</div>
</spring:bind>
<spring:bind path="document">
	<div class="form-group">
		<label class="control-label">Documento</label>
		<form:input path="document" class="form-control" placeholder="Documento" autofocus="true"></form:input>
		<form:errors path="document"></form:errors>
	</div>
</spring:bind>
<spring:bind path="note">
	<div class="form-group">
		<label class="control-label">Observação</label>
		<form:input path="note" class="form-control" placeholder="Observação" autofocus="true"></form:input>
		<form:errors path="note"></form:errors>
	</div>
</spring:bind>
<spring:bind path="phones">
	<div class="form-group">
		<label class="control-label">Telefone</label>
		<form:input path="phones" class="form-control" placeholder="Telefone" autofocus="true"></form:input>
		<form:errors path="phones"></form:errors>
	</div>
</spring:bind>
<div class="text-right">
	<a href="${contextPath}/admin/#cadastre" class="btn btn-default">
		<span class="glyphicon glyphicon-home"></span>
	</a>
	<a href="${contextPath}/admin/deliverymans" class="btn btn-default">
		<span class="glyphicon glyphicon-th-list"></span>
	</a>
	<button type="submit" class="btn btn-success">
		<span class="glyphicon glyphicon-floppy-disk"></span>
	</button>
</div>