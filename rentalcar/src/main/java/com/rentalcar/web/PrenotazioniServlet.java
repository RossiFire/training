package com.rentalcar.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentalcar.dao.MezzoDao;
import com.rentalcar.dao.PrenotazioneDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.model.Mezzo;
import com.rentalcar.model.Prenotazione;
import com.rentalcar.model.Utente;
/**
 * Servlet implementation class PrenotazioniServlet
 */
@WebServlet("/PrenotazioniServlet")
public class PrenotazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    PrenotazioneDao prenotazioneDao;
	public void init(ServletConfig config) throws ServletException {
		prenotazioneDao = new PrenotazioneDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	String comando;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comando = request.getParameter("comando");
		System.out.println("il comando è :" + comando);
		
		if(!comando.equals("VISUALIZZA")) {
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			doPost(request,response);
		}else {
			
			//visualizza prenotazioni in pagina admin
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			getPrenotazioni(request,response);						
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
		switch(comando) {
		
		case "APPROVA":
			int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			String dti = request.getParameter("dataInizio");
			String dtf = request.getParameter("dataFine");
			Utente u = new Utente();
			Mezzo m = new Mezzo();
			u.setId(Integer.parseInt(request.getParameter("idUtente")));
			m.setId(Integer.parseInt(request.getParameter("idMezzo")));
			Prenotazione p = new Prenotazione();
			
	
			Date dataInizio=null;
			Date dataFine = null;
			try {
				dataInizio = new SimpleDateFormat("yyyy/MM/dd").parse(dti);
				dataFine= new SimpleDateFormat("yyy/MM/dd").parse(dtf);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			
			p.setId(idPrenotazione);
			p.setDataInizio(dataInizio);
			p.setDataFine(dataFine);
			p.setApprovata(true);
			p.setUtentePrenotato(u);
			p.setMezzoPrenotato(m);
			
			
			prenotazioneDao.aupdatePrenotazione(p);
			request.setAttribute("utenteCorrente", utenteCorrente);
			getPrenotazioni(request,response);	
			break;
			
		case "ELIMINA":
			idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));
			prenotazioneDao.deletePrenotazione(idPrenotazione);
			
			utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			getPrenotazioni(request,response);	
			break;
			
		case "AGGIUNGI":
			u = new Utente();
			m = new Mezzo();
			p = new Prenotazione();
			u.setId(Integer.parseInt(request.getParameter("utentePrenotante")));
			m.setId(Integer.parseInt(request.getParameter("mezziDisponibili")));
			
			
			String dt1 = request.getParameter("yi") + "/" +request.getParameter("mi") + "/" + request.getParameter("di");
			dataInizio=null;
			try {
				dataInizio = new SimpleDateFormat("yyyy/MM/dd").parse(dt1);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			
			String dt2 = request.getParameter("yf") + "/" +request.getParameter("mf") + "/" + request.getParameter("df");
			dataFine=null;
			try {
				dataFine = new SimpleDateFormat("yyyy/MM/dd").parse(dt1);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			p.setDataInizio(dataInizio);
			p.setDataFine(dataFine);
			String approvazione = request.getParameter("approvazione");
			boolean ap = Boolean.parseBoolean(approvazione);
			
			p.setApprovata(ap);
			p.setUtentePrenotato(u);
			p.setMezzoPrenotato(m);
			
			prenotazioneDao.savePrenotazione(p);
			
			utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			
			getPrenotazioni(request,response);
			
			
		default:
			System.out.println("errore");
			response.sendRedirect("index.html");
			break;
			
		}
	}
	
	
	
	
	public void getPrenotazioni(HttpServletRequest request, HttpServletResponse response) {
		//get prenotazioni attuali
		List<Prenotazione> prenotazioni = prenotazioneDao.getPrenotazioni();
		ArrayList <Prenotazione> prenotazioniTrovate = new ArrayList<>();
		for(Prenotazione p : prenotazioni) {
			prenotazioniTrovate.add(p);
		}
		
		// get utenti e mezzi disponibili
		UtenteDao utenteDao = new UtenteDao();
		MezzoDao mezzoDao = new MezzoDao();
		List<Utente> ut = utenteDao.getUser();
		List <Mezzo> mz = mezzoDao.getMezzi();
		ArrayList <Utente> utentiPrenotanti = new ArrayList<>();
		ArrayList <Mezzo> mezziPrenotanti = new ArrayList<>();
		for(Utente u : ut) {
			utentiPrenotanti.add(u);
		}
		for(Mezzo m : mz) {
			mezziPrenotanti.add(m);
		}
		
		request.setAttribute("utenti", utentiPrenotanti);
		request.setAttribute("mezzi", mezziPrenotanti);
		request.setAttribute("prenotazioni", prenotazioniTrovate);
		try {
			request.getRequestDispatcher("prenotazioniAdmin.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
