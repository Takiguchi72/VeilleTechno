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
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	
	/**
	 * Constructeur par défaut
	 * @param controlleurPrincipal - Le controlleur qui sera ajouté à chaque élément pour gérer les évènements [Controlleur]
	 */
	public BarreDeMenu(Controlleur controlleurPrincipal)
	{
		mnFichier = new JMenu("Fichier");
		add(mnFichier);
		
		mnitQuitter = new JMenuItem("Quitter");
		mnitQuitter.addActionListener(controlleurPrincipal);
		mnFichier.add(mnitQuitter);
		
		mnitConsulter = new JMenuItem("Consulter");
		mnitConsulter.addActionListener(controlleurPrincipal);
		mnitConsulter.setVisible(false);
		mnFichier.add(mnitConsulter);
		
		mnitConnexion = new JMenuItem("Deconnexion");
		mnitConnexion.addActionListener(controlleurPrincipal);
		mnitConnexion.setVisible(false);
		add(mnitConnexion);
	}//fin constructeur
}//fin classe