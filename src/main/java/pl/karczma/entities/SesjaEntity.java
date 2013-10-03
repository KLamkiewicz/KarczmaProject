package pl.karczma.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sesja")
public class SesjaEntity {
	@Id
	@GeneratedValue
	private int id_sesja;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	private String opis;
	private String system;
	@OneToOne
	@JoinColumn(nullable = false)
	private UzytkownikEntity mg;
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="sesje")
	private Collection<UzytkownikEntity> gracze = new ArrayList<UzytkownikEntity>();

	public int getId() {
		return id_sesja;
	}

	public void setId(int id) {
		this.id_sesja = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public UzytkownikEntity getMg() {
		return mg;
	}

	public void setMg(UzytkownikEntity mg) {
		this.mg = mg;
	}

	public Collection<UzytkownikEntity> getGracze() {
		return gracze;
	}

	public void setGracze(Collection<UzytkownikEntity> gracz) {
		this.gracze = gracz;
	}
}
