package util;

public class FonctionsString {
	
	/**
	 * Découpe une chaine de caractères en un tableau de chaines.
	 * Ex : "Hello world!" ==> tableau[0] = "Hello" / tableau[1] = "world!"
	 * 
	 * @param La chaine à découper [String]
	 * @return Le tableau de chaines [String[]]
	 */
	public static String[] decouperUneChaine(String chaine)
	{
		return chaine.split(" ");
	}//fin decouperUneChaine
	
	/**
	 * Retourne un String contenant tous les éléments du tableau (passé en paramètre) séparés par un ";"
	 * 
	 * Cette fonction sert principalement à réaliser un test unitaire de la fonction decouperUneChaine()
	 * @param La liste de Strings [List<String>]
	 * @return La chaine composée grâce aux éléments de la liste [String] Ex : "premier;deuxieme;troisieme;"
	 */
	public static String getTableauEnString(String[] tableauChaines)
	{
		//On créer une chaine vide
		String resultat = "";
		//Pour chaue ligne du tableau passé en paramètres
		for(int i = 0 ; i < tableauChaines.length ; i++)
		{
			//On ajoute le contenu de la ligne au résultat, avec un ";" à la fin
			resultat += tableauChaines[i] + ";";
		}//fin for
		return resultat;
	}//fin getListeEnString
}//fin classe
