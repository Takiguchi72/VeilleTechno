package vues;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controlleur.Controlleur;
import javax.swing.JList;

public class JPanelRechercher extends JPanel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JTextField txbRecherche;
	private JTable tableUrls;
	private JButton btnRechercher;
	
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
		tableUrls = new JTable();
		tableUrls.setBounds(53, 173, 626, 236);
		add(tableUrls);
	}//fin JPanelRechercher
	
	private void initTableUrls(Controlleur controlleurPrincipal)
	{
		
	}//fin initTableUrls
}//fin classe