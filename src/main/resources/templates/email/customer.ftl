<!DOCTYPE html>
<html>
<head>
	<title>Cadastro de cliente</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div>
		<p>Prezado (a) ${customer.fullName},</p>
		<h4><ins>Informações de acesso ao sistema PRATIKO®:</ins></h4>
		<dl>
			<dt><b>Conta:</b></dt> <dd>${customer.customerAccess.username}</dd>
			<dt><b>Senha:</b></dt> <dd>${customer.customerAccess.password}</dd>
		</dl>
	</div>
</body>
</html>