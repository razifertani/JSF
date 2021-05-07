package tp.front.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_EMPLOYE")
public class Employe implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String prenom;
	private String nom;
	private String email;
	private String password;

	public Employe(String prenom, String nom, String email, boolean actif, Role role, String password) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.isActif = actif;
		this.role = role;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	private boolean  isActif;

	@Enumerated(EnumType.STRING)
	Role role;


	public Employe() {

	}


	
	

	public Employe(Long id, String prenom, String nom, String email, boolean isActif,
			Role role, String password) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.isActif = isActif;
		this.role = role;
		this.password = password;

	}

/***********crud***/
	public Employe(int id, String prenom, String nom, String email, String
			password, boolean actif, Role role) {
			super();
			this.id = (long) id;
			this.prenom = prenom;
			this.nom = nom;
			this.email = email;
			this.password = password;
			this.isActif = actif;
			this.role = role;
			}



	public Employe(String prenom, String nom, String email, boolean isActif,Role role) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.isActif = isActif;
		this.role = role;
		
	}





	public Long getId() {
		return id;
	}

	
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(boolean isActif) {
		this.isActif = isActif;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@Override
	public String toString() {
		return "Employe [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", isActif=" + isActif
				 + "]";
	}

}
