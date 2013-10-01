package pl.karczma.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pl.karczma.dao.UzytkownikManager;
import pl.karczma.entities.UzytkownikEntity;

@ManagedBean(name = "uzytkownikBean")
@RequestScoped
public class UzytkownikBean implements Serializable {

	private static final long serialVersionUID = 2658595756756935227L;

	private int id;
	private String nick;
	private String imie;
	private String nazwisko;
	private String email;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UzytkownikEntity convertToEntity() {
		
		UzytkownikEntity uzytkownikEntity = new UzytkownikEntity();

		uzytkownikEntity.setNick(this.nick);
		uzytkownikEntity.setImie(this.imie);
		uzytkownikEntity.setNazwisko(this.nazwisko);
		uzytkownikEntity.setEmail(this.email);
		uzytkownikEntity.setStatus(this.status);

		return uzytkownikEntity;
	}

	public void save() {

		UzytkownikManager uzytkownikManager = new UzytkownikManager();
		uzytkownikManager.save(this.convertToEntity());
	}

}
