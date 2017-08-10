<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:if test="${messageSuccess != null}">
	<div class="alert alert-success alert-dismissable">
		<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
		<strong>Sucesso!</strong> ${messageSuccess}
	</div>
</c:if>
<c:if test="${messageError != null}">
	<div class="alert alert-danger alert-dismissable">
		<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
		<strong>Erro!</strong> ${messageError}
	</div>
</c:if>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<spring:bind path="fullName">
	<div class="form-group">
		<label class="control-label">Nome</label>
		<form:input path="fullName" class="form-control" placeholder="Nome"></form:input>
		<form:errors path="fullName"></form:errors>
	</div>
</spring:bind>
<spring:bind path="document">
	<div class="form-group">
		<label class="control-label">Documento</label>
		<form:input path="document" class="form-control" placeholder="Documento"></form:input>
		<form:errors path="document"></form:errors>
	</div>
</spring:bind>
<spring:bind path="note">
	<div class="form-group">
		<label class="control-label">Observação</label>
		<form:input path="note" class="form-control" placeholder="Observação"></form:input>
		<form:errors path="note"></form:errors>
	</div>
</spring:bind>
<c:set var="count" value="0" scope="page" />
<c:forEach var="phone" items="${deliveryManForm.phones}">
	<spring:bind path="phones[${count}]">
		<div class="form-group">
			<label class="control-label">Telefone ${count + 1}</label>
			<div class="input-group">
				<form:input data-mask="(00) 90000-0000" path="phones[${count}]" class="form-control" placeholder="Telefone"></form:input>
				<span class="input-group-btn">
					<a class="btn btn-danger btn-block exclude-me">
						<span class="glyphicon glyphicon-minus"></span>
					</a>
				</span>
			</div>
			<form:errors path="phones[${count}]"></form:errors>
		</div>
	</spring:bind>
	<c:set var="count" value="${count + 1}" scope="page"/>
</c:forEach>
<div class="form-group">
	<input type="hidden" name="count" value="${count}" />
	<label class="control-label">Telefone ${count + 1}</label>
	<div class="input-group">
		<input id="phones${count}" name="phones[${count}]" placeholder="Telefone" data-mask="(00) 90000-0000" class="form-control" type="text"/>
		<span class="input-group-btn">
			<a class="btn btn-success btn-block add-more">
				<span class="glyphicon glyphicon-plus"></span>
			</a>
		</span>
	</div>
</div>
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