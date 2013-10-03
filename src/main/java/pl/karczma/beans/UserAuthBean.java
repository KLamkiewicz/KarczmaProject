package pl.karczma.beans;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
//import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
		if (uiViewRoot.getViewId().equals("/root/loginPage.xhtml"))
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
}
