package classes;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private String identifiant;
	private String email;
	private List<Url> listeUrls;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'identifiant de l'Utilisateur
	 * @return Identifiant de l'Utilisateur [String]
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * Modifie la valeur identifiant de l'Utilisateur à partir du paramètre
	 * @param Le nouvel identifiant [String]
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	/**
	 * Retourne l'email de l'Utilisateur
	 * @return L'email de l'Utilisateur [String]
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Modifie la valeur email de l'Utilistateur à partir du paramètre
	 * @param Le nouvel email [String]
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Retourne la liste des Urls qu'a créé l'utilisateur
	 * @return La liste des Urls qu'a créé l'utilisateur [List<Url>]
	 */
	public List<Url> getListeUrls() {
		return listeUrls;
	}
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	public Utilisateur()
	{
		identifiant = "";
		email 		= "";
		listeUrls	= new ArrayList<Url>();
	}//fin Utilisateur()
	
	
	public Utilisateur(String identifiant, String email)
	{
		this.identifiant = identifiant;
		this.email 		 = email;
		listeUrls 		 = new ArrayList<Url>();
	}//fin Utilisateur(...)
	
	/* **********************************
	 * M É T H O D E S
	 * ******************************* */
	/**
	 * Retourne une chaine contenant les informations de l'Utilisateur
	 * @return [String]
	 */
	@Override
	public String toString()
	{
		return "Identifiant : " + identifiant + ", Adresse email : " + email;
	}//fin toString
}//fin classe