package classes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeleTableRechercher extends AbstractTableModel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private List<Url> listeUrls;
	private final String[] entetes = { "Id", "Intitulé", "Adresse", "Créateur" };
	
	public List<Url> getListeUrls() {
		return listeUrls;
	}

	public void setListeUrls(List<Url> listeUrls) {
		this.listeUrls = listeUrls;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Constructeur par défaut
	 */
	public ModeleTableRechercher()
	{
		super();
		listeUrls = new ArrayList<Url>();
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
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

	/**
	 * Retourne l'attribut d'une Url en fonction de ses indexs dans la table
	 * @return L'attribut de l'Url [Object]
	 */
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
				return listeUrls.get(indexLigne).getCreateur().getIdentifiant();
		}//fin switch
	}//fin getValueAt
	

	/**
	 * Réinitialise la liste d'Urls en fonction des critères de recherche passées en paramètre via la variable "recherche"
	 * @param controlleurPrincipal
	 * @param recherche
	 * @throws SQLException
	 */
	public void updateUrlsEnFonctionDeLaRecherche(List<Url> nouvelleListe)
	{
		//On vide la liste d'url
		listeUrls.clear();
		
		//Puis on la "remplit" à partir de la nouvelle liste
		listeUrls = nouvelleListe;
		
		//Enfin, on met à jour la vue
		fireTableStructureChanged();
	}//fin updateUrlEnFonctionDeLaRecherche
}//fin classe