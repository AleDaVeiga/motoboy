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
		<label class="control-label">CNPJ/CPF</label>
		<form:input path="document" class="form-control" placeholder="CNPJ/CPF" autofocus="true"></form:input>
		<form:errors path="document"></form:errors>
	</div>
</spring:bind>
<spring:bind path="street">
	<div class="form-group">
		<label class="control-label">Endereço</label>
		<form:input path="street" class="form-control" placeholder="Endereço" autofocus="true"></form:input>
		<form:errors path="street"></form:errors>
	</div>
</spring:bind>
<spring:bind path="number">
	<div class="form-group">
		<label class="control-label">Número</label>
		<form:input path="number" class="form-control" placeholder="Número" autofocus="true"></form:input>
		<form:errors path="number"></form:errors>
	</div>
</spring:bind>
<spring:bind path="complement">
	<div class="form-group">
		<label class="control-label">Complemento</label>
		<form:input path="complement" class="form-control" placeholder="Complemento" autofocus="true"></form:input>
		<form:errors path="complement"></form:errors>
	</div>
</spring:bind>
<spring:bind path="district">
	<div class="form-group">
		<label class="control-label">Bairro</label>
		<form:input path="district" class="form-control" placeholder="Bairro" autofocus="true"></form:input>
		<form:errors path="district"></form:errors>
	</div>
</spring:bind>
<spring:bind path="city">
	<div class="form-group">
		<label class="control-label">Cidade</label>
		<form:input path="city" class="form-control" placeholder="Cidade" autofocus="true"></form:input>
		<form:errors path="city"></form:errors>
	</div>
</spring:bind>
<spring:bind path="country">
	<div class="form-group">
		<label class="control-label">Estado</label>
		<form:input path="country" class="form-control" placeholder="Estado" autofocus="true"></form:input>
		<form:errors path="country"></form:errors>
	</div>
</spring:bind>
<spring:bind path="postal_code">
	<div class="form-group">
		<label class="control-label">CEP</label>
		<form:input path="postal_code" class="form-control" placeholder="CEP" autofocus="true"></form:input>
		<form:errors path="postal_code"></form:errors>
	</div>
</spring:bind>
<spring:bind path="phone">
	<div class="form-group">
		<label class="control-label">Telefone</label>
		<form:input path="phone" class="form-control" placeholder="Telefone" autofocus="true"></form:input>
		<form:errors path="phone"></form:errors>
	</div>
</spring:bind>
<spring:bind path="mobile_phone">
	<div class="form-group">
		<label class="control-label">Celular</label>
		<form:input path="mobile_phone" class="form-control" placeholder="Celular" autofocus="true"></form:input>
		<form:errors path="mobile_phone"></form:errors>
	</div>
</spring:bind>
<spring:bind path="email">
	<div class="form-group">
		<label class="control-label">E-mail</label>
		<form:input path="email" class="form-control" placeholder="E-mail" autofocus="true"></form:input>
		<form:errors path="email"></form:errors>
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