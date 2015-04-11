package classes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controlleur.Controlleur;

public class ModeleTableRechercher extends AbstractTableModel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private List<Url> listeUrls = new ArrayList<Url>();
	private final String[] entetes = { "Id", "Intitulé", "Adresse", "Créateur" };
	
	public ModeleTableRechercher(Controlleur controlleurPrincipal)
	{
		super();
		listeUrls = controlleurPrincipal.getListeUrl().selectAll();
		
	}
	
	/**
	 * Retourne le nombre de colonnes du modèle
	 * @return Nombre de colonnes [int]
	 */
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	/**
	 * Retourne le nombre d'urls dans la liste du modèle
	 * @return Nombre d'url [int]
	 */
	@Override
	public int getRowCount() {
		return listeUrls.size();
	}
	
	/**
	 * Retourne le libelle de l'entete à l'index en paramètres
	 * @return Libellé de l'entete [String]
	 */
	public String getColumnName(int index) {
		return entetes[index];
	}

	@Override
	public Object getValueAt(int indexLigne, int indexColonne) {
		//En fonction du numéro de colonne choisi, on va retourner l'attribut de l'url correspondant
		switch(indexColonne)
		{
			case 0:
				return listeUrls.get(indexLigne).getId();
			case 1:
				return listeUrls.get(indexLigne).getIntitule();
			case 2:
				return listeUrls.get(indexLigne).getAdresse();
			default:
				return listeUrls.get(indexLigne).getCreateur();
		}//fin switch
	}//fin getValueAt
	
	
	public void updateUrlEnFonctionDeLaRecherche(Controlleur controlleurPrincipal, String recherche) throws Exception
	{
		//On vide la liste d'url,
		listeUrls.removeAll(listeUrls);
		
		//On la "rempli" à partir du résultat de la requete en fonction du critère de sélection
		listeUrls = controlleurPrincipal.getListeUrl().selectCorrespondantA(recherche);
	}//fin updateUrlEnFonctionDeLaRecherche
}//fin classe