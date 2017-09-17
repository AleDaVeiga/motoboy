<%@ page contentType="text/html;charset=UTF-8"%>
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
		<jsp:include page="../templates/menu_cadastre.jsp"/>
		<div class="container">
            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title clearfix">
                            Editar usuário
                        </h3>
                    </div>
                    <div class="panel-body">
                   		<div class="alert alert-info">
                   			<strong>Informação!</strong> Utilize um e-mail válido para recuperar uma nova senha de acesso, caso esqueça a mesma.
                   		</div>
                       	<c:if test="${messageSuccess != null}">
                       		<div class="alert alert-success alert-dismissable">
                       			<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
                       			<strong>Sucesso!</strong> ${messageSuccess}
                       		</div>
                       	</c:if>
                       	<c:if test="${messageError != null}">
                       		<div class="alert alert-danger alert-dismissable">
                       			<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
                       			<strong>Erro!</strong> ${messageError}
                       		</div>
                       	</c:if>
                        <form:form method="PUT" modelAttribute="userForm" class="form-signin" action="${contextPath}/admin/user/changeemail">
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        	<fieldset disabled>
	                        	<spring:bind path="username">
	                        		<div class="form-group">
	                        			<label class="control-label">Conta de acesso do usuário</label>
	                        			<form:input path="username" class="form-control" placeholder="Usuário"></form:input>
	                        			<form:errors path="username"></form:errors>
	                        		</div>
	                        	</spring:bind>
	                        </fieldset>
                        	<spring:bind path="email">
                        		<div class="form-group">
                        			<label class="control-label">E-mail</label>
                        			<form:input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" path="email" class="form-control" placeholder="E-mail"></form:input>
                        			<form:errors path="email"></form:errors>
                        		</div>
                        	</spring:bind>
                        	<div class="text-right">
                        		<a href="${contextPath}/admin/#cadastre" class="btn btn-default">
                        			<span class="glyphicon glyphicon-home"></span>
                        		</a>
                        		<button type="submit" class="btn btn-success">
                        			<span class="glyphicon glyphicon-floppy-disk"></span>
                        		</button>
                        	</div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
	    <jsp:include page="../templates/footer.jsp"/>
		
		<!-- Custom JavaScript -->
		<script src="${contextPath}/resources/js/dynamic.form.fields.js"></script>
	</body>
</html>