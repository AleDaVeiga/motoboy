<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#app-navbar-collapse">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand page-scroll" href="${contextPath}/admin/#consult">Pratiko</a>
        </div>

        <div class="collapse navbar-collapse" id="app-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${contextPath}/admin/deliveries">Corridas</a></li>
                <li><a href="${contextPath}/admin/customers">Clientes</a></li>
                <li><a href="${contextPath}/admin/deliverymans">Motoboys</a></li>
                <li><a href="#">Usuários</a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                	<li>
						<form id="logoutForm" method="POST" action="${contextPath}/logout">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
						<a class="page-scroll" onclick="document.forms['logoutForm'].submit()">Sair</a>
					</li>
				</c:if>
            </ul>
        </div>
    </div>
</nav>