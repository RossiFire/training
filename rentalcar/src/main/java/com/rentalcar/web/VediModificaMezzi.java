package com.rentalcar.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rentalcar.dao.MezzoDao;
import com.rentalcar.model.Mezzo;
import com.rentalcar.model.TipoMezzo;
/**
 * Servlet implementation class VediModificaMezzi
 */
@WebServlet("/VediModificaMezzi")
public class VediModificaMezzi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VediModificaMezzi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    private MezzoDao mezzoDao;
	public void init(ServletConfig config) throws ServletException {
		mezzoDao = new MezzoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	String comando = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VISUALIZZARE TUTTI I MEZZI
		comando = request.getParameter("comando");
		if(comando!=null) {
			doPost(request,response);
		}else {
			int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
			request.setAttribute("utenteCorrente", utenteCorrente);
			getMezzi(request,response);			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// AGGIUNGI, MODIFICA ED ELIMINA MEZZO
		
		comando = request.getParameter("comando");
		Mezzo m;
		int idMezzo;
		TipoMezzo tipomezzo;
		String casaCostr;
		String modello;
		int tipo;
		String targa;
		
		switch(comando) {
			
			case "AGGIUNGI":
				m = new Mezzo();
				casaCostr = request.getParameter("casaCostr");
				modello = request.getParameter("modello");
				tipo = Integer.parseInt(request.getParameter("dropdown"));
				targa = request.getParameter("targa");
				
				tipomezzo = new TipoMezzo();
				m.setCasaCostr(casaCostr);
				m.setModello(modello);
				tipomezzo.setId(tipo);
				m.setTipomezzo(tipomezzo);
				m.setTarga(targa);
				
				mezzoDao.saveMezzo(m);
				comando= null;
				int utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				request.setAttribute("utenteCorrente", utenteCorrente);
				getMezzi(request,response);
				break;
				
			case "LOAD":
				System.out.println("sono nel load");
				m = new Mezzo();
				idMezzo = Integer.parseInt(request.getParameter("mezzoid"));
				m = mezzoDao.getSingoloMezzo(idMezzo);
				request.setAttribute("mezzo", m);
				comando = null;
				utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				request.setAttribute("utenteCorrente", utenteCorrente);
				RequestDispatcher dis = request.getRequestDispatcher("formModificaMezzo.jsp");
				dis.forward(request, response);
				break;
				
			case "MODIFICA":
				System.out.println("Sono nel modifica");
				m = new Mezzo();
				tipomezzo = new TipoMezzo();
				idMezzo = Integer.parseInt(request.getParameter("id"));
				casaCostr = request.getParameter("casaCostr");
				modello = request.getParameter("modello");
				tipo = Integer.parseInt(request.getParameter("dropdown"));
				targa = request.getParameter("targa");
				utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				System.out.println("id è " + utenteCorrente);
				request.setAttribute("utenteCorrente", utenteCorrente);
				
				m.setId(idMezzo);
				m.setCasaCostr(casaCostr);
				m.setModello(modello);
				tipomezzo.setId(tipo);
				m.setTipomezzo(tipomezzo);
				m.setTarga(targa);
				mezzoDao.updateMezzo(m);
				comando = null;
				getMezzi(request,response);
				break;
				
			case "ELIMINA":
				idMezzo = Integer.parseInt(request.getParameter("mezzoid"));
				mezzoDao.deleteMezzo(idMezzo);
				comando = null;
				utenteCorrente = Integer.parseInt(request.getParameter("utenteCorrente"));
				request.setAttribute("utenteCorrente", utenteCorrente);
				getMezzi(request,response);
				break;
			default:
				System.out.println("c'è qualche problema");
		}
		
		
		
	}
	
	
	
	
	
	
	public void getMezzi(HttpServletRequest request, HttpServletResponse response) {
		
		List <Mezzo> mezzi = mezzoDao.getMezzi();
		ArrayList <Mezzo> tuttiMezzi = new ArrayList<>();
		
		for(Mezzo m: mezzi) {
			tuttiMezzi.add(m);
		}
		request.setAttribute("listaMezzi", tuttiMezzi);
	
		
		try {
			request.getRequestDispatcher("mezziAdmin.jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}

	
	
}
