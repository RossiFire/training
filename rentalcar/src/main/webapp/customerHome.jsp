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
      <c:url var="parcoAutoLink" value="PrenotazioniCustomer">
      		<c:param name="comando" value="PARCOAUTO"></c:param>
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${parcoAutoLink}">Parco Auto</a>
      </li>
         <li class="nav-item">
         <c:url var="linkUtenteCorrente" value="PrenotazioniCustomer">
         	<c:param name="comando" value="UTENTECORRENTE"></c:param>
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${linkUtenteCorrente}">Utente</a>
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
					<form id="addForm" action="PrenotazioniCustomer" method="get">
         				  <select name = "mezziDisponibili" required>
						  <c:forEach var="mezzi" items="${mezzi}">
            				<option value ="${mezzi.id}"> <c:out value="${mezzi.casaCostr}"></c:out> <c:out value="${mezzi.modello}"></c:out> </option>
            			</c:forEach>
         				</select>
         				<div class="data">
         				<label>Data Inizio</label>
         					<input type="text" name="di" placeholder="DD" required /> 
         					<input type="text" name="mi" placeholder="MM" required />
         					<input type="text" name="yi" placeholder="YYYY" required />
         				</div>
         				<div class="data">
         				<label>Data Fine</label>
         					<input type="text" name="df" placeholder="DD" required /> 
         					<input type="text" name="mf" placeholder="MM" required />
         					<input type="text" name="yf" placeholder="YYYY" required />
         				</div>
         				<input type="hidden" name="approvata" value="false" />
         				<input type="hidden" name="comando" value="AGGIUNGI" />
         				<input type="hidden" name="nomeUtente" value="${nomeUtente}" />
         				<input type="hidden" name="utenteCorrente" value="${utenteCorrente}"/>
         				<button type="submit">Aggiungi</button>
					</form>
				
				
				
						<jsp:useBean id="Prenotazione" class="com.rentalcar.model.Prenotazione" /> 
						<input type="text" id="input" onkeyup="cerca()" placeholder="filtra per utente" />					
						<table id="tbMezzi">
							<tr>
								<th>Id</th>
								<th>Utente</th>
								<th>Mezzo</th>
								<th>Approvata</th>
								<th>Data Inizio</th>
								<th>Data Fine</th>
								<th>Azioni</th>
							</tr>	
						
							<c:forEach var="prenotazioni" items="${prenotazioni}">
							

								<!-- SET DEI LINK PER L'ELIMINAZIONE DI UNA PRENOTAZIONE -->
								<c:url var="elimina" value="PrenotazioniCustomer">
									<c:param name="comando" value="ELIMINA"></c:param>
									<c:param name="idPrenotazione" value="${prenotazioni.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
								
									<tr class="righe">
										<td>${prenotazioni.id}</td>
										<td class="userName">${prenotazioni.utentePrenotato.nome}</td>
										<td>${prenotazioni.mezzoPrenotato.modello}</td>
										<td>${Prenotazione.approvazione(prenotazioni.approvata)}</td>
										<td>${Prenotazione.getDate(prenotazioni.dataFine)}</td>
										<td>${Prenotazione.getDate(prenotazioni.dataInizio)}</td>
										<td>
										<c:set var = "approvazione" scope = "session" value = "${prenotazioni.approvata}"/>
										<c:if test = "${not approvazione}">
											<a href="${elimina}">Elimina</a> 	
										</c:if>
										</td>
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