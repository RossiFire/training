package com.rentalcar.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalcar.model.Prenotazione;
import com.rentalcar.model.Utente;
import com.rentalcar.util.HibernateUtil;

public class PrenotazioneDao {

	
	
	public List<Prenotazione> getPrenotazioni(){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Prenotazione", Prenotazione.class).list();
		}		
	}
	
	
	public void savePrenotazione(Prenotazione p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(p);
			session.flush();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	public void aupdatePrenotazione(Prenotazione p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(p);
			session.getTransaction().commit();
		}
	}
	
	
	public void deletePrenotazione(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Prenotazione WHERE id= :uid");
			query.setParameter("uid", id);
			query.executeUpdate();
			transaction.commit();
		}
	}
	
	
	
	public List<Prenotazione> getPrenotazioniUtente(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			String hql = "FROM Prenotazione WHERE utentePrenotato = :uid";
			Query query = session.createQuery(hql);
			query.setParameter("uid", id);
			List<Prenotazione> prenotazioni = query.getResultList();
			transaction.commit();
			System.out.println("tutto bene nella funzione");
			return prenotazioni;
			
		}
		
	}

	
	
	
	
}
