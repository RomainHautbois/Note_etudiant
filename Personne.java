package ges;


public class Personne {
	int id;
	float moy1,moy2,moy3,moyan;
	String  matricule,nom,daten,sexe,classe,tuteur,constat;byte[] photo;
	
	
	
	public Personne(){
		
	}



	public Personne(int id, float moy1, float moy2, float moy3, float moyan, String matricule, String nom, String daten,
			String sexe, String classe, String tuteur,String constat, byte[] photo) {
		super();
		this.id = id;
		this.moy1 = moy1;
		this.moy2 = moy2;
		this.moy3 = moy3;
		this.moyan = moyan;
		this.matricule = matricule;
		this.nom = nom;
		this.daten = daten;
		this.sexe = sexe;
		this.classe = classe;
		this.tuteur = tuteur;
		this.constat=constat;
		this.photo = photo;
		
	}



	public int getId() {
		return id;
	}



	public float getMoy1() {
		return moy1;
	}



	public float getMoy2() {
		return moy2;
	}



	public float getMoy3() {
		return moy3;
	}



	public float getMoyan() {
		return moyan;
	}



	public String getMatricule() {
		return matricule;
	}



	public String getNom() {
		return nom;
	}



	public String getDaten() {
		return daten;
	}



	public String getSexe() {
		return sexe;
	}



	public String getClasse() {
		return classe;
	}



	public String getTuteur() {
		return tuteur;
	}

	public String getConstat() {
		return constat;
	}

	public byte[] getPhoto() {
		return photo;
	}



	




	
	
		
	}
	



