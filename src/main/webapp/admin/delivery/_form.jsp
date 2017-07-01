<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:bind path="delivery_from">
	<div class="form-group">
		<label class="control-label">Origem</label>
		<form:input path="delivery_from" class="form-control" placeholder="Origem" autofocus="true"></form:input>
		<form:errors path="delivery_from"></form:errors>
	</div>
</spring:bind>
<spring:bind path="delivery_to">
	<div class="form-group">
		<label class="control-label">Destino</label>
		<form:input path="delivery_to" class="form-control" placeholder="Destino"></form:input>
		<form:errors path="delivery_to"></form:errors>
	</div>
</spring:bind>
<spring:bind path="delivery_to">
	<div class="form-group">
		<label class="control-label">Motoboy</label>
		<form:select path="delivery_to" class="form-control">
			<form:option value="0" label="Selecione" />
			<form:options items="${deliveryManList}" itemValue="id" itemLabel="name" />
		</form:select>
		<form:errors path="delivery_to"></form:errors>
	</div>
</spring:bind> 
<spring:bind path="value">
	<div class="form-group">
		<label class="control-label">Valor</label>
		<form:input path="value" class="form-control" placeholder="Valor"></form:input>
		<form:errors path="value"></form:errors>
	</div>
</spring:bind>
<spring:bind path="delivery_at">
	<div class="form-group">
		<label class="control-label">Data da corrida</label>
		<form:input path="delivery_at" class="form-control" placeholder="Data da corrida"></form:input>
		<form:errors path="delivery_at"></form:errors>
	</div>
</spring:bind>
<form:hidden path="created_at" class="form-control"/>
<form:hidden path="updated_at" class="form-control"/>