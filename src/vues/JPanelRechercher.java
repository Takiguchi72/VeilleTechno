package vues;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.JTableRechercher;
import classes.ModeleTableRechercher;
import controlleur.Controlleur;

public class JPanelRechercher extends JPanel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JTextField txbRecherche;
	private JTableRechercher tableUrls;
	private JButton btnRechercher;
	private JScrollPane scrollPane;
	private JLabel lblErreur;
	private ModeleTableRechercher leModele = new ModeleTableRechercher();
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'attribut txbRecherche du panel
	 * @return L'attribut txbRecherche [JTextField] 
	 */
	public JTextField getTxbRecherche() {
		return txbRecherche;
	}

	/**
	 * Retourne l'attribut tableUrls du panel
	 * @return L'attribut tableUrls [JTableRechercher]
	 */
	public JTable getTableUrls() {
		return tableUrls;
	}

	/**
	 * Retourne l'attribut btnRechercher du panel
	 * @return L'attribut btnRechercher [JButton]
	 */
	public JButton getBtnRechercher() {
		return btnRechercher;
	}
	
	/**
	 * Retourne l'attribut ScrollPane du panel
	 * @return L'attribut scrollPane [JScrollPane]
	 */
	public JScrollPane getScrollPane() {
			return scrollPane;
	}
	
	/**
	 * Retourne l'attribut lblErreurs du panel
	 * @return L'attribut lblErreurs [JLabel]
	 */
	public JLabel getLblErreur() {
		return lblErreur;
	}
	
	/**
	 * Retourne l'attribut leModele
	 * @return L'attribut leModele [ModeleTableRechercher]
	 */
	public ModeleTableRechercher getLeModele() {
		return leModele;
	}
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Constructeur par défault de la classe JPanelRechercher
	 * @param Le Controlleur principal [Controlleur]
	 */
	public JPanelRechercher(Controlleur controlleurPrincipal) {
		setLayout(null);
		
		//Définition du label précédant la zone de recherches
		JLabel lblRechercher = new JLabel("Rechercher :");
		lblRechercher.setBounds(35, 30, 99, 15);
		add(lblRechercher);
		
		//Définition de la zone de recherches
		txbRecherche = new JTextField();
		txbRecherche.setBounds(135, 30, 391, 19);
		txbRecherche.setName("Rechercher");
		add(txbRecherche);
		txbRecherche.setColumns(10);
		
		//Définition du bouton permettant de lancer la recherche
		btnRechercher = new JButton("Valider");
		btnRechercher.setBounds(540, 28, 90, 22);
		btnRechercher.addActionListener(controlleurPrincipal);
		add(btnRechercher);
		
		//Définition du label destiné à afficher les erreurs
		lblErreur = new JLabel("Erreur :");
		lblErreur.setFont(new Font("Dialog", Font.BOLD, 14));
		lblErreur.setForeground(Color.RED);
		lblErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreur.setBounds(30, 432, 636, 30);
		lblErreur.setVisible(false);
		add(lblErreur);
		
		//Initialisation du tableau qui contiendra le résultat de la recherche
		tableUrls = new JTableRechercher(leModele, controlleurPrincipal);
		
		//Ajout de la JTableRechercher dans un JScrollPane au cas où le nombre de ligne 
		//serait supérieur au nombre de ligne que peut afficher la table
		scrollPane = new javax.swing.JScrollPane(tableUrls, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(20, 70);
		scrollPane.setSize(747, 350);
		scrollPane.setVisible(false);
		add(scrollPane);
	}//fin JPanelRechercher
}//fin classe