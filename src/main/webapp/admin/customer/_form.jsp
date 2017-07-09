<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<ul class="nav nav-tabs">
	<li class="active">
		<a href="#customerFormInfo" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> Informações</a>
	</li>
	<li>
		<a href="#customerFormAdd" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> Endereço</a>
	</li>		
</ul>
<div class="tab-content clearfix">
	<div class="tab-pane active" id="customerFormInfo">
		<form:hidden path="id" class="form-control"/>
		<spring:bind path="fullName">
			<div class="form-group">
				<label class="control-label">Nome</label>
				<form:input path="fullName" class="form-control" placeholder="Nome" autofocus="true"></form:input>
				<form:errors path="fullName"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="document">
			<div class="form-group">
				<label class="control-label">CNPJ/CPF</label>
				<form:input path="document" class="form-control" placeholder="CNPJ/CPF" autofocus="true"></form:input>
				<form:errors path="document"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="phones">
			<div class="form-group">
				<label class="control-label">Telefone</label>
				<form:input path="phones" class="form-control" placeholder="Telefone" autofocus="true"></form:input>
				<form:errors path="phones"></form:errors>
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
	</div>
	<div class="tab-pane" id="customerFormAdd">
		<spring:bind path="customerAddress.street">
			<div class="form-group">
				<label class="control-label">Endereço</label>
				<form:input path="customerAddress.street" class="form-control" placeholder="Endereço" autofocus="true"></form:input>
				<form:errors path="customerAddress.street"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="numberAddress">
			<div class="form-group">
				<label class="control-label">Número</label>
				<form:input path="numberAddress" class="form-control" placeholder="Número" autofocus="true"></form:input>
				<form:errors path="numberAddress"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="complementAddress">
			<div class="form-group">
				<label class="control-label">Complemento</label>
				<form:input path="complementAddress" class="form-control" placeholder="Complemento" autofocus="true"></form:input>
				<form:errors path="complementAddress"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.district">
			<div class="form-group">
				<label class="control-label">Bairro</label>
				<form:input path="customerAddress.district" class="form-control" placeholder="Bairro" autofocus="true"></form:input>
				<form:errors path="customerAddress.district"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.city">
			<div class="form-group">
				<label class="control-label">Cidade</label>
				<form:input path="customerAddress.city" class="form-control" placeholder="Cidade" autofocus="true"></form:input>
				<form:errors path="customerAddress.city"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.state">
			<div class="form-group">
				<label class="control-label">Estado</label>
				<form:select path="customerAddress.state" class="form-control">
					<form:option value="0" label="Selecione" />
					<form:options items="${stateList}" itemValue="id" itemLabel="stateName" />
				</form:select>
				<form:errors path="customerAddress.state"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.zipCode">
			<div class="form-group">
				<label class="control-label">CEP</label>
				<form:input path="customerAddress.zipCode" class="form-control" placeholder="CEP" autofocus="true"></form:input>
				<form:errors path="customerAddress.zipCode"></form:errors>
			</div>
		</spring:bind>
	</div>
</div>
<div class="text-right">
	<a href="${contextPath}/admin/#cadastre" class="btn btn-default">
		<span class="glyphicon glyphicon-home"></span>
	</a>
	<a href="${contextPath}/admin/customers" class="btn btn-default">
		<span class="glyphicon glyphicon-th-list"></span>
	</a>
	<button type="submit" class="btn btn-success">
		<span class="glyphicon glyphicon-floppy-disk"></span>
	</button>
</div>