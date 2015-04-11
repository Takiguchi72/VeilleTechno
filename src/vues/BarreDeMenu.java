package vues;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controlleur.Controlleur;

public class BarreDeMenu extends JMenuBar {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JMenu mnFichier;
	private JMenuItem mnitQuitter;
	private JMenuItem mnitConsulter;
	private JMenuItem mnitConnexion;
	private JMenu mnEspacePersonnel;
	private JMenuItem mnitEPConsulter;
	private JMenuItem mnitEPAjouter;
	private JMenuItem mnitEPModifier;
	private JMenuItem mnitEPSupprimer;
	private JMenuItem mnitEPDeconnexion;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */

	/**
	 * Accesseur de l'attribut mnitQuitter
	 * @return L'attribut mnitQuitter [JMenuItem]
	 */
	public JMenuItem getMnitQuitter()
	{
		return mnitQuitter;
	}//fin getMnitQuitter
	
	/**
	 * Accesseur de l'attribut mnuitConsulter
	 * @return L'attribut mnitConsulter [JMenuItem]
	 */
	public JMenuItem getMnitConsulter() {
		return mnitConsulter;
	}//fin getMnitConsulter

	/**
	 * Accesseur de l'attribut mnitConnexion
	 * @return L'attribut mnitConnexion [JMenuItem]
	 */
	public JMenuItem getMnitConnexion()
	{
		return mnitConnexion;
	}//fin getMnitConnexion
	
	/**
	 * Accesseur de l'attribut mnFichier
	 * @return L'attribut mnFichier [JMenuItem]
	 */
	public JMenu getMnFichier() {
		return mnFichier;
	}

	/**
	 * Accesseur de l'attribut mnEspacePersonnel
	 * @return L'attribut mnEspacePersonnel [JMenuItem]
	 */
	public JMenu getMnEspacePersonnel() {
		return mnEspacePersonnel;
	}

	/**
	 * Accesseur de l'attribut mnitEPConsulter
	 * @return L'attribut mnitEPConsulter [JMenuItem]
	 */
	public JMenuItem getMnitEPConsulter() {
		return mnitEPConsulter;
	}

	/**
	 * Accesseur de l'attribut mnitEPAjouter
	 * @return L'attribut mnitEPAjouter [JMenuItem]
	 */
	public JMenuItem getMnitEPAjouter() {
		return mnitEPAjouter;
	}

	/**
	 * Accesseur de l'attribut mnitEPModifier
	 * @return L'attribut mnitEPModifier [JMenuItem]
	 */
	public JMenuItem getMnitEPModifier() {
		return mnitEPModifier;
	}

	/**
	 * Accesseur de l'attribut mnitEPSupprimer
	 * @return L'attribut mnitEPSupprimer [JMenuItem]
	 */
	public JMenuItem getMnitEPSupprimer() {
		return mnitEPSupprimer;
	}

	/**
	 * Accesseur de l'attribut mnitEPDeconnexion
	 * @return L'attribut mnitEPDeconnexion [JMenuItem]
	 */
	public JMenuItem getMnitEPDeconnexion() {
		return mnitEPDeconnexion;
	}
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */

	/**
	 * Constructeur par défaut
	 * @param controlleurPrincipal - Le controlleur qui sera ajouté à chaque élément pour gérer les évènements [Controlleur]
	 */
	public BarreDeMenu(Controlleur controlleurPrincipal)
	{
		//Définition du menu "Fichier"
		mnFichier = new JMenu("Fichier");
		add(mnFichier);
			
			//Définition des boutons du menu "Fichier" de la barre de menu
			mnitQuitter = new JMenuItem("Quitter");
			mnitQuitter.addActionListener(controlleurPrincipal);
			mnFichier.add(mnitQuitter);
		
		//Définition du menu "Espace personnel"
		mnEspacePersonnel = new JMenu("Espace personnel");
		mnEspacePersonnel.setVisible(false);
		add(mnEspacePersonnel);

			//Définition des boutons du menu "Espace personnel" de la barre de menu
			mnitEPConsulter = new JMenuItem("Consulter mes marques-page");
			mnitEPConsulter.addActionListener(controlleurPrincipal);
			mnEspacePersonnel.add(mnitEPConsulter);
	
			mnitEPAjouter = new JMenuItem("Ajouter des marques-page");
			mnitEPAjouter.addActionListener(controlleurPrincipal);
			mnEspacePersonnel.add(mnitEPAjouter);
	
			mnitEPModifier = new JMenuItem("Modifier mes marques-page");
			mnitEPModifier.addActionListener(controlleurPrincipal);
			mnEspacePersonnel.add(mnitEPModifier);
	
			mnitEPSupprimer = new JMenuItem("Supprimer des marques-page");
			mnitEPSupprimer.addActionListener(controlleurPrincipal);
			mnEspacePersonnel.add(mnitEPSupprimer);
	
			mnitEPDeconnexion = new JMenuItem("Se déconnecter");
			mnitEPDeconnexion.addActionListener(controlleurPrincipal);
			mnEspacePersonnel.add(mnitEPDeconnexion);
		
		//Définition des boutons de la barre de menu
		mnitConsulter = new JMenuItem("Consulter");
		mnitConsulter.addActionListener(controlleurPrincipal);
		mnitConsulter.setVisible(false);
		add(mnitConsulter);
		
		mnitConnexion = new JMenuItem("Connexion");
		mnitConnexion.addActionListener(controlleurPrincipal);
		mnitConnexion.setVisible(true);
		add(mnitConnexion);
	}//fin constructeur
}//fin classe