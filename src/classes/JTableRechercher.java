package classes;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import controlleur.Controlleur;

public class JTableRechercher extends JTable {
	//entete correspond aux Entetes qui seront affichés dans la JTable, soit les attributs de la classe Url (4)
	private static final String[] entete = { "Id", "Intitulé", "Adresse", "Créateur" };
	
	/**
	 * Constructeur de la classe JTableRechercher
	 * @param Liste d'<Url>s qui sera convertie en tableau d'Objets pour initialiser la JTable [List<Url>]
	 */
	public JTableRechercher(List<Url> listeDUrl)
	{
		//On instancie la JTable à partir de la liste qui sera convertie en tableau d'objets, et les entetes
		super(getDonneesFromList(listeDUrl), entete);
		//On empeche l'ajustement automatique de la JTable
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
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
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
              if (e.getClickCount() == 2) {
                Point p = e.getPoint();
                
                int row = rowAtPoint(p);
                int column = convertColumnIndexToModel(columnAtPoint(p));
                if (row >= 0 && column >= 0) {
                	System.out.println("T'as cliqué sur la " + (row + 1) + "eme ligne et la " + (column + 1) + "eme colone");
                	System.out.println(getValueAt(row,2).toString());
                }
              }
            }
        });
	}//fin constructeur
	
	public JTableRechercher(Controlleur controlleurPrincipal)
	{
		//On instancie la JTable à partir de la liste qui sera convertie en tableau d'objets, et les entetes
				super(getDonneesFromList(controlleurPrincipal.getListeUrl().selectAll()), entete);
				//On empeche l'ajustement automatique de la JTable
				setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
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
		        
		        addMouseListener(controlleurPrincipal);
	}
	
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
			liste[i][3] = listeDUrls.get(i).getCreateur().getIdentifiant();
			i++;
		}//fin while
		return liste;
	}//fin getDonneesFromList
	
	/**
	 * Modifie l'alignement du contenu de la colonne passée en paramètre en fonction de l'alignement choisi
	 * @param La colonne à modifier [TableColumn]
	 * @param L'alignement {"right","center", Autre chose : "left"} [String]
	 */
	private void alignerContenuColonne(TableColumn colonne, String alignement)
	{
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
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
        colonne.setCellRenderer(renderer);
	}//fin alignerContenuColonne
}//fin classe