<%@ include file="/decorators/jstl.jsp" %>
<html>
<head>
	<title>Cadastrar Usuário</title>
</head>
<body>

	<form id="formNovoUsuario" method="post" action="<c:url value="/usuario/salvar" />">
		<fieldset>
			<legend>Cadastrar Usuário</legend>
			
			<label for="login">Login:</label>
			<input id="login" type="text" name="usuario.username"><br/>
			<label for="senha">Senha:</label>
			<input id="senha" type="password"" name="usuario.password"><br/>
			<label for="nome">Nome:</label>
			<input id="nome" type="text" name="usuario.pessoa.nome"><br/>
			<label for="email">Email:</label>
			<input id="email" type="text" name="usuario.pessoa.email"><br/><br/>
			
			<button type="submit">Cadastrar</button>
			<button type="button">Cancelar</button>
		</fieldset>
	</form>

</body>
</html>
