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
				<form:input path="fullName" class="form-control" placeholder="Nome" required="true"></form:input>
				<form:errors path="fullName"></form:errors>
			</div>
		</spring:bind>
		<fieldset ${0 < customerForm.customerAccess.id ? 'disabled' : ''}>
			<spring:bind path="customerAccess.username">
				<div class="form-group">
					<label class="control-label">Conta de acesso do usuário</label>
					<form:input path="customerAccess.username" class="form-control" placeholder="Usuário"></form:input>
					<form:errors path="customerAccess.username"></form:errors>
				</div>
			</spring:bind>
		</fieldset>
		<spring:bind path="document">
			<div class="form-group">
				<label class="control-label">CNPJ/CPF</label>
				<form:input path="document" class="form-control" placeholder="CNPJ/CPF"></form:input>
				<form:errors path="document"></form:errors>
			</div>
		</spring:bind>
		<c:set var="count" value="0" scope="page" />
		<c:forEach var="phone" items="${customerForm.phones}">
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
		<spring:bind path="email">
			<div class="form-group">
				<label class="control-label">E-mail</label>
				<form:input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" path="email" class="form-control" placeholder="E-mail"></form:input>
				<form:errors path="email"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="note">
			<div class="form-group">
				<label class="control-label">Observação</label>
				<form:input path="note" class="form-control" placeholder="Observação"></form:input>
				<form:errors path="note"></form:errors>
			</div>
		</spring:bind>
	</div>
	<div class="tab-pane" id="customerFormAdd">
		<spring:bind path="customerAddress.street">
			<div class="form-group">
				<label class="control-label">Endereço</label>
				<form:input path="customerAddress.street" class="form-control" placeholder="Endereço"></form:input>
				<form:errors path="customerAddress.street"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="numberAddress">
			<div class="form-group">
				<label class="control-label">Número</label>
				<form:input path="numberAddress" class="form-control" placeholder="Número"></form:input>
				<form:errors path="numberAddress"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="complementAddress">
			<div class="form-group">
				<label class="control-label">Complemento</label>
				<form:input path="complementAddress" class="form-control" placeholder="Complemento"></form:input>
				<form:errors path="complementAddress"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.district">
			<div class="form-group">
				<label class="control-label">Bairro</label>
				<form:input path="customerAddress.district" class="form-control" placeholder="Bairro"></form:input>
				<form:errors path="customerAddress.district"></form:errors>
			</div>
		</spring:bind>
		<spring:bind path="customerAddress.city">
			<div class="form-group">
				<label class="control-label">Cidade</label>
				<form:input path="customerAddress.city" class="form-control" placeholder="Cidade"></form:input>
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
				<form:input pattern="^\\d{5}[-]\\d{3}$" data-mask="00000-000" path="customerAddress.zipCode" class="form-control" placeholder="CEP"></form:input>
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