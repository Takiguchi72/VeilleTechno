package classes;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import controlleur.Controlleur;

public class JTableRechercher extends JTable {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	//"entetes" correspond aux entetes du tableau, soit les attributs de la classe Url (4)
	private static final String[] entetes = { "Id", "Intitulé", "Adresse", "Créateur" };
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'attribut entetes de la classe JTableRecherche - /!\ Attribut de Classe et non d'objet /!\
	 * @return Les entetes de la JTableRechercher [String[]]
	 */
	public static String[] getEntetes()
	{
		return entetes;
	}//fin getEntetes
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Constructeur de la classe JTableRechercher
	 * @param Le controlleur principal pour pouvoir récupérer les données de la BDD [Controlleur]
	 */
	public JTableRechercher(Controlleur controlleurPrincipal)
	{
		super();
		//On instancie la JTable à partir de la liste (alimentée par la bdd) qui sera convertie en tableau d'objets, et les entetes
        setModel(new DefaultTableModel(getDonneesFromList(controlleurPrincipal.getListeUrl().selectAll()), entetes));
        
        //On met en forme le tableau (définit la taille et l'alignement des colonnes)
        mettreEnFormeLeTableau();
        
        //On ajoute le controlleur principal comme MouseListener
        addMouseListener(controlleurPrincipal);
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
	 * Réinitialise le contenu du tableau à partir des données de la BDD, et redéfinit le style des colonnes du tableau
	 * @param Le controlleur principal pour pouvoir récupérer les données de la BDD [Controlleur]
	 * @param Une chaine contenant les criteres de selection des Urls [String]
	 * @throws Lève une exception lorsqu'il est impossible de récupérer les données de la base [SQLException]
	 */
	public void updateTable(Controlleur controlleurPrincipal, String recherche) throws Exception
	{
		//On génère un nouveau model grâce aux données correspondantes à la recherche passée en paramètres
		setModel(new DefaultTableModel(getDonneesFromList(controlleurPrincipal.getListeUrl().selectCorrespondantA(recherche)), entetes));
		
		//On empeche l'ajustement automatique de la JTable
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//On met en forme le tableau (définit la taille et l'alignement des colonnes)
		mettreEnFormeLeTableau();
	}//fin updateTable
	
	/**
	 * Convertit une liste d'<Url>s en tableau d'<Object>s
	 * @param La liste d'<Url>s à convertir [List<Url>]
	 * @return Le tableau correspondant à la convertion [Object[][]]
	 */
	private static Object[][] getDonneesFromList(List<Url> listeAConvertir)
	{
		//On créer un tableau à deux dimentions :
		// D1 -> Les Urls
		// D2 -> Les données de l'url
		//Une Url n'a que 4 attributs (d'où le 4 dans l'initialisation)
		Object[][] tableauConvertit = new Object[listeAConvertir.size()][4];
		
		int i = 0;	//Compteur
		//Pour chaque élément de la liste, on va renseigner la ième ligne
		while ( i < listeAConvertir.size())
		{
			tableauConvertit[i][0] = listeAConvertir.get(i).getId();
			tableauConvertit[i][1] = listeAConvertir.get(i).getIntitule();
			tableauConvertit[i][2] = listeAConvertir.get(i).getAdresse();
			tableauConvertit[i][3] = listeAConvertir.get(i).getCreateur().getIdentifiant();
			i++;
		}//fin while
		return tableauConvertit;
	}//fin getDonneesFromList
	
	private void mettreEnFormeLeTableau()
	{
		//On va définir la taille de chaque colonne de la table
		//On récupère la 1ère colonne
		TableColumn col = getColumnModel().getColumn(0);
		//On lui définit sa largeur
		col.setPreferredWidth(45);
		//On aligne son contenu pour le centrer
		alignerContenuColonne(col,"CENTER");

		//On fait de même avec les autres colonnes
		//2ème colonne
		col = getColumnModel().getColumn(1);
		col.setPreferredWidth(340);
		//3ème colonne
		col = getColumnModel().getColumn(2);
		col.setPreferredWidth(275);
		//4ème colonne
		col = getColumnModel().getColumn(3);
		col.setPreferredWidth(70);
		alignerContenuColonne(col,"CENTER");
	}
}//fin classe