package classes;

import java.util.ArrayList;
import java.util.List;

public class Url {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private int id;
	private String intitule;
	private String adresse;
	private Utilisateur createur;
	private List<Tag> listeTagAssocies;
	
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'id de l'Url
	 * @return Id de l'Url [Integer]
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Modifie la valeur id de l'Url à partir du paramètre
	 * @param Le nouvel id [Integer]
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	/**
	 * Retourne l'adresse de l'Url
	 * @return Adresse de l'Url [String]
	 */
	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * Modifie la valeur adresse de l'Url à partir du paramètre
	 * @param La nouvelle adresse [String]
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * Retourne le créateur de l'Url
	 * @return Le créateur de l'Url [Utilisateur]
	 */
	public Utilisateur getCreateur() {
		return createur;
	}

	/**
	 * Modifie le créateur de l'Url à partir de celui passé en paramètres
	 * @param Le createur qui remplacera l'ancien [Utilisateur]
	 */
	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}

	/**
	 * Retourne la liste des Tags associés à l'Url
	 * @return Liste des Tags associés à l'Url [List<Tag>]
	 */
	public List<Tag> getListeTagAssocies() {
		return listeTagAssocies;
	}
	
	/**
	 * Modifie la liste de Tags  associés à l'Url à partir de la liste passée en paramètres
	 * @param La nouvelle liste qui remplacera l'ancienne [List<Tag>]
	 */
	public void setListeTagAssocies(List<Tag> listeTagAssocies)
	{
		this.listeTagAssocies = listeTagAssocies;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Instancie un objet de la classe Url, avec 0 pour l'id, et "" pour l'adresse.
	 */
	public Url()
	{
		id 		 = 0;
		intitule = "";
		adresse  = "";
		createur = new Utilisateur();
		listeTagAssocies = new ArrayList<Tag>();
	}//fin Url()
	
	public Url(String intitule, String adresse, Utilisateur createur)
	{
		this.id			= 0;
		this.intitule 	= intitule;
		this.adresse 	= adresse;
		this.createur	= createur;
		this.listeTagAssocies = new ArrayList<Tag>();
	}
	
	/**
	 * Instancie un objet de la classe Url à partir des paramètres
	 * @param L'id de l'url [Integer]
	 * @param L'adresse url [String]
	 */
	public Url(int id, String intitule, String adresse, Utilisateur createur)
	{
		this.id		 	= id;
		this.intitule 	= intitule;
		this.adresse 	= adresse;
		this.createur	= createur;
		this.listeTagAssocies = new ArrayList<Tag>();
	}//fin Url(...)
	
	/* **********************************
	 * M É T H O D E S
	 * ******************************* */
	/**
	 * Retourne une chaine contenant les informations de l'Url
	 * @return [String]
	 */
	@Override
	public String toString()
	{
		return "Id : " + id + ", Intitule : " + intitule + ", Adresse : " + adresse + ", Createur : " + createur.getIdentifiant();
	}//fin toString
}//fin classe