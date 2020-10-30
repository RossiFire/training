<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Admin Page</title>
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
	  <!-- CSS E JS-->
	  <link rel="stylesheet" href="./css/navbar.css">
	  <link rel="stylesheet" href="./css/adminHome.css">
	  <script src="./js/addFormAdmin.js"></script>
</head>
<body>
	
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"><img scr="img/icon.png" width="32px" height="32px"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">Home</a>
      </li>
      <li class="nav-item">
      <c:url var="parcoAutoLink" value="VediModificaMezzi">
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${parcoAutoLink}">Parco Auto</a>
      </li>
         <li class="nav-item">
         <c:url var="linkUtenteCorrente" value="ModificaUtenti">
			<c:param name="utenteIdCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${linkUtenteCorrente}">Utente</a>
      </li>
       <li class="nav-item">
       <c:url var="prenotazioniLink" value="PrenotazioniServlet">
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
			<c:param name="comando" value="VISUALIZZA"></c:param>
		</c:url>
        <a class="nav-link" href="${prenotazioniLink}">Prenotazioni</a>
      </li>
              <li class="nav-item">
        <a class="nav-link" href="index.html">Esci</a>
      </li>
    </ul>
  </div>
</nav>

	
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-3 col-md-4 col-sm-12">

					</div>
					<div class="col-lg-6 col-md-4 col-sm-12">

					<div id="btnAggiungi">
					<button class="addBtn" onclick="compare()"><i class="fas fa-user-plus"></i></button>					
					</div>
					
					
					<form id="addForm" action="LoginServlet" method="get">
						<input type="text" placeholder="nome "name="nome" required />
						<input type="text" placeholder="cognome" name="cognome" required />
						<!--  <select name = "dropdown" required>
            				<option value = "1">ADMIN</option>
            				<option value = "2" selected>CUSTOMER</option>
         				</select> -->
         				<div class="data">
         					<input type="text" name="d" placeholder="DD" required /> 
         					<input type="text" name="m" placeholder="MM" required />
         					<input type="text" name="y" placeholder="YYYY" required />
         				</div>
         				<input type="text" name="password" placeholder="password" required />
         				<input type="hidden" name="cheFare" value="AGGIUNGI" />
         				<input type="hidden" name="utenteCorrente" value="${utenteCorrente}"/>
         				<button type="submit">Aggiungi</button>
					</form>
					<input type="text" id="input" onkeyup="cerca()" placeholder="filtra per nome" />
						<table id="tbCustomer">
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Privilegi</th>
								<th>Età</th>
								<th>Password</th>
								<th>Azioni</th>
							</tr>	
						<jsp:useBean id="User" class="com.rentalcar.model.Utente" /> 
						<jsp:useBean id="Tipo" class="com.rentalcar.model.TipoUtente" />
						
							<c:forEach var="customerUser" items="${utenti}">
							
							<!-- SET DEI LINK PER LA MODIFICA DI UN UTENTE -->
								<c:url var="modifica" value="ModificaUtenti">
									<c:param name="comando" value="LOAD"></c:param>
									<c:param name="utenteId" value="${customerUser.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
								<!-- SET DEI LINK PER L'ELIMINAZIONE DI UN UTENTE -->
								<c:url var="elimina" value="ModificaUtenti">
									<c:param name="comando" value="DELETE"></c:param>
									<c:param name="utenteId" value="${customerUser.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
									<tr class="righe">
										<td>${customerUser.id}</td>
										<td class="userName">${customerUser.nome}</td>
										<td>${customerUser.cognome}</td>
										<td>${customerUser.tipoutente.tipo}</td>
										<td>${User.getDate(customerUser.nascita)}</td>
										<td>${customerUser.password}</td>
										<td><a href="${modifica}">Modifica</a> | 
										<a href="${elimina}">Elimina</a></td>
									</tr>								
							</c:forEach>	
						</table>
					</div>
					<div class="col-lg-3 col-md-4 col-sm-12">
						
					</div>
				</div>
			</div>





</body>
</html>