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
      	<c:url var="visualizza" value="LoginServlet">
			<c:param name="cheFare" value="VISUALIZZA"></c:param>
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${visualizza}">Home</a>
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
							
<%-- 				 <c:url var="formPrenotazione" value="PrenotazioniServlet">
					<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
					<c:param name="comando" value="TOFORM"></c:param>
				</c:url>
					<a href="${formPrenotazione}">Aggiungi</a> | 	 --%>	
				
				<div id="btnAggiungi">
					<button class="addBtn" onclick="compare()"><i class="fas fa-user-plus"></i></button>					
					</div>
					
					
					<form id="addForm" action="PrenotazioniServlet" method="get">
						  <select name = "utentePrenotante" required>
						  <c:forEach var="utenti" items="${utenti}">
            				<option value = "${utenti.id}"> <c:out value="${utenti.nome}"></c:out> </option>
            			</c:forEach>
         				</select> 
         				 <select name = "mezziDisponibili" required>
						  <c:forEach var="mezzi" items="${mezzi}">
            				<option value = "${mezzi.id}"> <c:out value="${mezzi.casaCostr}"></c:out> <c:out value="${mezzi.modello}"></c:out> </option>
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
         				<select name = "approvazione" required>
            				<option value = "true">Approvata</option>
            				<option value = "false">Non approvata</option>
         				</select>
         				<input type="hidden" name="comando" value="AGGIUNGI" />
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
						
							<c:forEach var="prenotazione" items="${prenotazioni}">
							
	 							<!-- SET DEI LINK PER LA MODIFICA DI UNA PRENOTAZIONE -->
 								<c:url var="approva" value="PrenotazioniServlet">
									<c:param name="comando" value="APPROVA"></c:param>
									<c:param name="idPrenotazione" value="${prenotazione.id}"></c:param>
									<c:param name="idUtente" value="${prenotazione.utentePrenotato.id}"></c:param>
									<c:param name="idMezzo" value="${prenotazione.mezzoPrenotato.id}"></c:param>
									<c:param name="dataInizio" value="${Prenotazione.getDate(prenotazione.dataInizio)}"></c:param>
									<c:param name="dataFine" value="${Prenotazione.getDate(prenotazione.dataFine)}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
								<!-- SET DEI LINK PER L'ELIMINAZIONE DI UNA PRENOTAZIONE -->
								<c:url var="elimina" value="PrenotazioniServlet">
									<c:param name="comando" value="ELIMINA"></c:param>
									<c:param name="idPrenotazione" value="${prenotazione.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url> 
								
									<tr class="righe">
										<td>${prenotazione.id}</td>
										<td class="userName">${prenotazione.utentePrenotato.nome}</td>
										<td>${prenotazione.mezzoPrenotato.modello}</td>
										<td>${Prenotazione.approvazione(prenotazione.approvata)}</td>
										<td>${Prenotazione.getDate(prenotazione.dataFine)}</td>
										<td>${Prenotazione.getDate(prenotazione.dataInizio)}</td>
									 	<td><a href="${approva}">Approva</a> | 
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