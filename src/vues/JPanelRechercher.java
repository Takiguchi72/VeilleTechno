package vues;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import classes.JTableRechercher;
import controlleur.Controlleur;

public class JPanelRechercher extends JPanel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JTextField txbRecherche;
	private JTableRechercher tableUrls;
	private JButton btnRechercher;
	private JScrollPane scrollPane;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	
	public JTextField getTxbRecherche() {
		return txbRecherche;
	}

	public JTable getTableUrls() {
		return tableUrls;
	}

	public JButton getBtnRechercher() {
		return btnRechercher;
	}
	
	public JScrollPane getScrollPane() {
			return scrollPane;
	}
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */

	public JPanelRechercher(Controlleur controlleurPrincipal) {
		setLayout(null);
		
		JLabel lblRechercher = new JLabel("Rechercher :");
		lblRechercher.setBounds(35, 30, 99, 15);
		add(lblRechercher);
		
		txbRecherche = new JTextField();
		txbRecherche.setBounds(135, 30, 391, 19);
		add(txbRecherche);
		txbRecherche.setColumns(10);
		
		btnRechercher = new JButton("Valider");
		btnRechercher.setBounds(540, 28, 90, 22);
		btnRechercher.addActionListener(controlleurPrincipal);
		add(btnRechercher);
		
		//On initialise la table des Urls à partir du contenu de la table t_url 
		tableUrls = new JTableRechercher(controlleurPrincipal);
		tableUrls.setBounds(55, 90, 650, 100);
		
		scrollPane = new javax.swing.JScrollPane(tableUrls, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(20, 70);
		scrollPane.setSize(747, 385);
		add(scrollPane);
		
		//add(tableUrls);
		
	}//fin JPanelRechercher
}//fin classe