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
		
		SesjaEntity sesja2 = new SesjaEntity();
		sesja2.setData(new Date());
		sesja2.setName("Sesja Chuja");
		sesja2.setOpis("To jest sesja Chuja");
		sesja2.setSystem("WH");
		sesja2.setMg(user2);
		
		
		session.save(user1);
		session.save(user2);
		session.save(sesja1);
		session.save(sesja2);
		
		UzytkownikEntity ue1 = new UzytkownikEntity();
		ue1 = (UzytkownikEntity) session.get(UzytkownikEntity.class, 1);
		ue1.getSesje().add(sesja1);
		ue1.getSesje().add(sesja2);
		
		UzytkownikEntity ue2 = new UzytkownikEntity();
		ue2 = (UzytkownikEntity) session.get(UzytkownikEntity.class, 2);
		ue2.getSesje().add(sesja1);
		ue2.getSesje().add(sesja2);
		
		session.save(ue1);
		session.save(ue2);
		//session.save(sesja1);
		
		session.getTransaction().commit();
		System.out.println("--- end b1 test ---");
		
	}
	
	public void basicTest2() {

		System.out.println("----b2 test----");
		
		Session session = HibernateFactory.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		
		UzytkownikEntity userZapisany = new UzytkownikEntity();
		userZapisany = (UzytkownikEntity) session.get(UzytkownikEntity.class, 1);
		
		SesjaEntity sesjaZapisana = new SesjaEntity();
		sesjaZapisana = (SesjaEntity) session.get(SesjaEntity.class, 1);
		
		session.getTransaction().commit();
		
		System.out.println("------------------------------------");
		System.out.println(userZapisany.getSesje().size());
		System.out.println(sesjaZapisana.getGracze().size());
		
		
	}
	
	public void oszustwo() {
		
	}
}
