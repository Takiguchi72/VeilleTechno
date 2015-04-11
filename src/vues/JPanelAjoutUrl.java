package vues;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import classes.ModeleTableAjout;
import controlleur.Controlleur;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JPanelAjoutUrl extends JPanel{
	
    /* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JTextField txbUrl;
	private JTextField txbIntitule;
	private JTextField txbTag;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnEnregistrer;
	private JLabel lblErreur;
	private ModeleTableAjout leModele = new ModeleTableAjout();
	private JTable tableTags;
	
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	
	/**
	 * Retourne l'attribut txbUrl 
	 * @return L'attribut txbUrl [JTextField]  
	 */
	public JTextField getTxbUrl() {
		return txbUrl;
	}// fin getTxbUrl
	
	/**
	 * Retourne l'attribut txbIntitule 
	 * @return L'attribut txbIntitule [JTextField]  
	 */
	public JTextField getTxbIntitule() {
		return txbIntitule;
	}// fin getTxbIntitule
    
	/**
	 * Retourne l'attribut txbTags 
	 * @return L'attribut txbTags [JTextField]  
	 */
	public JTextField getTxbTag() {
		return txbTag;
	}// fin getTxbTags
    
	/**
	 * Retourne l'attribut btnAjouter 
	 * @return L'attribut btnAjouter [JButton]  
	 */
	public JButton getBtnAjouter() {
		return btnAjouter;
	}// fin getBtnAjouter

	/**
	 * Retourne l'attribut btnSupprimer 
	 * @return L'attribut btnSupprimer [JButton]  
	 */
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}// fin getBtnSupprimer

	/**
	 * Retourne l'attribut btnEnregistrer 
	 * @return L'attribut btnEnregister [JButton]  
	 */
	public JButton getBtnEnregistrer() {
		return btnEnregistrer;
	}// fin getBtnEnregistrer

	/**
	 * Retourne l'attribut lblErreur
	 * @return L'attribut lblErreur [JLabel]
	 */
	public JLabel getLblErreur() {
		return lblErreur;
	}// fin getlblErreur 
	
	/**
	 * Retourne l'attribut leModele
	 * @return L'attribut leModele [ModeleTableAjout]
	 */
	public ModeleTableAjout getLeModele() {
		return leModele;
	}

	public JTable getTableTags() {
		return tableTags;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Instancie un objet de la classe JPanelAjoutUrl
	 */
	public JPanelAjoutUrl(Controlleur controlleurPrincipal) 
	{
		setLayout(null);
		
		JLabel lblIntitule = new JLabel("Intitulé :");
		lblIntitule.setBounds(45, 62, 70, 15);
		add(lblIntitule);
		
		txbIntitule = new JTextField();
		txbIntitule.setBounds(150, 60, 380, 19);
		add(txbIntitule);
		txbIntitule.setColumns(10);
		
		JLabel lblUrl = new JLabel("Adresse URL :");
		lblUrl.setBounds(45, 92, 110, 15);
		add(lblUrl);
		
		txbUrl = new JTextField();
		txbUrl.setBounds(150, 90, 380, 19);
		add(txbUrl);
		txbUrl.setColumns(10);
		
		JLabel lblTags = new JLabel("Tag :");
		lblTags.setOpaque(true);
		lblTags.setBounds(80, 162, 40, 15);
		add(lblTags);
		
		txbTag = new JTextField();
		txbTag.setBounds(120, 160, 181, 19);
		add(txbTag);
		txbTag.setColumns(10);
	
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(190, 190, 100, 19);
		btnAjouter.addActionListener(controlleurPrincipal);
		add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(180, 288, 110, 19);
		btnSupprimer.addActionListener(controlleurPrincipal);
		
		JLabel lblSelectUnTag = new JLabel("Sélectionnez un tag,");
		lblSelectUnTag.setBounds(60, 260, 175, 15);
		add(lblSelectUnTag);
		
		JLabel lblCliquerIci = new JLabel("puis cliquez ici :");
		lblCliquerIci.setBounds(60, 290, 118, 15);
		add(lblCliquerIci);
		add(btnSupprimer);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(470, 400, 125, 25);
		btnEnregistrer.addActionListener(controlleurPrincipal);
		add(btnEnregistrer);
		
		lblErreur = new JLabel("Erreur");
		lblErreur.setFont(new Font("Dialog", Font.BOLD, 14));
		lblErreur.setForeground(Color.RED);
		lblErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblErreur.setBounds(79, 447, 636, 30);
		lblErreur.setVisible(false);
		add(lblErreur);
		
		tableTags = new JTable(leModele);
		
		JScrollPane scrollPane = new JScrollPane(tableTags, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(340, 150, 370, 230);
		add(scrollPane);
		
		JLabel lblPourLeSuppr = new JLabel("pour le supprimer de la liste");
		lblPourLeSuppr.setBounds(60, 320, 205, 15);
		add(lblPourLeSuppr);
		
	}
}
