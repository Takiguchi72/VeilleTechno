package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlleur.Controlleur;

public class FenetrePrincipale extends JFrame {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private BarreDeMenu 		laBarreDeMenu;
	private JPanelConnexion 	panelConnexion;
	private JPanelRechercher 	panelRecherche;
	private JPanelAjoutUrl 		panelAjout;
	private JPanelModifierUrl 	panelModifier;
	private JStatusBar			statusBar;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Accesseur de laBarreDeMenu
	 * @return L'attribut laBarreDeMenu [BarreDeMenu]
	 */
	public BarreDeMenu getLaBarreDeMenu()
	{
		return laBarreDeMenu;
	}//fin getLaBarreDeMenu
	
	/**
	 * Accesseur de panelConnexion
	 * @return L'attribut panelConnexion [BarreDeMenu]
	 */
	public JPanelConnexion getPanelConnexion()
	{
		return panelConnexion;
	}//fin getpanelConnexion
	
	/**
	 * Accesseur de panelRecherche
	 * @return L'attribut panelRecherche [JPanelRecherche]
	 */
	public JPanelRechercher getPanelRecherche() {
		return panelRecherche;
	}
	
	/**
	 * Accesseur de panelAjout
	 * @return L'attribut panelAjout [JPanelAjoutUrl]
	 */
	public JPanelAjoutUrl getPanelAjout() {
		return panelAjout;
	}
	
	/**
	 * Accesseur de panelModifier
	 * @return L'attribut panelModifier [JPanelModifierUrl]
	 */
	public JPanelModifierUrl getPanelModifier() {
		return panelModifier;
	}

	public JStatusBar getStatusBar() {
		return statusBar;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Constructeur principal
	 */
	public FenetrePrincipale(Controlleur controlleurPrincipal) {
		setSize(new Dimension(800, 550));
		setLocation(new Point(100, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 27, 800, 550));
		setResizable(false);
		setTitle("Programme de Veille technologique");
		
		//Ajout de la barre de menu
		laBarreDeMenu = new BarreDeMenu(controlleurPrincipal);
		setJMenuBar(laBarreDeMenu);
		
		//Ajout de la fenetre de connexion
		panelConnexion = new JPanelConnexion(controlleurPrincipal);
		panelConnexion.setBounds(0, 0, 795, 461);
		panelConnexion.setVisible(false);
		
		add(panelConnexion);
		//Ajout de la fenetre de recherche
		panelRecherche = new JPanelRechercher(controlleurPrincipal);
		panelRecherche.setBounds(0, 0, 795, 461);
		panelRecherche.setVisible(true);
		
		add(panelRecherche);
		//Ajout de la fenetre d'ajout de marque page
		panelAjout = new JPanelAjoutUrl(controlleurPrincipal);
		panelAjout.setBounds(0, 0, 795, 461);
		panelAjout.setVisible(false);
		
		add(panelAjout);
		//Ajout de la fenetre de modification de marque page
		panelModifier = new JPanelModifierUrl(controlleurPrincipal);
		panelModifier.setBounds(5,5,788,456);
		panelModifier.setVisible(false);
		getContentPane().add(panelModifier);
		
		statusBar = new JStatusBar();
		statusBar.setVisible(true);
		add(statusBar, BorderLayout.SOUTH);
			
		setVisible(true);
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	/**
	 * Réinitialise les modules de l'espace personnel, soit panelAjout, panelModifier et panelSupprimer
	 */
	public void reinitialiserEspacePersonnel()
	{
		panelAjout.reinitialiser();
		panelModifier.reinitialiser();
		
		afficherOuCacherEspacePersonnel(false);
	}//fin reinitialiserEspacePersonnel
	
	/**
	 * Affiche ou cache les modules de l'espace personnel
	 * @param true -> Affiche les menu des modules de l'espace personnel, cache le module e recherche
	 */
	public void afficherOuCacherEspacePersonnel(boolean value)
	{
		panelAjout.setVisible(false);
		panelModifier.setVisible(false);
		
		panelRecherche.setVisible(!value);
		
		laBarreDeMenu.getMnitConsulter().setVisible(value);
	}//fin afficherOuCacherEspacePersonnel
}//fin classe