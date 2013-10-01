package pl.karczma.dao;

import org.hibernate.Session;

import pl.karczma.entities.SesjaEntity;
import pl.karczma.utils.HibernateFactory;

public class SesjaManager {

	public void save(SesjaEntity sesjaEntity) {

		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		session.save(sesjaEntity);

		session.getTransaction().commit();
	}
}
