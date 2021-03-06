<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		
		<title>..:: Motoboy ::..</title>
		
		<!-- Bootstrap Core CSS -->
	    <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	    <!-- Custom Fonts -->
	    <link href="${contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
	    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
	
	    <!-- Plugin CSS -->
	    <link href="${contextPath}/resources/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">
	
	    <!-- Theme CSS -->
	    <link href="${contextPath}/resources/css/creative.css" rel="stylesheet">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</head>
	<body id="page-top">
		<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
	        <div class="container-fluid">
	            <ul class="navbar-header">
	                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
	                </button>
	                <c:if test="${pageContext.request.userPrincipal.name != null}">
		                <a class="navbar-brand page-scroll" href="#page-top">${pageContext.request.userPrincipal.name}</a>
					</c:if>
	            </ul>
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav navbar-right">
	                    <li>
	                        <a class="page-scroll" href="#consult">Consulta</a>
	                    </li>
	                    <li>
	                        <a class="page-scroll" href="#report">Relatório</a>
	                    </li>
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
	    <header>
	        <div class="header-content">
	            <div class="header-content-inner">
	                <h1 id="homeHeading">Bem vindo ao Pratiko</h1>
	                <hr>
	                <p>Área do cliente</p>
	                <a href="#consult" class="btn btn-success btn-xl page-scroll">Acessar as corridas</a>
	            </div>
	        </div>
	    </header>
	    <section id="consult">
	        <div class="container">
	            <div class="row text-center">
	                <div class="col-lg-10 col-lg-offset-1">
	                    <h2 class="section-heading">Lista de corridas</h2>
	                    <hr>
	                    <div class="row">
	                    	<c:forEach var="delivery" items="${deliveries}" >
		                    	<div class="col-md-3 col-sm-6">
		                    		<div class="service-item">
		                    			<h4>
		                    				<strong>
		                    					<span class="label label-default">
			                    					<fmt:formatDate value="${delivery.deliveryAt}" pattern="dd/MM/yyyy" />
			                    				</span>
		                    				</strong>
		                    				<strong>
		                    					<span class="label label-${delivery.status ? 'success' : 'danger'}">
		                    						<fmt:formatNumber value="${delivery.price}" type="currency"/>
		                    					</span>
				                			</strong>
		                    			</h4>
		                    			<p>
		                    				<i class="glyphicon glyphicon-record"></i>
		                    				${delivery.deliveryFrom}
		                    			</p>
		                    			<p>
		                    				<i class="glyphicon glyphicon-map-marker"></i>
		                    				${delivery.deliveryTo}
		                    			</p>
		                    			<c:if test="${!delivery.status}">
			                    			<a href="${contextPath}/delivery/${delivery.id}/accept#consult" class="btn btn-danger">Aceitar</a>
		                    			</c:if>
		                    			<c:if test="${delivery.status}">
			                    			<a class="btn btn-success">Confirmado <fmt:formatDate value="${delivery.confirmedIn}" pattern="dd/MM/yyyy" /></a>
		                    			</c:if>
		                    		</div>
		                    	</div>
	                    	</c:forEach>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    
	    <section id="report" class="call-to-action bg-dark">
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-lg-12 text-center">
	    				<h2 class="section-heading">Relatório</h2>
	    				<h3 class="section-subheading text-muted">Filtrar relatório de corridas.</h3>
	    			</div>
	    		</div>
	    		<div class="row">
	    			<div class="col-lg-12">
	    				<form:form method="POST" target="_blank" modelAttribute="reportDeliveryForm" class="form-signin" action="${contextPath}/report/deliveries">
	    					<div class="row">
	    						<div class="col-md-6">
	    							<div class="form-group">
	    								<form:input type="date" path="startDeliveryAt" class="form-control"></form:input>
	    							</div>
	    						</div>
	    						<div class="col-md-6">
	    							<div class="form-group">
	    								<form:input type="date" path="endDeliveryAt" class="form-control"></form:input>
	    							</div>
	    						</div>
    							<div class="clearfix"></div>
    							<div class="col-lg-12 text-center">
    								<div id="success"></div>
    								<button class="btn btn-default btn-xl sr-button" type="submit">Download</button>
    							</div>
    						</div>
    					</form:form>
    				</div>
    			</div>
    		</div>	    						
	    </section>
		
		<!-- Bootstrap Core JavaScript -->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
			    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	    <!-- Plugin JavaScript -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	    <script src="${contextPath}/resources/vendor/scrollreveal/scrollreveal.min.js"></script>
	    <script src="${contextPath}/resources/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
	
	    <!-- Theme JavaScript -->
	    <script src="${contextPath}/resources/js/creative.min.js"></script>
	</body>
</html>
