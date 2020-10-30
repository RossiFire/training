<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Modifica Form</title>
	  <!--======== bootstrap =========-->
	  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	  integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	  integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	  crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	  integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	  crossorigin="anonymous"></script>
  <!--========= line icons ======-->
  <link rel="stylesheet" href="https://cdn.lineicons.com/1.0.1/LineIcons.min.css">
  <!--========= font awesome =======-->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	  integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
	  <!--===== JQUERY LINK ======-->
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
	    <link rel="stylesheet" href="./css/navbar.css">
	  <link rel="stylesheet" href="./css/adminHome.css">
</head>
<body style="text-align: center">

								<jsp:useBean id="User" class="com.rentalcar.model.Utente" /> 
	<h1>MODIFICA UTENTE</h1>
	
					
					<form id="addForm" action="ModificaUtenti" method="post" style="display: inline-block !important">
						<input type="hidden" name="id" value="${utente.id}" />
						<input type="text" placeholder="nome "name="nome" value="${utente.nome}" />
						<input type="text" placeholder="cognome" name="cognome" value="${utente.cognome}"/>
						<input type="hidden" name="comando" value="MODIFICA" />
         				<input type="string" placeholder="data di nascita" name="data" value="${User.getDate(utente.nascita)}"/>
         				<input type="text" name="password" placeholder="password" value="${utente.password}" />
         				<input type="hidden" name="utenteCorrente" value="${utenteCorrente}"/>
         				<button type="submit">Modifica</button>
					</form>


</body>
</html>