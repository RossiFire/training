package com.rentalcar.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import com.rentalcar.dao.MezzoDao;
import com.rentalcar.dao.PrenotazioneDao;
import com.rentalcar.dao.UtenteDao;
import com.rentalcar.model.Mezzo;
import com.rentalcar.model.Prenotazione;
import com.rentalcar.model.TipoUtente;
import com.rentalcar.model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private UtenteDao utenteDao;
    private PrenotazioneDao prenotazioneDao;
    private MezzoDao mezzoDao;
	public void init(ServletConfig config) throws ServletException {
		 utenteDao = new UtenteDao();
		 prenotazioneDao = new PrenotazioneDao();
		 mezzoDao = new MezzoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ARRIVO DALLA PAGINA PARCO AUTO PER FAR VISUALIZZARE SOLO LA HOME SENZA FARE ALTRO
		String azione = request.getParameter("cheFare");
		if(azione.equals("AGGIUNGI")) {
			
			//SALVARE NUOVO UTENTE NELLA PAGINA ADMIN
			String nome= request.getParameter("nome");
			String cognome= request.getParameter("cognome");
			String pss= request.getParameter("password");
			
			String dataString = request.getParameter("y") + "/" +request.getParameter("m") + "/" + request.getParameter("d");
			Date data=null;
			try {
				data = new SimpleDateFormat("yyyy/MM/dd").parse(dataString);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			
			//INSTANCE MODEL
			Utente u = new Utente();
			TipoUtente tipoutente = new TipoUtente();
			
			//SET DATA
			u.setNome(nome);
			u.setCognome(cognome);
			u.setNascita(data);
			u.setPassword(pss);
			tipoutente.setId(2);
			u.setTipoutente(tipoutente);
			
			//SAVE USER
			utenteDao.saveUser(u);
			u = null;
		}
		int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
		request.setAttribute("utenteCorrente", utenteCorrente);
		getUser(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	Utente user;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//CONTROLLO LOGIN
		 	PrintWriter pw= response.getWriter();
	    	String firstName = request.getParameter("firstname");
	        String password = request.getParameter("password");
	        HttpSession session = request.getSession();
	        
	        user = new Utente();   
            user.setNome(firstName);
	        user.setPassword(password);
	        if(utenteDao.checkUser(user)==1) {
//utente admin
	        	user = utenteDao.getCurrentUserInfo(user);
	        	request.setAttribute("utenteCorrente", user.getId());
		        getUser(request,response);
	        }else if(utenteDao.checkUser(user)==0) {
//utente customer
	        	CustomerHome(request,response);
	        } else {
//utente non esistente
	        	pw.println("nome/password sbagliata oppure utente insesistente");
	        }
	        
	       
	}
	
	
	private void CustomerHome(HttpServletRequest request, HttpServletResponse response) {
		Prenotazione p = new Prenotazione();
    	user = utenteDao.getCurrentUserInfo(user);
    	request.setAttribute("utenteCorrente", user.getId());
    	p.setUtentePrenotato(user);
    	List<Prenotazione> prenotazioni = p.getPrenotazioniUtente();
    	request.setAttribute("prenotazioni", prenotazioni);
    	List<Mezzo> mz = mezzoDao.getMezzi();
    	ArrayList <Mezzo> mezziDisp = new ArrayList <>();
    	for(Mezzo m : mz) {
    		mezziDisp.add(m);
    	}
    	request.setAttribute("nomeUtente", user.getNome());
    	request.setAttribute("mezzi", mezziDisp);
    	try {
			request.getRequestDispatcher("customerHome.jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	

	public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw= response.getWriter();
		
		List<Utente> tutti = utenteDao.getUser();
		ArrayList<Utente> utentiTrovati = new ArrayList();
	
		for(Utente u : tutti) {
			if(u.getTipoutente().getTipo().equals("CUSTOMER")) {
				utentiTrovati.add(u);				
			}
		}
		request.setAttribute("utenti", utentiTrovati);
		
		try {
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}

