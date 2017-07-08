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
	                        Administração de corridas
	                    </h3>
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-condensed table-striped">
	                                <thead>
	                                <tr>
	                                    <th>Cliente</th>
	                                    <th>Origem</th>
	                                    <th>Destino</th>
	                                    <th class="text-right">
	                                    	<a href="${contextPath}/admin/#consult" class="btn btn-default btn-sm">
	                                    		<span class="glyphicon glyphicon-home"></span>
	                                    	</a>
	                                    	<a href="${contextPath}/admin/delivery/" class="btn btn-success btn-sm">
	                                    		<span class="glyphicon glyphicon-plus"></span>
	                                    	</a>
	                                    </th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="delivery" items="${page.content}" >
		                                    <tr>
		                                        <td>${delivery.deliveredBy.fullName}</td>
		                                        <td>${delivery.deliveryFrom}</td>
		                                        <td>${delivery.deliveryTo}</td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/admin/delivery/${delivery.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<form:form method="DELETE" action="${contextPath}/admin/delivery/${delivery.id}" style="display:inline">
		                                        		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir corrida" data-message="Deseja realmente excluir esta corrida?">
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
						<jsp:include page="../templates/pagination.jsp">
							<jsp:param name="paginationUrl" value="${contextPath}/admin/deliveries?"/>
						</jsp:include>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>