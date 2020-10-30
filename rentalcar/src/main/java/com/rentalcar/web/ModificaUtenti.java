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

import com.rentalcar.dao.UtenteDao;
import com.rentalcar.model.TipoUtente;
import com.rentalcar.model.Utente;
/**
 * Servlet implementation class ModificaUtenti
 */
@WebServlet("/ModificaUtenti")
public class ModificaUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private UtenteDao utenteDao;
	public void init(ServletConfig config) throws ServletException {
		utenteDao = new UtenteDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	String operazione;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		operazione = request.getParameter("comando");
		if(operazione!=null) {
			doPost(request,response);
		}
		int utenteIdCorrente = Integer.parseInt(request.getParameter("utenteIdCorrente"));
		Utente u = new Utente();
		u = utenteDao.getSingoloUtente(utenteIdCorrente);
		request.setAttribute("utenteCorrente", u);
		
		request.getRequestDispatcher("utenteCorrente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		operazione = request.getParameter("comando");
		if(operazione.equals("LOAD")) {
			operazione = null;
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			preCompilaForm(request,response);
		}else
			if(operazione.equals("MODIFICA")) {
				Utente u = new Utente();
				int id = Integer.parseInt(request.getParameter("id"));
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String dt = request.getParameter("data");
				TipoUtente tip = new TipoUtente();
				tip.setId(2);
				Date data = null;
				try {
					data = new SimpleDateFormat("dd/MM/yyyy").parse(dt);
					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String pss = request.getParameter("password");
				u.setId(id);
				u.setNome(nome);
				u.setCognome(cognome);
				u.setNascita(data);
				u.setPassword(pss);
				u.setTipoutente(tip);
				utenteDao.updateUser(u);
				
				int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				request.setAttribute("utenteCorrente", utenteCorrente);
				getUser(request,response);
				
			}else {
				int utenteId = Integer.parseInt(request.getParameter("utenteId"));
				utenteDao.deleteUser(utenteId);
				
				int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				request.setAttribute("utenteCorrente", utenteCorrente);
				getUser(request,response);
			}
	}
	
	
	
	private void preCompilaForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in in memoria è " + request.getAttribute("utenteCorrente"));
		int utenteId = Integer.parseInt(request.getParameter("utenteId"));
		Utente u = new Utente();
		u = utenteDao.getSingoloUtente(utenteId);
		request.setAttribute("utente", u);
		try {
			RequestDispatcher dis = request.getRequestDispatcher("/formModifica.jsp");
			dis.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
