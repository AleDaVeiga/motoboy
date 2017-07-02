<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:bind path="customer">
	<div class="form-group">
		<label class="control-label">Cliente</label>
		<form:select path="customer" class="form-control">
			<form:option value="0" label="Selecione" />
			<form:options items="${customerList}" itemValue="id" itemLabel="fullName" />
		</form:select>
		<form:errors path="customer"></form:errors>
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
		<form:input path="deliveryFrom" class="form-control" placeholder="Origem" autofocus="true"></form:input>
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
		<form:input path="price" class="form-control" placeholder="0.000,00"></form:input>
		<form:errors path="price"></form:errors>
	</div>
</spring:bind>
<spring:bind path="deliveryAt">
	<div class="form-group">
		<label class="control-label">Data da corrida</label>
		<form:input path="deliveryAt" class="form-control" placeholder="01/12/9999 24:59"></form:input>
		<form:errors path="deliveryAt"></form:errors>
	</div>
</spring:bind>