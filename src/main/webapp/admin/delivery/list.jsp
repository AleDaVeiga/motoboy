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
	                            <table class="table table-striped">
	                                <thead>
	                                <tr>
	                                    <th style="width: 10px">#</th>
	                                    <th>Origem</th>
	                                    <th class="text-right">
	                                    	<a class="btn btn-success" href="${contextPath}/admin/delivery/">Nova</a>
	                                    </th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                    <tr>
	                                        <td>1</td>
	                                        <td>Corrida</td>
	                                        <td class="text-right">
	                                        	<a class="btn btn-warning" href="#">Editar</a>
	                                        	<a class="btn btn-danger" href="#">Remover</a>
	                                        </td>
	                                    </tr>
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