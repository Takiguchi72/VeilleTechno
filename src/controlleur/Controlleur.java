package controlleur;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vues.FenetrePrincipale;
import classes.Url;
import dao.DAO;
import dao.UrlDAO;

public class Controlleur implements ActionListener, MouseListener {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private FenetrePrincipale laFenetre;
	private DAO<Url> listeUrl;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'attribut listeUrl du Controlleur
	 * @return L'attribut listeUrl [DAO<Url>]
	 */
	public DAO<Url> getListeUrl() {
		return listeUrl;
	}//fin getListeUrl
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */

	/**
	 * Constructeur par défaut
	 */
	public Controlleur()
	{
		super();
		listeUrl = new UrlDAO();
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	/**
	 * Permet de définir la fenetre principale du controlleur principal
	 * @param laFenetre - La fenetre principal à définir [FenetrePrincipale]
	 */
	public void ajouterFenetrePrincipale(FenetrePrincipale laFenetre)
	{
		this.laFenetre = laFenetre;
	}//fin ajouterFenetrePrincipale
	
	/**
	 * Gestion des évènements du logiciel
	 * @param e - L'évenement détecté [ActionEvent]
	 */
	public void actionPerformed(ActionEvent e)
	{
		//-------------------------------------------------------------
		// B O U T O N   Q U I T T E R   -   B A R R E   D E   M E N U
		//-------------------------------------------------------------
		if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitQuitter())
		{
			System.exit(0);
		}//fin if
		//-----------------------------------------------------------------
		// B O U T O N   C O N N E X I O N   -   B A R R E   D E   M E N U
		//-----------------------------------------------------------------
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitConnexion())
		{
			
		}//fin if
		//---------------------------------------------------------------------
		// B O U T O N   C O N N E X I O N   -   P A N E L   C O N N E X I O N 
		//---------------------------------------------------------------------
		else if(e.getSource() == laFenetre.getPanelConnexion().getBtnConnexion())
		{
			try{
				//On vérifie que les champs ne sont pas vides
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getTxbIdentifiant());
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getPswdField());
			} catch (Exception ex) {
				//On affiche l'erreur dans le label d'erreurs
				ErrorManagement.showError(laFenetre.getPanelConnexion().getLblErreur(), ex.getMessage(), 1);
			}//fin catch
		}//fin else if
		//-------------------------------------------------------------------------
		// B O U T O N   R E C H E R C H E R   -   P A N E L   R E C H E R C H E R
		//-------------------------------------------------------------------------
		else if(e.getSource() == laFenetre.getPanelRecherche().getBtnRechercher())
		{
			try {
				//On vérifie que le champ de recherche n'est pas vide
				ErrorManagement.checkEmptyField(laFenetre.getPanelRecherche().getTxbRecherche());
				laFenetre.getPanelRecherche().afficherTableDUrls(this, laFenetre.getPanelRecherche().getTxbRecherche().getText().toString());
			} catch (Exception ex) {
				//On cache le scrollPane
				laFenetre.getPanelRecherche().getScrollPane().setVisible(false);
				
				//On affiche l'erreur dans le label d'erreurs
				ErrorManagement.showError(laFenetre.getPanelRecherche().getLblErreur(), ex.getMessage(), 1);
			}//fin catch
		}//fin else if
	}//fin actionPerformed
	
	/**
	 * Gestion des clics du logiciel
	 * @param e - Le clic détecté [MouseEvent]
	 */
	public void mouseClicked(MouseEvent e) {
		//-----------------------------------------------------------------------------------
		// U N E   L I G N E   D E   L A   J T A B L E   -   P A N E L   R E C H E R C H E R
		//-----------------------------------------------------------------------------------
		if(e.getSource() == laFenetre.getPanelRecherche().getTableUrls())
		{
			try {
				Point p = e.getPoint();
                int row = laFenetre.getPanelRecherche().getTableUrls().rowAtPoint(p);
                if (row >= 0)
                {
                	Runtime runtime = Runtime.getRuntime();
    				runtime.exec("firefox " + laFenetre.getPanelRecherche().getTableUrls().getValueAt(row,2).toString());
                	//System.out.println(laFenetre.getPanelRecherche().getTableUrls().getValueAt(row,2).toString() + "\n\n");
                }//fin if
			} catch (Exception ex) {
				ErrorManagement.showError(laFenetre.getPanelRecherche().getLblErreur(), ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			}//fin catch
		}//fin if
	}//fin mouseClicked

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
}//fin classe