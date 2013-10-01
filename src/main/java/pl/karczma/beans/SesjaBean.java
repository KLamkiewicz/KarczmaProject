package pl.karczma.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import pl.karczma.dao.SesjaManager;
import pl.karczma.entities.SesjaEntity;

@ManagedBean(name="sesjaBean")
@RequestScoped
public class SesjaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Date data;
	private String opis;
	private String system;
	@ManagedProperty(value="#{uzytkownikBean}")
	private UzytkownikBean mg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public UzytkownikBean getMg() {
		return mg;
	}
	public void setMg(UzytkownikBean mg) {
		this.mg = mg;
	}
	
	public SesjaEntity convertToEntity(){
		
		SesjaEntity sesjaEntity = new SesjaEntity();
		sesjaEntity.setName(this.name);
		sesjaEntity.setData(this.data);
		sesjaEntity.setOpis(this.opis);
		sesjaEntity.setSystem(this.system);
		sesjaEntity.setMg(this.mg.convertToEntity());
		
		return sesjaEntity;
	}
	
	public void save() {
		
		SesjaManager sesjaManager = new SesjaManager();
		sesjaManager.save(this.convertToEntity());
	}
	
}
