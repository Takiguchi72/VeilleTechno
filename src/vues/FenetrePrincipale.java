package vues;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import controlleur.Controlleur;

import java.awt.Rectangle;
import java.awt.Point;

public class FenetrePrincipale extends JFrame {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private BarreDeMenu laBarreDeMenu;
	private JPanelConnexion panelConnexion;
	public JPanel contentPane;
	
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
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	
	/**
	 * Constructeur principal
	 */
	public FenetrePrincipale(Controlleur controlleurPrincipal) {
		setLocation(new Point(100, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 27, 800, 550));
		setResizable(false);
		setTitle("Programme de Veille technologique");
		
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 798, 540));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//Ajout de la barre de menu
		laBarreDeMenu = new BarreDeMenu(controlleurPrincipal);
		setJMenuBar(laBarreDeMenu);
		//Ajout de la fenetre de connexion
		panelConnexion = new JPanelConnexion(controlleurPrincipal);
		panelConnexion.setBounds(0, 28, 795, 540);
		panelConnexion.setVisible(true);
		
		contentPane.add(panelConnexion);
		
		setVisible(true);
	}//fin constructeur
}//fin classe
