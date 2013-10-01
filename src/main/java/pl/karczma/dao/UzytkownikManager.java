package pl.karczma.dao;

import org.hibernate.Session;

import pl.karczma.entities.UzytkownikEntity;
import pl.karczma.utils.HibernateFactory;

public class UzytkownikManager {

	public void save(UzytkownikEntity uzytkownikEntity) {

		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		session.save(uzytkownikEntity);

		session.getTransaction().commit();
	}
}
