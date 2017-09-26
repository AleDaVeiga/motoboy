<#setting number_format="currency">
<#setting locale="pt_BR">
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
</head>
<body>
	<div>
		<p>Prezado (a) ${delivery.customer.fullName},</p>
		<p>Foi solicitada uma corrida para você através do PRATIKO.<br>
		Favor acessar o sistema PRATIKO com seu login e senha para confirmar a corrida.</p>
		<h4><ins>Informações da corrida:</ins></h4>
		<dl>
			<dt><b>Origem:</b></dt> <dd>${delivery.deliveryFrom}</dd>
			<dt><b>Destino:</b></dt> <dd>${delivery.deliveryTo}</dd>
			<dt><b>Valor:</b></dt> <dd>${delivery.price}</dd>
			<dt><b>Data:</b></dt> <dd>${delivery.deliveryAt?datetime?string("dd/MM/yyyy")}</dd>
		</dl>
	</div>
</body>
</html>