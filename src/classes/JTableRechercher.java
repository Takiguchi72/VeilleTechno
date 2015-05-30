package classes;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import controlleur.Controlleur;

public class JTableRechercher extends JTable {
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Constructeur de la classe JTableRechercher
	 * @param Le modèle de la JTableRechercher [ModeleTableRechercher]
	 * @param Le controlleur principal pour pouvoir récupérer les données de la BDD [Controlleur]
	 */
	public JTableRechercher(ModeleTableRechercher modele, Controlleur controlleurPrincipal)
	{
		super(modele);
		
		//On met en forme le tableau (définit la taille et l'alignement des colonnes)
        mettreEnFormeLeTableau();
        
        //On ajoute le controlleur principal comme MouseListener
        addMouseListener(controlleurPrincipal);
        
        //On permet à l'utilisateur de trier le contenu en cliquant sur l'entete des colonnes
        //setAutoCreateRowSorter(true);
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	/**
	 * Modifie l'alignement du contenu de la colonne passée en paramètre en fonction de l'alignement choisi
	 * @param La colonne à modifier [TableColumn]
	 * @param L'alignement {"right","center", Autre chose -> "left"} [String]
	 */
	public void alignerContenuColonne(TableColumn colonne, String alignement)
	{
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		//En fonction de l'alignement passé en paramètre, on va définir l'alignement (droite, centré, ou à gauche)
		switch (alignement.toLowerCase())
		{
			case "right":
				renderer.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			case "center":
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				break;
			default:
				renderer.setHorizontalAlignment(SwingConstants.LEFT);
				break;
		}//fin switch
		
		//On définit l'alignement du contenu de la colonne
        colonne.setCellRenderer(renderer);
	}//fin alignerContenuColonne
	
	/**
	 * Applique un style personnalisé pour le contenu de chaque colonnes
	 */
	private void mettreEnFormeLeTableau()
	{
		super.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//On va définir la taille de chaque colonne de la table
		//On récupère la 1ère colonne
		TableColumn col = getColumnModel().getColumn(0);
		
		//On lui définit sa largeur
		col.setPreferredWidth(145);
		col.setMaxWidth(45);
		
		//On aligne son contenu pour le centrer
		alignerContenuColonne(col,"CENTER");

		//On fait de même avec les autres colonnes
		//2ème colonne
		col = getColumnModel().getColumn(1);
		col.setPreferredWidth(340);
		col.setMaxWidth(340);
		
		//3ème colonne
		col = getColumnModel().getColumn(2);
		col.setPreferredWidth(275);
		col.setMaxWidth(275);
		
		//4ème colonne
		col = getColumnModel().getColumn(3);
		col.setPreferredWidth(70);
		col.setMaxWidth(70);
		alignerContenuColonne(col,"CENTER");
	}//fin mettreEnFormeLeTableau
}//fin classe