package controlleur;

import static util.FonctionsString.md5;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import vues.FenetrePrincipale;
import classes.Tag;
import classes.Url;
import classes.Utilisateur;
import dao.DAO;
import dao.TagDAO;
import dao.UrlDAO;
import dao.UtilisateurDAO;

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
		listeTag = new TagDAO();
		lesUtilisateurs = new UtilisateurDAO();
		utilisateurConnecte = new Utilisateur();
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * Méthodes liées à la gestion d'évènements classiques *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/**
	 * Gestion des évènements du logiciel
	 * @param e - L'évenement détecté [ActionEvent]
	 */
	public void actionPerformed(ActionEvent e)
	{
		//--------------------------------//
		// Bouton QUITTER - Barre de Menu //
		//--------------------------------//
		if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitQuitter())
		{
			System.exit(0);
		}//fin if
		//----------------------------------//
		// Bouton CONNEXION - Barre de Menu //
		//----------------------------------//
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitConnexion())
		{
			//On affiche le pannel de connexion
			afficherOuCacherPanelConnexion(true);
		}//fin else if
		//----------------------------------//
		// Bouton CONSULTER - Barre de Menu //
		//----------------------------------//
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitConsulter())
		{
			//On cache le pannel de connexion et on affiche le pannel pour consulter les marques-page
			afficherOuCacherPanelConnexion(false);
		}//fin else if
		//---------------------------------------// 
		// Bouton SE DÉCONNECTER - Barre de Menu //
		//---------------------------------------//
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitEPDeconnexion())
		{
			//On modifie la barre de menu
			laFenetre.getLaBarreDeMenu().etatUtilisateurConnecte(false);
			
			//On réinitialise les modules liées à l'espace personnel
			laFenetre.reinitialiserEspacePersonnel();

			//On réinitialise l'utilisateur
			utilisateurConnecte = new Utilisateur();
		}//fin else if
		//-------------------------------------------------//
		// Bouton AJOUTER DES MARQUES-PAGE - Barre de Menu //
		//-------------------------------------------------//
		else if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitEPAjouter())
		{
			//On cache le panel de recherches
			laFenetre.getPanelRecherche().setVisible(false);
			
			//On affiche le panel d'ajout
			laFenetre.getPanelAjout().setVisible(true);
		}//fin else if
		//------------------------------------//
		// Bouton CONNEXION - Panel CONNEXION //
		//------------------------------------//
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
				ErrorManagement.clearAndFocusField(laFenetre.getPanelConnexion().getPswdField());
				ErrorManagement.clearAndFocusField(laFenetre.getPanelConnexion().getTxbIdentifiant());
			}//fin catch
		}//fin else if
		//--------------------------------------//
		// Bouton RECHERCHER - Panel RECHERCHER //
		//--------------------------------------//
		else if(e.getSource() == laFenetre.getPanelRecherche().getBtnRechercher())
		{
			laFenetre.getPanelRecherche().getLblErreur().setVisible(false);
			laFenetre.getPanelRecherche().getLblErreur().setText("");
			try {
				//On vérifie que le champ de recherche n'est pas vide
				ErrorManagement.checkEmptyField(laFenetre.getPanelRecherche().getTxbRecherche());
				
				//On met à jour la liste d'Urls en fonction de la recherche				
				laFenetre.getPanelRecherche().getLeModele().updateUrlsEnFonctionDeLaRecherche(listeUrl.selectCorrespondantA(laFenetre.getPanelRecherche().getTxbRecherche().getText()));
				laFenetre.getPanelRecherche().getScrollPane().setVisible(true);
				
				//On replace le focus dans la barre de recherche, et on selectionne le texte
				laFenetre.getPanelRecherche().getTxbRecherche().requestFocus();
				laFenetre.getPanelRecherche().getTxbRecherche().selectAll();
			} catch (Exception ex) {
				//On cache le scrollPane
				laFenetre.getPanelRecherche().getScrollPane().setVisible(false);
				//On affiche l'erreur dans le label d'erreurs
				ErrorManagement.showError(laFenetre.getPanelRecherche().getLblErreur(), ex.getMessage(), 1);
			}//fin catch
		}//fin else if
		//----------------------------------//
		// Bouton AJOUTER - Panel AJOUT URL //
		//----------------------------------//
		else if(e.getSource() == laFenetre.getPanelAjout().getBtnAjouter())
		{
			//On ajoute le tag dans le tableau
			ajouterUnTagAjoutUrl();
		}//fin else if
		//------------------------------------//
		// Bouton SUPPRIMER - Panel AJOUT URL //
		//------------------------------------//
		else if(e.getSource() == laFenetre.getPanelAjout().getBtnSupprimer())
		{
			//On supprime du tableau les Tags sélectionnés par l'utilisateur
			supprimerTagsSelectionnesAjoutUrl();
		}//fin else if
		//--------------------------------------//
		// Bouton ENREGISTRER - Panel AJOUT URL //
		//--------------------------------------//
		else if(e.getSource() == laFenetre.getPanelAjout().getBtnEnregistrer())
		{
			enregisterNouvelleUrl();
		}//fin else if
	}//fin actionPerformed
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * Méthodes liées à la gestion d'évènements liés à la souris *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/**
	 * Gestion des clics du logiciel
	 * @param e - Le clic détecté [MouseEvent]
	 */
	public void mouseClicked(MouseEvent e) {
		//-----------------------------------------//
		// Une Ligne du Tableau - Panel RECHERCHER //
		//-----------------------------------------//
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

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }
	
	/* ~~~~~~~~~~~~~~~~~~~ *
	 * Méthodes CLASSIQUES *
	 * ~~~~~~~~~~~~~~~~~~~ */
	/**
	 * Permet de définir la fenetre principale du controlleur principal
	 * @param laFenetre - La fenetre principal à définir [FenetrePrincipale]
	 */
	public void ajouterFenetrePrincipale(FenetrePrincipale laFenetre)
	{
		this.laFenetre = laFenetre;
	}//fin ajouterFenetrePrincipale	
	
	/**
	 * Affiche ou cache le pannel de connexion en fonction du paramètre
	 * @param true -> Affiche ce pannel ; false -> Cache ce pannel
	 */
	private void afficherOuCacherPanelConnexion(boolean valeur)
	{
		//On modifie la barre de menu en conséquence
		laFenetre.getLaBarreDeMenu().etatConnexionEnCours(valeur);
		//On affiche ou on cache les modules en fonction de "valeur"
		laFenetre.getPanelConnexion().setVisible(valeur);
		laFenetre.getPanelRecherche().setVisible(!valeur);
		//On cache les labels d'erreurs des deux modules
		laFenetre.getPanelRecherche().getLblErreur().setVisible(false);
		laFenetre.getPanelConnexion().getLblErreur().setVisible(false);
		
		//Si on cache le pannel de connexion, on va réinitialiser ses attributs
		if(valeur == false)
		{
			laFenetre.getPanelConnexion().reinitialiser();
			laFenetre.getPanelRecherche().getTxbRecherche().requestFocus();
		}//fin if
		else
		{
			laFenetre.getPanelConnexion().getTxbIdentifiant().requestFocus();
		}//fin else
	}//fin afficherOuCacherPanelConnexion
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * Méthodes liées au JPanelConnexion *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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
	
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
	 * Méthodes liées au JPanelAjouterUrl *
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/**
	 * Ajoute un tag dans le tableau de Tags du panel d'ajout d'Url
	 */
	private void ajouterUnTagAjoutUrl()
	{
		try {
			//On vérifie que le champ Tag n'est pas vide
			ErrorManagement.checkEmptyField(laFenetre.getPanelAjout().getTxbTag());
			
			//On ajoute le tag au tableau du panel ajouter
			laFenetre.getPanelAjout().getLeModele().ajouterTag(new Tag(0,laFenetre.getPanelAjout().getTxbTag().getText()));
			
			//On vide puis on place le focus à nouveau dans la zone de saisies pour ajouter un tag
			ErrorManagement.clearAndFocusField(laFenetre.getPanelAjout().getTxbTag());
		} catch (Exception ex) {
			//On affiche l'erreur dans le label d'erreurs
			ErrorManagement.showError(laFenetre.getPanelAjout().getLblErreur(), ex.getMessage(), 1);
		}//fin catch
	}//fin ajouterUnTagAjoutUrl
	
	/**
	 * Supprime les tags qui sont sélectionnés dans le tableau de Tags du panel d'ajout d'Url. Si il n'y a aucun tag de sélectionné, il sera affiché un avertissement à l'utilisateur
	 */
	private void supprimerTagsSelectionnesAjoutUrl()
	{
		//On récupère les index des tags qui sont sélectionnés
		int[] selection = laFenetre.getPanelAjout().getTableTags().getSelectedRows();
		
		//Si la selection est supérieure à 0 on supprime les tags du tableau
		if(selection.length > 0)
		{
			for(int i = selection.length - 1; i >= 0; i--)
			{
				laFenetre.getPanelAjout().getLeModele().supprimerTag(selection[i]);
			}//fin for
		}//fin if
		//Sinon on affiche une erreur
		else
		{
			ErrorManagement.showError(laFenetre.getPanelAjout().getLblErreur(), "Veuillez sélectionner au moins un tag !", 2);
		}//fin else
	}//fin supprimerTagsSelectionnes
	
	/**
	 * Effectue les contrôles de saisies, puis enregistre la nouvelle Url dans la base de données, en effectuant les associations entre l'Url et les tags
	 */
	private void enregisterNouvelleUrl()
	{
		try {
			//On vérifie que les zones de saisies ne sont pas vides
			ErrorManagement.checkEmptyField(laFenetre.getPanelAjout().getTxbIntitule());
			ErrorManagement.checkEmptyField(laFenetre.getPanelAjout().getTxbUrl());
			
			//On va créer un objet Url correspondant à celui qui sera créé
			Url urlACreer = new Url(laFenetre.getPanelAjout().getTxbIntitule().getText(), laFenetre.getPanelAjout().getTxbUrl().getText(), utilisateurConnecte);
			
			// INSERTION DE NOUVEAUX TAGS DANS LA BDD
			//On créer une liste de tags vide pour l'affecter par la suite à l'url qu'on va créer
			List<Tag> listeTagsAssocies = new ArrayList<Tag>();
			
			//Pour chaque tag dans la liste, on va vérifier qu'il existe dans la bdd
			//Si ce n'est pas le cas, on va le créer
			for(int i = 0 ; i < laFenetre.getPanelAjout().getLeModele().getListeTags().size() ; i++)
			{
				//On créer un tag temporaire pour vérifier s'il faut le créer en bdd
				Tag tagTemp = listeTag.read(laFenetre.getPanelAjout().getLeModele().getListeTags().get(i));
				
				//S'il n'existe pas, on va l'insérer dans la base
				if(tagTemp.getId() == 0)
				{
					tagTemp = listeTag.create(laFenetre.getPanelAjout().getLeModele().getListeTags().get(i));															// À VÉRIFIER !!!!!!!
				}//fin if
				
				listeTagsAssocies.add(tagTemp);
			}//fin for
			
			//On affecte la liste des tags associés à l'Url à créer
			urlACreer.setListeTagsAssocies(listeTagsAssocies);
			
			//On enregistre l'url dans la bdd
			listeUrl.create(urlACreer);
			
			//On réinitialise le panel
			laFenetre.getPanelAjout().reinitialiser();
			
			//Puis on affiche un message pour dire que l'insertion a été effectuée
			ErrorManagement.showError(laFenetre.getPanelAjout().getLblErreur(), "L'url a bien été enregistrée !", 0);
		} catch (Exception ex) {
			ErrorManagement.showError(laFenetre.getPanelAjout().getLblErreur(), ex.getMessage(), 1);
		}
	}//fin enregistrerNouvelleUrl
}//fin classe