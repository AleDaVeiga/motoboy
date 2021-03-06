<%@ page contentType="text/html;charset=UTF-8"%>
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
<spring:bind path="customer">
	<div class="form-group">
		<label class="control-label">Cliente</label>
		<div class="input-group">
			<form:select path="customer" class="selectpicker form-control">
				<form:option value="0" label="Selecione" />
				<form:options items="${customerList}" itemValue="id" itemLabel="fullName" autofocus="true"/>
			</form:select>
			<span class="input-group-btn">
				<a href="${contextPath}/admin/customer/" class="btn btn-success">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</a>
			</span>
			<form:errors path="customer"></form:errors>
		</div>
	</div>
</spring:bind>
<spring:bind path="status">
	<div class="form-group">
		<label class="control-label">Tipo</label><br>
		<label class="radio-inline"><form:radiobutton path="status" value="false"/>Solicitado</label>
		<label class="radio-inline"><form:radiobutton path="status" value="true"/>Aceito</label>
		<form:errors path="status"></form:errors>
	</div>
</spring:bind>
<spring:bind path="deliveryFrom">
	<div class="form-group">
		<label class="control-label">Origem</label>
		<form:input path="deliveryFrom" class="form-control" placeholder="Origem"></form:input>
		<form:errors path="deliveryFrom"></form:errors>
	</div>
</spring:bind>
<spring:bind path="deliveryTo">
	<div class="form-group">
		<label class="control-label">Destino</label>
		<form:input path="deliveryTo" class="form-control" placeholder="Destino"></form:input>
		<form:errors path="deliveryTo"></form:errors>
	</div>
</spring:bind>
<spring:bind path="deliveredBy">
	<div class="form-group">
		<label class="control-label">Motoboy</label>
		<form:select path="deliveredBy" class="form-control">
			<form:option value="0" label="Selecione" />
			<form:options items="${deliveryManList}" itemValue="id" itemLabel="fullName" />
		</form:select>
		<form:errors path="deliveredBy"></form:errors>
	</div>
</spring:bind>
<spring:bind path="price">
	<div class="form-group">
		<label class="control-label">Valor</label>
		<form:input path="price" class="form-control"></form:input>
		<form:errors path="price"></form:errors>
	</div>
</spring:bind>
<spring:bind path="paymentMethod">
	<div class="form-group">
		<label class="control-label">Pagamento</label>
		<form:select path="paymentMethod" class="form-control">
			<form:option value="0" label="Selecione" />
			<form:options items="${paymentMethodList}" itemValue="id" itemLabel="description" />
		</form:select>
		<form:errors path="deliveredBy"></form:errors>
	</div>
</spring:bind>
<spring:bind path="deliveryAt">
	<div class="form-group">
		<label class="control-label">Data da corrida</label>
		<form:input type="date" path="deliveryAt" class="form-control"></form:input>
		<form:errors path="deliveryAt"></form:errors>
	</div>
</spring:bind>
<div class="text-right">
	<a href="${contextPath}/admin/#cadastre" class="btn btn-default">
		<span class="glyphicon glyphicon-home"></span>
	</a>
	<a href="${contextPath}/admin/deliveries" class="btn btn-default">
		<span class="glyphicon glyphicon-th-list"></span>
	</a>
	<button type="submit" class="btn btn-success">
		<span class="glyphicon glyphicon-floppy-disk"></span>
	</button>
</div>