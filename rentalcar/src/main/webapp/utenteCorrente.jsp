<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Utente <c:out value="${utenteCorrente.nome}"></c:out> </title>
		  <!--===== JQUERY LINK ======-->
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
	  <!-- CSS E JS-->
	  <link rel="stylesheet" href="./css/navbar.css">
	  <link rel="stylesheet" href="./css/adminHome.css">
	  <script src="./js/addFormAdmin.js"></script>
</head>
<body>

	

	<p>${utenteCorrente.id}</p>
	<p>${utenteCorrente.nome}</p>
	<p>${utenteCorrente.cognome}</p>
	

</body>
</html>