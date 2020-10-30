<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Lista Mezzi</title>
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
      	<c:url var="visualizza" value="LoginServlet">
			<c:param name="cheFare" value="VISUALIZZA"></c:param>
			<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
		</c:url>
        <a class="nav-link" href="${visualizza}">Home</a>
      </li>
      <li class="nav-item">

        <a class="nav-link" href="#">Parco Auto</a>
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
					<button class="addBtn" onclick="compare()"><i class="fas fa-car"></i></button>					
					</div>
					<form id="addForm" action="VediModificaMezzi" method="POST">
						<input type="text" placeholder="Casa Costruttrice" name="casaCostr" required />
						<input type="text" placeholder="Modello" name="modello" required />
						  <select name = "dropdown" required>
							<option value = "1">MINIVAN</option>
            				<option value = "2" selected>AUTOVEICOLO</option>
            				<option value = "3">FURGONE</option>
            				<option value = "4">SUV</option>
         				</select>
         				<input type="text" name="targa" placeholder="targa" required />
         				<input type="hidden" name="comando" value="AGGIUNGI" />
         				<input type="hidden" name="utenteCorrente" value="${utenteCorrente}"/>
         				<button type="submit">Aggiungi</button>
					</form>
				
						<jsp:useBean id="Mezzo" class="com.rentalcar.model.Mezzo" /> 
						<jsp:useBean id="Tipo" class="com.rentalcar.model.TipoMezzo" />
						<input type="text" id="input" onkeyup="cerca()" placeholder="filtra per casa costruttrice" />				
						<table id="tbMezzi">
							<tr>
								<th>Id</th>
								<th>Casa</th>
								<th>Modello</th>
								<th>Tipo</th>
								<th>Targa</th>
								<th>Azioni</th>
							</tr>	
						
							<c:forEach var="mezzo" items="${listaMezzi}">
							
	 							<!-- SET DEI LINK PER LA MODIFICA DI UN MEZZO -->
								<c:url var="modifica" value="VediModificaMezzi">
									<c:param name="comando" value="LOAD"></c:param>
									<c:param name="mezzoid" value="${mezzo.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
								<!-- SET DEI LINK PER L'ELIMINAZIONE DI UN MEZZO -->
								<c:url var="elimina" value="VediModificaMezzi">
									<c:param name="comando" value="ELIMINA"></c:param>
									<c:param name="mezzoid" value="${mezzo.id}"></c:param>
									<c:param name="utenteCorrente" value="${utenteCorrente}"></c:param>
								</c:url>
								
									<tr class="righe">
										<td>${mezzo.id}</td>
										<td class="userName">${mezzo.casaCostr}</td>
										<td>${mezzo.modello}</td>
										<td>${mezzo.tipomezzo.tipo}</td>
										<td>${mezzo.targa}</td>
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