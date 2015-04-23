package classes;

public class ModeleTableModifier extends ModeleTableAjout {
	
	/**
	 * Constructeur par défaut
	 */
	public ModeleTableModifier() {
		super();
	}
    
	/**
	 * Réinitialise la liste de Tags en fonction de l'Url qui a été selectionné dans la liste déroulante
	 * @param L'Url selectionné [Url]
	 */
    public void reinitialiser(Url urlSelectionne)
    {
    	//On vide la liste de tags
    	listeTags.clear();
    	
    	//On ajoute tous les tags de l'url sélectionné à la liste du modèle
    	for(Tag t : urlSelectionne.getListeTagsAssocies())
    	{
    		listeTags.add(t);
    	}//fin foreach
    	
    	//Enfin, on met à jour la vue
    	fireTableStructureChanged();
    }//fin reinitialiserEnFonctionDeLUrlSelectionne
}//fin classe
