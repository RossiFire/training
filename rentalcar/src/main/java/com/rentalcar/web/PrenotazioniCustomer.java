package com.rentalcar.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class PrenotazioniCustomer
 */
@WebServlet("/PrenotazioniCustomer")
public class PrenotazioniCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazioniCustomer() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    PrenotazioneDao prenotazioneDao;
    UtenteDao utenteDao;
    MezzoDao mezzoDao;
	public void init(ServletConfig config) throws ServletException {
		prenotazioneDao = new PrenotazioneDao();
		utenteDao = new UtenteDao();
		mezzoDao = new MezzoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	String comando;
	Utente u;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comando = request.getParameter("comando");
		if(comando.equals("PARCOAUTO")) {
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			getMezzi(request,response);
		}else {
			switch(comando) {
				case "AGGIUNGI":
					u = new Utente();
					Mezzo m = new Mezzo();
					Prenotazione p = new Prenotazione();
					int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
					u.setId(utenteCorrente);
					u.setNome(request.getParameter("nomeUtente"));
					m.setId(Integer.parseInt(request.getParameter("mezziDisponibili")));
				
				
					String dt1 = request.getParameter("yi") + "/" +request.getParameter("mi") + "/" + request.getParameter("di");
					Date dataInizio=null;
					try {
						dataInizio = new SimpleDateFormat("yyyy/MM/dd").parse(dt1);
					} catch (ParseException e) {
						e.printStackTrace();
					}  
					
					String dt2 = request.getParameter("yf") + "/" +request.getParameter("mf") + "/" + request.getParameter("df");
					Date dataFine=null;
					try {
						dataFine = new SimpleDateFormat("yyyy/MM/dd").parse(dt1);
					} catch (ParseException e) {
						e.printStackTrace();
					}  
					p.setDataInizio(dataInizio);
					p.setDataFine(dataFine);
					
					p.setApprovata(false);
					p.setUtentePrenotato(u);
					p.setMezzoPrenotato(m);
					
					prenotazioneDao.savePrenotazione(p);
					
					
					request.setAttribute("utenteCorrente", utenteCorrente);
					CustomerHome(request,response);
					break;
					
				case "ELIMINA":
					prenotazioneDao.deletePrenotazione(Integer.parseInt(request.getParameter("idPrenotazione")));
					utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
					CustomerHome(request,response);
					break;
					
				case "VISUALIZZA":
					utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
					request.setAttribute("utenteCorrente", utenteCorrente);
					CustomerCurrentHome(request,response);
					break;
				case "UTENTECORRENTE":
					Utente ut = new Utente();
					utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
					ut = utenteDao.getSingoloUtente(utenteCorrente);
					request.setAttribute("utenteCorrente", ut);
					request.getRequestDispatcher("utenteCorrente.jsp").forward(request, response);
					break;			
			}
			
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	
	
	public void CustomerCurrentHome(HttpServletRequest request, HttpServletResponse response) {
		Prenotazione p = new Prenotazione();
		int id = Integer.parseInt(request.getParameter("utenteCorrente"));
    	u = utenteDao.getSingoloUtente(id);
		System.out.println("id utente: " + u.getId());
    	request.setAttribute("utenteCorrente", u.getId());
    	p.setUtentePrenotato(u);
    	List<Prenotazione> prenotazioni = p.getPrenotazioniUtente();
    	request.setAttribute("prenotazioni", prenotazioni);
    	List<Mezzo> mz = mezzoDao.getMezzi();
    	ArrayList <Mezzo> mezziDisp = new ArrayList <>();
    	for(Mezzo m : mz) {
    		mezziDisp.add(m);
    	}
    	request.setAttribute("nomeUtente", u.getNome());
    	request.setAttribute("mezzi", mezziDisp);
    	try {
			request.getRequestDispatcher("customerHome.jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	private void CustomerHome(HttpServletRequest request, HttpServletResponse response) {
		Prenotazione p = new Prenotazione();
		int id = u.getId();
    	u = utenteDao.getSingoloUtente(id);
		System.out.println("id utente: " + u.getId());
    	request.setAttribute("utenteCorrente", u.getId());
    	p.setUtentePrenotato(u);
    	List<Prenotazione> prenotazioni = p.getPrenotazioniUtente();
    	request.setAttribute("prenotazioni", prenotazioni);
    	List<Mezzo> mz = mezzoDao.getMezzi();
    	ArrayList <Mezzo> mezziDisp = new ArrayList <>();
    	for(Mezzo m : mz) {
    		mezziDisp.add(m);
    	}
    	request.setAttribute("nomeUtente", u.getNome());
    	request.setAttribute("mezzi", mezziDisp);
    	try {
			request.getRequestDispatcher("customerHome.jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	
	public void getMezzi(HttpServletRequest request, HttpServletResponse response) {
		
		List <Mezzo> mezzi = mezzoDao.getMezzi();
		ArrayList <Mezzo> tuttiMezzi = new ArrayList<>();
		
		for(Mezzo m: mezzi) {
			tuttiMezzi.add(m);
		}
		request.setAttribute("listaMezzi", tuttiMezzi);
	
		
		try {
			request.getRequestDispatcher("mezziCustomer.jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	

}
