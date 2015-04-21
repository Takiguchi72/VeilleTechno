package classes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeleTableAjout extends AbstractTableModel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private List<Tag> listeTags;
	private final String[] entetes = { "Libellé" };
	
	public List<Tag> getListeTags() {
		return listeTags;
	}

	/**
	 * Constructeur par défaut
	 */
	public ModeleTableAjout()
	{
		super();
		listeTags = new ArrayList<Tag>();
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
	 * Retourne le nombre de Tags dans la liste du modèle
	 * @return Nombre de Tags [int]
	 */
	@Override
	public int getRowCount() {
		return listeTags.size();
	}
	
	/**
	 * Retourne le libelle de l'entete à l'index en paramètres
	 * @return Libellé de l'entete [String]
	 */
	public String getColumnName(int index) {
		return entetes[index];
	}

	/**
	 * Retourne le libellé d'un Tag en fonction de son numéro de ligne dans la table
	 * @return Le libellé du Tag [Object]
	 */
	@Override
	public Object getValueAt(int indexLigne, int indexColonne) {
		return listeTags.get(indexLigne).getLibelle();
	}
	
	/**
	 * Ajoute un Tag à la liste et met à jour la vue
	 * @param Le nouveau tag à ajouter [Tag]
	 */
	public void ajouterTag(Tag nouveauTag) {
        listeTags.add(nouveauTag);
        fireTableRowsInserted(listeTags.size() -1, listeTags.size() -1);
    }

	/**
	 * Supprimer un Tag de la liste et met à jour la vue
	 * @param L'index du tag à supprimer dans la liste [int]
	 */
    public void supprimerTag(int rowIndex) {
        listeTags.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    /**
	 * Supprime tous les éléments de la liste
	 */
	public void removeAll()
	{
		//On supprime chaque élément de la liste
		listeTags.clear();
		//On indique au modèle que son contenu a changé, pour mettre à jour la vue
		fireTableDataChanged();
	}//fin removeAll
}//fin classe
