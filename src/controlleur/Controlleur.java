package controlleur;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vues.FenetrePrincipale;
import classes.Tag;
import classes.Url;
import classes.Utilisateur;
import dao.DAO;
import dao.UrlDAO;
import dao.UtilisateurDAO;
import static util.FonctionsString.md5;

public class Controlleur implements ActionListener, MouseListener {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private FenetrePrincipale laFenetre;
	private DAO<Url> listeUrl;
	private DAO<Tag> listeTag;
	private UtilisateurDAO lesUtilisateurs;
	private Utilisateur utilisateurConnecte;
	
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

	/**
	 * Retourne l'attribut listeTag du Controlleur
	 * @return L'attribut listeTag [DAO<Tag>]
	 */
	public DAO<Tag> getListeTag() {
		return listeTag;
	}
	
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
		lesUtilisateurs = new UtilisateurDAO();
		utilisateurConnecte = new Utilisateur();
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
			//On affiche le pannel de connexion
			afficherOuCacherPanelConnexion(true);
		}//fin else if
		//-----------------------------------------------------------------
		// B O U T O N   C O N S U L T E R   -   B A R R E   D E   M E N U
		//-----------------------------------------------------------------
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitConsulter())
		{
			//On cache le pannel de connexion et on affiche le pannel pour consulter les marques-page
			afficherOuCacherPanelConnexion(false);
		}//fin else if
		//---------------------------------------------------------------------
		// B O U T O N   C O N N E X I O N   -   P A N E L   C O N N E X I O N 
		//---------------------------------------------------------------------
		else if(e.getSource() == laFenetre.getPanelConnexion().getBtnConnexion())
		{
			try{
				//On vérifie que les champs ne sont pas vides
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getTxbIdentifiant());
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getPswdField());
				
				//On vérifie que les IDs saisis correspondent à un utilisateur de la base
				checkIdentifiants(laFenetre.getPanelConnexion().getTxbIdentifiant().getText(), String.valueOf(laFenetre.getPanelConnexion().getPswdField().getPassword()));
				
				//On cache le panel de connexion
				afficherOuCacherPanelConnexion(false);
				
				//On cache le bouton de connexion
				laFenetre.getLaBarreDeMenu().getMnitConnexion().setVisible(false);
				
				//On affiche le menu pour gérer son espace personnel
				laFenetre.getLaBarreDeMenu().getMnEspacePersonnel().setVisible(true);
			} catch (Exception ex) {
				//On affiche l'erreur dans le label d'erreurs
				ErrorManagement.showError(laFenetre.getPanelConnexion().getLblErreur(), ex.getMessage(), 1);
			}//fin catch
		}//fin else if
		//---------------------------------------------------------------------------
		// B O U T O N   S E   D É C O N N E C T E R   -   B A R R E   D E   M E N U 
		//---------------------------------------------------------------------------
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitEPDeconnexion())
		{
			//On cache le menu pour gérer son espace personnel
			laFenetre.getLaBarreDeMenu().getMnEspacePersonnel().setVisible(false);
			
			//On affiche le bouton de connexion
			laFenetre.getLaBarreDeMenu().getMnitConnexion().setVisible(true);
			
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
	
	/**
	 * Affiche ou cache le pannel de connexion en fonction du paramètre
	 * @param true -> Affiche le pannel ; false -> Cache le pannel
	 */
	private void afficherOuCacherPanelConnexion(boolean valeur)
	{
		//Si on cache le pannel de connexion, on va réinitialiser ses attributs
		if(valeur == false)
		{
			laFenetre.getPanelConnexion().getTxbIdentifiant().setText(null);
			laFenetre.getPanelConnexion().getPswdField().setText(null);
		}//fin if
		laFenetre.getPanelConnexion().setVisible(valeur);
		laFenetre.getLaBarreDeMenu().getMnitConnexion().setVisible(!valeur);
		laFenetre.getLaBarreDeMenu().getMnitConsulter().setVisible(valeur);
		laFenetre.getPanelRecherche().setVisible(!valeur);
		laFenetre.getPanelRecherche().getLblErreur().setVisible(false);
		laFenetre.getPanelConnexion().getLblErreur().setVisible(false);
	}//fin afficherOuCacherPanelConnexion
	
	/**
	 * Vérifie que les logs correspondent à un utlisateur dans la base
	 * @param identifiant
	 * @param motDePasse
	 */
	private void checkIdentifiants(String identifiant, String motDePasse) throws Exception
	{
		//On récupère un utilisateur à partir de la base de données
		Utilisateur unUtilisateur = lesUtilisateurs.read(identifiant);
		
		//Si le mot de passe passé en paramètres ne correspond pas au mdp de l'utilisateur récupéré, 
		//ou si l'identifiant de l'utilisateur est vide, c'est qu'il n'y a pas d'utilisateur dans la base ayant pour identifiant "identifiant".
		//Donc on va lever une exception
		if(!unUtilisateur.getPasswd().equals(md5(motDePasse)) || unUtilisateur.getIdentifiant().equals(""))
		{
			throw new Exception("Identifiant ou mot de passe incorrect !", new Throwable("authenticationFailed"));
		}//fin if
		
		//Sinon, on valide l'authentification et on initialise les autres panels
		utilisateurConnecte = unUtilisateur;
	}//fin checkIdentifiants
}//fin classe