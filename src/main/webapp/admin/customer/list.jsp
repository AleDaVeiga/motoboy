<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Motoboy - Controle das informações</title>
		<jsp:include page="../templates/header.jsp"/>
	</head>
    <body>
		<jsp:include page="../templates/menu.jsp"/>
		<div class="container">
	        <div class="row">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title">
	                        Administração de clientes
	                    </h3>
	                </div>
	                <div class="panel-body">
	                    <div class="row">
		                	<form id="customerSearchForm" method="GET" action="${contextPath}/admin/customers">
			                	<div class="form-group">
			                		<div class="input-group input-sm">
			                			<input class="form-control input-md" type="text" placeholder="Buscar por nome do cliente..." name="search" value="${param.search}">
			                			<a class="input-group-addon" onclick="document.forms['customerSearchForm'].submit()">
			                				<span class="glyphicon glyphicon-search"></span>
			                			</a>
			                		</div>
			                	</div>
		                	</form>
	                	</div>
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-condensed table-striped">
	                                <thead>
	                                <tr>
	                                    <th>Nome</th>
	                                    <th>Telefone</th>
	                                    <th class="text-right">
	                                    	<a href="${contextPath}/admin/customer/" class="btn btn-success btn-sm">
	                                    		<span class="glyphicon glyphicon-plus"></span>
	                                    	</a>
	                                    </th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="customer" items="${customers}" >
		                                    <tr>
		                                        <td>${customer.fullName}</td>
		                                        <td>${customer.phones}</td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/admin/customer/${customer.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<form:form method="DELETE" action="${contextPath}/admin/customer/${customer.id}" style="display:inline">
		                                        		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir cliente" data-message="Deseja realmente excluir este cliente?">
		                                        			<span class="glyphicon glyphicon-trash"></span>
		                                        		</button>
		                                        	</form:form>
		                                        </td>
		                                    </tr>
	                                	</c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>