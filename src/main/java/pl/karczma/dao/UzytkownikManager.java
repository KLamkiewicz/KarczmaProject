package pl.karczma.dao;

import org.hibernate.Session;

import pl.karczma.entities.UserAuthEntity;
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
	
	public void saveUserAuth(String email, String password) {
		
		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		UserAuthEntity uaEntity = new UserAuthEntity();
		uaEntity.setEmail(email);
		uaEntity.setHaslo(password);
		
		session.save(uaEntity);

		session.getTransaction().commit();
	}
	
	public UserAuthEntity getAuthenticationObject(String email) {
		
		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		UserAuthEntity uaEntity = new UserAuthEntity();

		try { 
			uaEntity = (UserAuthEntity) session.get(UserAuthEntity.class, email);
		} catch(Exception E) {
			System.out.println("exception");
		}
		
		session.getTransaction().commit();
		
		return uaEntity;
	}
}
