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
		<jsp:include page="../templates/menu_consult.jsp"/>
		<div class="container">
	        <div class="row">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title clearfix">
	                        Administração de motoboys
	                        <span class="pull-right">
                               	<a href="${contextPath}/admin/#consult" class="btn btn-default btn-sm">
                               		<span class="glyphicon glyphicon-home"></span>
                               	</a>
                               	<a href="${contextPath}/admin/deliveryman/" class="btn btn-success btn-sm">
                               		<span class="glyphicon glyphicon-plus"></span>
                               	</a>
                        	</span>
	                    </h3>
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-condensed table-striped">
	                                <thead>
	                                <tr>
	                                    <th>Nome</th>
	                                    <th>Telefone</th>
	                                    <th/>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="deliveryMan" items="${page.content}" >
		                                    <tr>
		                                        <td>${deliveryMan.fullName}</td>
		                                        <td>${deliveryMan.phones}</td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/admin/deliveryman/${deliveryMan.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<form:form method="DELETE" action="${contextPath}/admin/deliveryman/${deliveryMan.id}" style="display:inline">
		                                        		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir motoboy" data-message="Deseja realmente excluir este motoboy">
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
							<jsp:param name="paginationUrl" value="${contextPath}/admin/deliverymans?"/>
						</jsp:include>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>