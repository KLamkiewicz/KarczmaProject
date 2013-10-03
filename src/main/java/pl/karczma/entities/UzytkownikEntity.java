package pl.karczma.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uzytkownicy")
public class UzytkownikEntity {
	@Id
	@GeneratedValue
	private int id;
	private String nick;
	private String imie;
	private String nazwisko;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String status;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "uzytkownik_sesja", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_sesja") })
	private Collection<SesjaEntity> sesje = new ArrayList<SesjaEntity>();

	public Collection<SesjaEntity> getSesje() {
		return sesje;
	}

	public void setSesje(Collection<SesjaEntity> sesje) {
		this.sesje = sesje;
	}

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

}
