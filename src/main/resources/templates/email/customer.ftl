<!DOCTYPE html>
<html>
<head>
	<title>Cadastro de cliente</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div>
		<p>Prezado (a) ${customer.fullName},</p>
		<p>A MotoboySC em parceria com a WG Sistemas & Sites lançaram o PRATIKO - um Sistema com confirmação dos serviços solicitados e relatórios desenvolvidos, com acompanhamento em tempo real e eliminação dos canhotos de papel, que só trazem desconforto e lentidão na hora da execução dos serviços.</p>
		<p>Este sistema não trará nenhum custo a você e sua empresa.</p>
		<p>Desde já a MotoboySC agradece sua atenção e confiança.</p>
		<p>Sandro, Gerente Administrativo.</p>
		<p>Acesse <a href="http://sistema.motoboysc.com.br">Pratiko App</a> e faça seu login</p>
		<dl>
			<dt><b>Conta:</b></dt> <dd>${customer.customerAccess.username}</dd>
			<dt><b>Senha:</b></dt> <dd>${customer.customerAccess.password}</dd>
		</dl>
	</div>
</body>
</html>