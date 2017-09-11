<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Motoboy - Controle das informações</title>
		<jsp:include page="../../templates/header.jsp"/>
	</head>
    <body>
		<jsp:include page="../../templates/menu_consult.jsp"/>
		<div class="container">
            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Filtrar relatório de corridas agrupado por clientes
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form:form method="POST" target="_blank" modelAttribute="reportDeliveryByCustomerForm" class="form-signin" action="${contextPath}/admin/report/deliveries/customers">
                        	<spring:bind path="startDeliveryAt">
                        		<div class="form-group">
                        			<label class="control-label">Data inicial</label>
                        			<form:input type="date" path="startDeliveryAt" class="form-control"></form:input>
                        			<form:errors path="startDeliveryAt"></form:errors>
                        		</div>
                        	</spring:bind>
                        	<spring:bind path="endDeliveryAt">
                        		<div class="form-group">
                        			<label class="control-label">Data final</label>
                        			<form:input type="date" path="endDeliveryAt" class="form-control"></form:input>
                        			<form:errors path="endDeliveryAt"></form:errors>
                        		</div>
                        	</spring:bind>
                        	<spring:bind path="status">
                        		<div class="form-group">
                        			<label class="control-label">Tipo</label><br>
                        			<label class="radio-inline"><form:radiobutton path="status" value="0"/>Todos</label>
                        			<label class="radio-inline"><form:radiobutton path="status" value="-1"/>Solicitado</label>
                        			<label class="radio-inline"><form:radiobutton path="status" value="1"/>Aceito</label>
                        			<form:errors path="status"></form:errors>
                        		</div>
                        	</spring:bind>
	                        <div class="text-right">
	                        	<a href="${contextPath}/admin/#report" class="btn btn-default">
	                        		<span class="glyphicon glyphicon-home"></span>
	                        	</a>
	                        	<button type="submit" class="btn btn-success">
	                        		<span class="glyphicon glyphicon-print"></span>
	                        	</button>
	                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
	    <jsp:include page="../../templates/footer.jsp"/>
	</body>
</html>