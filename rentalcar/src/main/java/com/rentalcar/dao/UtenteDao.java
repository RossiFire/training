package com.rentalcar.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.jdbc.internal.LogicalConnectionManagedImpl;

import com.rentalcar.model.Prenotazione;
import com.rentalcar.model.Utente;
import com.rentalcar.util.HibernateUtil;


public class UtenteDao {


	
	
	public void saveUser(Utente user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(user);
			// commit transaction
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

	
	
	public int checkUser(Utente user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			String hql = "FROM Utente WHERE nome = :username AND password= :pwd";
			Query query = session.createQuery(hql);
			query.setParameter("username", user.getNome());
			query.setParameter("pwd", user.getPassword());
			List<Utente> utente = query.getResultList();
				if(utente.isEmpty()) {
					return -1;
				}else {
					for(Utente ut: utente) {
						if(ut.getTipoutente().getTipo().equals("ADMIN")) {
							return 1;
						}else {
							return 0;
						}
					}
				}
		}catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				return -1;
			}
			e.printStackTrace();
		}
		return -99;
	}
	
	
	
	public Utente getCurrentUserInfo(Utente user) {
		Transaction transaction = null;
		Utente u = new Utente();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			String hql = "FROM Utente WHERE nome = :username AND password= :pwd";
			Query query = session.createQuery(hql);
			query.setParameter("username", user.getNome());
			query.setParameter("pwd", user.getPassword());
			u = (Utente) query.getSingleResult();
			transaction.commit();
			return u;
		}
	}
	
	
	
	public Utente getCurrentUserInfoNome(Utente user) {
		Transaction transaction = null;
		Utente u = new Utente();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			String hql = "FROM Utente WHERE nome = :username AND id= :pwd";
			Query query = session.createQuery(hql);
			query.setParameter("username", user.getNome());
			query.setParameter("pwd", user.getId());
			u = (Utente) query.getSingleResult();
			transaction.commit();
			return u;
		}
	}

	
	
	
	
	public List <Utente> getUser(){
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				return session.createQuery("from Utente", Utente.class).list();
			}		
		}
		
		
	
	public Utente getSingoloUtente(int id) {
		Transaction transaction = null;
		Utente u = new Utente();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start transaction
			transaction = session.beginTransaction();
			String hql = "FROM Utente WHERE id= :uid";
			Query query = session.createQuery(hql);
			query.setParameter("uid", id);
			u = (Utente) query.getSingleResult();
			transaction.commit();
			return u;
		}
		
	}

		
		
	public void updateUser(Utente user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		}
	}
		
	
	
	public void deleteUser(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Utente WHERE id= :uid");
			query.setParameter("uid", id);
			query.executeUpdate();
			transaction.commit();
		}
	}

		
		
}