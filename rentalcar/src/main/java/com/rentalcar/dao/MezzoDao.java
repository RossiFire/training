package com.rentalcar.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rentalcar.model.Mezzo;
import com.rentalcar.util.HibernateUtil;

public class MezzoDao {

	
	
			public List<Mezzo> getMezzi(){
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					return session.createQuery("from Mezzo", Mezzo.class).list();
				}	
			}
	
			
			public void saveMezzo(Mezzo m) {
				Transaction transaction = null;
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					transaction = session.beginTransaction();
					session.save(m);
					transaction.commit();
					session.close();
				} catch (Exception e) {
					if (transaction != null) {
						transaction.rollback();
					}
					e.printStackTrace();
				}
			}

			
			public Mezzo getSingoloMezzo(int id) {
				Transaction transaction = null;
				Mezzo m = new Mezzo();
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					// start transaction
					transaction = session.beginTransaction();
					String hql = "FROM Mezzo WHERE id= :uid";
					Query query = session.createQuery(hql);
					query.setParameter("uid", id);
					m = (Mezzo) query.getSingleResult();
					transaction.commit();
					return m;
				}
				
			}
			
			
			
			public void updateMezzo(Mezzo mezzo) {
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					session.beginTransaction();
					session.update(mezzo);
					session.getTransaction().commit();
				}
			}
			
			
			public void deleteMezzo(int id) {
				Transaction transaction = null;
				try (Session session = HibernateUtil.getSessionFactory().openSession()) {
					transaction = session.beginTransaction();
					Query query = session.createQuery("DELETE FROM Mezzo WHERE id= :uid");
					query.setParameter("uid", id);
					query.executeUpdate();
					transaction.commit();
				}
			}
			
}
