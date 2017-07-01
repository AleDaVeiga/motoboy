<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:bind path="name">
	<div class="form-group">
		<label class="control-label">Nome</label>
		<form:input path="name" class="form-control" placeholder="Nome" autofocus="true"></form:input>
		<form:errors path="name"></form:errors>
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
<form:hidden path="created_at" class="form-control"/>
<form:hidden path="updated_at" class="form-control"/>