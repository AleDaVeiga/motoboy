<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Motoboy - Controle das informações</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		
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
		<link href="${contextPath}/resources/css/creative.min.css" rel="stylesheet">
		
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
					<a class="navbar-brand page-scroll" href="#page-top">Pratiko</a>
		        </ul>
		        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		            <ul class="nav navbar-nav navbar-right">
		                <li>
		                    <a class="page-scroll" href="#cadastre">Cadastro</a>
		                </li>
		                <li>
		                    <a class="page-scroll" href="#consult">Consulta</a>
		                </li>
		                <li>
		                    <a class="page-scroll" href="#report">Relatório</a>
		                </li>
	                    <li>
	                        <a class="page-scroll" href="#contact">Contato</a>
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
	                <a href="#cadastre" class="btn btn-primary btn-xl page-scroll">Utilizando o sistema</a>
	            </div>
	        </div>
	    </header>
	    <section id="cadastre">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12 text-center">
	                    <h2 class="section-heading">Cadastros</h2>
	                    <hr class="primary">
	                </div>
	            </div>
	        </div>
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                        <a href="${contextPath}/admin/customers">
	                        	<i class="fa fa-4x fa-user text-primary sr-icons"></i>
	                        </a>
	                        <h3>Clientes</h3>
	                        <p class="text-muted">Cadastre quem pode pedir corridas.</p>
	                    </div>
	                </div>
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                        <a href="${contextPath}/admin/deliverymans">
	                        	<i class="fa fa-4x fa-motorcycle text-primary sr-icons"></i>
	                        </a>
                        	<h3>Motoboys</h3>
	                        <p class="text-muted">Cadastre as pessoas que irão realizar as corrigas.</p>
	                    </div>
	                </div>
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                		<a href="${contextPath}/admin/deliveries">
	                        	<i class="fa fa-4x fa-list-ol text-primary sr-icons"></i>
							</a>
	                        <h3>Corridas</h3>
	                        <p class="text-muted">Cadastre o que será entregue.</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    <section class="no-padding" id="consult">
	        <div class="container-fluid">
	            <div class="row no-gutter popup-gallery">
	                <div class="col-lg-4 col-sm-6">
	                    <a href="${contextPath}/resources/img/portfolio/fullsize/1.jpg" class="portfolio-box">
	                        <img src="${contextPath}/resources/img/portfolio/thumbnails/1.jpg" class="img-responsive" alt="">
	                        <div class="portfolio-box-caption">
	                            <div class="portfolio-box-caption-content">
	                                <div class="project-category text-faded">
	                                    Clientes
	                                </div>
	                                <div class="project-name">
	                                    Consultar clientes
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </div>
	                <div class="col-lg-4 col-sm-6">
	                    <a href="${contextPath}/resources/img/portfolio/fullsize/2.jpg" class="portfolio-box">
	                        <img src="${contextPath}/resources/img/portfolio/thumbnails/2.jpg" class="img-responsive" alt="">
	                        <div class="portfolio-box-caption">
	                            <div class="portfolio-box-caption-content">
	                                <div class="project-category text-faded">
	                                    Motoboys
	                                </div>
	                                <div class="project-name">
	                                    Consultar motoboys
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </div>
	                <div class="col-lg-4 col-sm-6">
	                    <a href="${contextPath}/resources/img/portfolio/fullsize/3.jpg" class="portfolio-box">
	                        <img src="${contextPath}/resources/img/portfolio/thumbnails/3.jpg" class="img-responsive" alt="">
	                        <div class="portfolio-box-caption">
	                            <div class="portfolio-box-caption-content">
	                                <div class="project-category text-faded">
	                                    Corridas
	                                </div>
	                                <div class="project-name">
	                                    Consultar corridas
	                                </div>
	                            </div>
	                        </div>
	                    </a>
	                </div>
	            </div>
	        </div>
	    </section>
	    <section id="report">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-12 text-center">
	                    <h2 class="section-heading">Relatórios</h2>
	                    <hr class="primary">
	                </div>
	            </div>
	        </div>
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                        <a href="${contextPath}/admin/report/customers" target="_blank">
	                        	<i class="fa fa-4x fa-file-pdf-o text-primary sr-icons"></i>
	                        </a>
	                        <h3>Clientes</h3>
	                        <p class="text-muted">Veja quem pediu corridas.</p>
	                    </div>
	                </div>
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                        <a href="${contextPath}/admin/report/deliverymans" target="_blank">
	                        	<i class="fa fa-4x fa-file-o text-primary sr-icons"></i>
	                        </a>
                        	<h3>Motoboys</h3>
	                        <p class="text-muted">Veja as pessoas que realizaram corridas.</p>
	                    </div>
	                </div>
	                <div class="col-lg-4 col-md-6 text-center">
	                    <div class="service-box">
	                		<a href="${contextPath}/admin/report/deliveries" target="_blank">
	                        	<i class="fa fa-4x fa-file-text text-primary sr-icons"></i>
							</a>
	                        <h3>Corridas</h3>
	                        <p class="text-muted">Veja o que já foi entregue.</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    <section id="contact">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-8 col-lg-offset-2 text-center">
	                    <h2 class="section-heading">Vamos entrar em contato</h2>
	                    <hr class="primary">
	                    <p>Pronto para iniciar uma entrega conosco? Muito bem! Dê-nos um telefonema ou envie um e-mail que nós daremos um retorno assim que possível!</p>
	                </div>
	                <div class="col-lg-4 col-lg-offset-2 text-center">
	                    <i class="fa fa-phone fa-3x sr-contact"></i>
	                    <p>48 99999 9999</p>
	                </div>
	                <div class="col-lg-4 text-center">
	                    <i class="fa fa-envelope-o fa-3x sr-contact"></i>
	                    <p><a href="mailto:contato@pratiko.com">contato@pratiko.com</a></p>
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