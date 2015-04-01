package classes;

import java.util.List;
import javax.swing.JTable;

public class JTableRechercher extends JTable {
	//entete correspond aux Entetes qui seront affichés dans la JTable, soit les attributs de la classe Url (4)
	private static final String[] entete = { "Id", "Intitulé", "Adresse", "Créateur" };
	
	/**
	 * Constructeur de la classe JTableRechercher
	 * @param Liste d'<Url>s qui sera convertie en tableau d'Objets pour initialiser la JTable [List<Url>]
	 */
	public JTableRechercher(List<Url> listeDUrl)
	{
		super(getDonneesFromList(listeDUrl), entete);
	}//fin constructeur
	
	/**
	 * Convertit une liste d'<Url>s en tableau d'<Object>s
	 * @param La liste d'<Url>s à convertir [List<Url>]
	 * @return Le tableau correspondant à la convertion [Object[][]]
	 */
	private static Object[][] getDonneesFromList(List<Url> listeDUrls)
	{
		//On créer un tableau à deux dimentions :
		// D1 -> Les Urls
		// D2 -> Les données de l'url
		//Une Url n'a que 4 attributs (d'où le 4 dans l'initialisation)
		Object[][] liste = new Object[listeDUrls.size()][4];
		
		int i = 0;	//Compteur
		//Pour chaque élément de la liste, on va renseigner la ième ligne
		while ( i < listeDUrls.size())
		{
			liste[i][0] = listeDUrls.get(i).getId();
			liste[i][1] = listeDUrls.get(i).getIntitule();
			liste[i][2] = listeDUrls.get(i).getAdresse();
			liste[i][3] = listeDUrls.get(i).getCreateur();
			i++;
		}//fin while
		return liste;
	}//fin getDonneesFromList
}//fin classe