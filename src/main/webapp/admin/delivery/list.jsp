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
	                        Administração de corridas
	                        <span class="pull-right">
                               	<a href="${contextPath}/admin/#consult" class="btn btn-default btn-sm">
                               		<span class="glyphicon glyphicon-home"></span>
                               	</a>
                               	<a href="${contextPath}/admin/delivery/" class="btn btn-success btn-sm">
                               		<span class="glyphicon glyphicon-plus"></span>
                               	</a>
	                        </span>
	                    </h3>
	                </div>
	                <div class="panel-body">
	                	<ul class="list-group">
                           	<c:forEach var="delivery" items="${page.content}" >
		                		<li class="list-group-item">
		                			<div class="row">
		                				<div class="col-sm-4">
		                					<p>
		                						<i class="glyphicon glyphicon-user"></i>
		                						${delivery.customer.fullName}
		                					</p>
		                					<small>
		                						<i class="fa fa-motorcycle sr-icons"></i>
		                						${delivery.deliveredBy.fullName}
		                					</small>
		                				</div>
		                				<div class="col-sm-4">
		                					<p>
		                						<i class="glyphicon glyphicon-map-marker"></i>
		                						${delivery.deliveryFrom}
		                					</p>
		                					<p>
		                						<i class="glyphicon glyphicon-record"></i>
		                						${delivery.deliveryTo}
		                					</p>
		                				</div>
		                				<div class="col-sm-2 text-right">
		                					<p>${delivery.price}</p>
		                					<p>${delivery.deliveryAt}</p>
		                				</div>
		                				<div class="col-sm-2">
		                					<div class="well pull-right">
	                                        	<a href="${contextPath}/admin/delivery/${delivery.id}" class="btn btn-warning btn-sm">
	                                        		<span class="glyphicon glyphicon-pencil"></span>
	                                        	</a>
	                                        	<form:form method="DELETE" action="${contextPath}/admin/delivery/${delivery.id}" style="display:inline">
	                                        		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir corrida" data-message="Deseja realmente excluir esta corrida?">
	                                        			<span class="glyphicon glyphicon-trash"></span>
	                                        		</button>
	                                        	</form:form>
	                                        </div>
	                                    </div>
	                                </div>
	                            </li>
                            </c:forEach>
						</ul>
					</div>
					<jsp:include page="../templates/pagination.jsp">
						<jsp:param name="paginationUrl" value="${contextPath}/admin/deliveries?"/>
					</jsp:include>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>