package pl.karczma.beans;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
//import javax.faces.component.UIViewRoot;

import pl.karczma.dao.UzytkownikManager;
import pl.karczma.entities.UserAuthEntity;
import pl.karczma.entities.UzytkownikEntity;
import pl.karczma.utils.PasswordHashingUtils;

@ManagedBean(name = "userAuthBean")
@RequestScoped
public class UserAuthBean implements Serializable {

	private static final long serialVersionUID = 4971762672119119698L;
	private String email;
	private String haslo;
	@ManagedProperty(value = "#{zalogowanyUzytkownikBean}")
	private ZalogowanyUzytkownikBean zalogowanyUzytkownik;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public ZalogowanyUzytkownikBean getZalogowanyUzytkownik() {
		return zalogowanyUzytkownik;
	}

	public void setZalogowanyUzytkownik(
			ZalogowanyUzytkownikBean zalogowanyUzytkownik) {
		this.zalogowanyUzytkownik = zalogowanyUzytkownik;
	}

	// Metoda wywolywana automatycznie w template
	// (authorizationEvent)
	public void authenticate(ComponentSystemEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = context.getViewRoot();

		// Jezeli jakims sposobem autoryzacja zostalaby wywolana na stronie
		// logowania
		// - przerwij autoryzacje i pozostan na stronie logowania
		if (uiViewRoot.getViewId().equals("/root/loginPage.xhtml") ||
				uiViewRoot.getViewId().equals("/root/registerPage.xhtml"))
			return;

		// try dla sprawdzenia czy zalogowany uzytkownik w ogole istnieje
		try {
			// Jezeli email zostal zdewalidowany lub wyzerowany - przenies
			// uzytkownika na strone logowania
			if (zalogowanyUzytkownik.getEmail().equals(null)
					|| zalogowanyUzytkownik.getEmail() == null
					|| zalogowanyUzytkownik.getEmail().equals("")
					|| zalogowanyUzytkownik.getEmail() == "") {
				ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) context
						.getApplication().getNavigationHandler();

				navHandler
						.performNavigation("/root/loginPage?faces-redirect=true");
			}
			// W przypadku gdy zalogowany uzytkownik nie istnieje w ogole -
			// przenies na strone logowania
		} catch (java.lang.NullPointerException nullPointerException) {
			ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) context
					.getApplication().getNavigationHandler();
			navHandler.performNavigation("/root/loginPage?faces-redirect=true");
		}
	}

	public void logout() {

		// Wyzeruj email uzytkownika
		zalogowanyUzytkownik.setEmail(null);

		// Zdewaliduj sesjê
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		externalContext.invalidateSession();

		// Przekieruj uzytkownika do strony logowania
		FacesContext context = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) context
				.getApplication().getNavigationHandler();
		navHandler.performNavigation("/root/loginPage?faces-redirect=true");
	}

	public void login() {

		//Przygotuj navigation handler dla ewentualnego przeniesienia do dashboardu
		FacesContext context = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler navHandler = (ConfigurableNavigationHandler) context
				.getApplication().getNavigationHandler();
		
		//Pobierz obiekt autoryzacji dla danego adresu email
		UzytkownikManager uzytkownikManager = new UzytkownikManager();
		UserAuthEntity ueAuth = uzytkownikManager.getAuthenticationObject(this.email);
		
		//Sprawdz czy zostal pobrany uzytkownik. Brak pobranego uzytkownika oznacza przekazanie
		//nieistniejacego adresu email. Przekieruj do strony logowania w takim wypadku
		try {
			ueAuth.getEmail();
		} catch(java.lang.NullPointerException nullPointerException) {
			//Instrukcje obslugujace bledny adres email
			navHandler.performNavigation("/root/loginPage?faces-redirect=true");
			//Przerwij procedure logowania
			return;
		}
		
		//Przehashuj wpisane haslo i wyzeruj haslo w beanie
		PasswordHashingUtils phu = new PasswordHashingUtils();
		String passHash = phu.hashAndReturn(haslo);
		this.haslo = "";
		
		//Sprawdz zgodnosc hasel
		if(passHash.equals(ueAuth.getHaslo())) {
			//Haslo poprawne, ustaw dane zalogowanego uzytkownika i przejdz do dash'a
			zalogowanyUzytkownik.setEmail(this.email);
			navHandler.performNavigation("dashboard");
		} else {
			//Instrukcje w przypadku niepowodzenia logowania
		}
	}
	
	public void registerNewUser() {
		
		//Przygotuj nowego uzytkownika o emailu podanym w stronie jsf oraz statusie gracza
		UzytkownikEntity newUser = new UzytkownikEntity();
		newUser.setEmail(this.email);
		newUser.setStatus("Gracz");
		
		UzytkownikManager uzytkownikManager = new UzytkownikManager();
		PasswordHashingUtils phu = new PasswordHashingUtils();
		
		//Dodaj uzytkownika do bazy oraz utworz dla niego obiekt autoryzacji
		uzytkownikManager.saveUserAuth(this.email, phu.hashAndReturn(haslo));
		uzytkownikManager.save(newUser);
	}
	
}
