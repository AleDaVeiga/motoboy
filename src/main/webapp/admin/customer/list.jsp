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
	                        <div class="col-md-12">
	                            <table class="table table-striped">
	                                <thead>
	                                <tr>
	                                    <th style="width: 10px">#</th>
	                                    <th>Nome</th>
	                                    <th>Telefone</th>
	                                    <th class="text-right">
	                                    	<a href="${contextPath}/admin/customer/" class="btn btn-success">
	                                    		<span class="glyphicon glyphicon-plus"></span>
	                                    	</a>
	                                    </th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="customer" items="${customers}" >
		                                    <tr>
		                                        <td>${customer.id}</td>
		                                        <td>${customer.fullName}</td>
		                                        <td>${customer.phones}</td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/admin/customer/${customer.id}" class="btn btn-warning">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<a href="${contextPath}/admin/customer/${customer.id}" class="btn btn-danger">
		                                        		<span class="glyphicon glyphicon-remove"></span>
		                                        	</a>
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
	</body>
</html>