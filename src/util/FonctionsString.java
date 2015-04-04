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
	
	
	public static String getClausesWhere(String chaine)
	{
		//On va découper la recherche
		String[] tableauMots = util.FonctionsString.decouperUneChaine(chaine);
		String clauses = "WHERE intitule LIKE '%" + tableauMots[0] + "%' OR adresse LIKE '%" + tableauMots[0] + "%' "
					   + "OR createur LIKE '%" + tableauMots[0] + "%' "
					   + "OR id IN (SELECT id_url "
								 + "FROM \"veilletechnologique\".t_ligne_url_tag "
								 + "WHERE id_tag IN (SELECT id "
												 + "FROM \"veilletechnologique\".t_tag "
												 + "WHERE libelle LIKE '%" + tableauMots[0] + "%')) ";
		//Pour chaque ligne du tableau, on va ajouter des conditions dans le where de la requête
		for(int i = 1 ; i < tableauMots.length ; i++)
		{
			clauses += "OR intitule LIKE '%" + tableauMots[i] + "%' OR adresse LIKE '%" + tableauMots[i] + "%' "
					 + "OR createur LIKE '%" + tableauMots[i] + "%' "
					 + "OR id IN (SELECT id_url "
							   + "FROM \"veilletechnologique\".t_ligne_url_tag "
							   + "WHERE id_tag IN (SELECT id "
												+ "FROM \"veilletechnologique\".t_tag "
												+ "WHERE libelle LIKE '%" + tableauMots[i] + "%')) ";
		}
		return clauses;
	}
}//fin classe
