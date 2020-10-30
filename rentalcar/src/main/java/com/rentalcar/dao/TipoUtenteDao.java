package com.rentalcar.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.rentalcar.util.HibernateUtil;

public class TipoUtenteDao {

		public String getTypeDao(int n) {
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				Query qr = session.createQuery("SELECT tipo FROM TipoUtente WHERE id_tipo= :id");
				qr.setParameter("id", n);
				String tipo_user = qr.getResultList().toString();
				tipo_user = tipo_user.replaceAll("\\[", "").replaceAll("\\]", "");
				return tipo_user;
			}
		}
	
	
	
}
