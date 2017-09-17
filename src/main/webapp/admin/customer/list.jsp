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
	                        Administração de clientes
	                        <span class="pull-right">
					        	<a href="${contextPath}/admin/#consult" class="btn btn-default btn-sm">
					        		<span class="glyphicon glyphicon-home"></span>
					        	</a>
					        	<a href="${contextPath}/admin/customer/" class="btn btn-success btn-sm">
					        		<span class="glyphicon glyphicon-plus"></span>
					        	</a>
					        </span>
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
	                	<ul class="list-group">
                           	<c:forEach var="customer" items="${page.content}" >
		                		<li class="list-group-item">
		                			<div class="row">
		                				<div class="col-xs-10">
		                					<div class="row">
				                				<div class="col-xs-10 col-sm-5">
				                					<p>
				                						<i class="glyphicon glyphicon-user"></i>
				                						${customer.fullName}
				                					</p>
				                					<p>
				                						<i class="glyphicon glyphicon-file"></i>
				                						${customer.document}
				                					</p>
				                				</div>
				                				<div class="col-xs-10 col-sm-5">
				                					<p>
				                						<i class="glyphicon glyphicon-record"></i>
				                						${customer.customerAddress.street} ${customer.numberAddress} ${customer.complementAddress}
				                					</p>
				                					<p>
				                						<i class="glyphicon glyphicon-map-marker"></i>
				                						${customer.customerAddress.city}
				                					</p>
				                				</div>
				                				<div class="col-xs-10 col-sm-2">
				                					<c:forEach var="phone" items="${customer.phones}">
					                					<p>
					                						<i class="glyphicon glyphicon-earphone"></i>
					                						${phone}
						                				</p>
				                					</c:forEach>
				                				</div>
				                			</div>
			                			</div>
		                				<div class="col-xs-2 col-sm-2">
		                					<div class="pull-right">
	                                        	<a href="${contextPath}/admin/customer/${customer.id}" class="btn btn-warning btn-sm">
	                                        		<span class="glyphicon glyphicon-pencil"></span>
	                                        	</a>
	                                        	<form:form method="DELETE" action="${contextPath}/admin/customer/${customer.id}" style="display:inline">
								             		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir cliente" data-message="Deseja realmente excluir este cliente?">
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
	            </div>
	        </div>
	        <div class="row">
				<jsp:include page="../templates/pagination.jsp">
					<jsp:param name="paginationUrl" value="${contextPath}/admin/customers?search=${param.search}"/>
				</jsp:include>
			</div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>