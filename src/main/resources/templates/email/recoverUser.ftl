<!DOCTYPE html>
<html>
<head>
	<title>Recuperação de senha</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div>
		<p>Prezado (a) usuário (a) ${user.username},</p>
		<p>Foi solicitada uma nova senha de acesso ao sistema PRATIKO®.<br>
		Favor acessar o sistema PRATIKO® com seu login e a seguinte senha:</p>
		<dl>
			<dt><b>Senha:</b></dt> <dd>${user.password}</dd>
		</dl>
	</div>
</body>
</html>