package pl.karczma.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;

import pl.karczma.entities.SesjaEntity;
import pl.karczma.entities.UzytkownikEntity;
import pl.karczma.utils.HibernateFactory;

@ManagedBean(name="testingBean")
@RequestScoped
public class TestingBean {

	public void basicTest() {

		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		UzytkownikEntity user1 = new UzytkownikEntity();
		user1.setEmail("lukskar@gmail.com");
		user1.setImie("£ukasz");
		user1.setNazwisko("Skar¿yñski");
		user1.setNick("Konio");
		user1.setStatus("Admin");
		
		UzytkownikEntity user2 = new UzytkownikEntity();
		user2.setEmail("mismaciej2@gmail.com");
		user2.setImie("Maciej");
		user2.setNazwisko("Miœ");
		user2.setNick("Miœ");
		user2.setStatus("Admin");
		
		SesjaEntity sesja1 = new SesjaEntity();
		sesja1.setData(new Date());
		sesja1.setName("Sesja Konia");
		sesja1.setOpis("To jest sesja Konia");
		sesja1.setSystem("SW");
		sesja1.setMg(user2);

		sesja1.getGracze().add(user1);
		sesja1.getGracze().add(user2);
		
		session.save(user1);
		session.save(user2);
		session.save(sesja1);
		
		session.getTransaction().commit();
	}
}
