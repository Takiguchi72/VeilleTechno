package vues;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import classes.JLabelErreur;
import classes.ModeleTableAjout;
import controlleur.Controlleur;

public class JPanelAjoutUrl extends JPanel implements JPanelPersonnalise{
	
    /* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	protected JLabel lblIntitule;
	protected JLabel lblUrl;
	protected JLabel lblTags;
	protected JLabel lblSelectUnTag;
	protected JLabel lblCliquerIci;
	protected JLabel lblPourLeSuppr;
	
	protected JTextField txbUrl;
	protected JTextField txbIntitule;
	protected JTextField txbTag;
	protected JButton btnAjouter;
	protected JButton btnSupprimer;
	protected JButton btnEnregistrer;
	protected JLabel lblErreur;
	protected ModeleTableAjout leModele;
	protected JTable tableTags;
	protected JScrollPane scrollPane;
	
	
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
	
	/**
	 * Retourne l'attribut tableTags
	 * @return L'attribut tableTags [JTable]
	 */
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
		
		lblIntitule = new JLabel("Intitulé :");
		lblIntitule.setBounds(95, 22, 70, 15);
		add(lblIntitule);
		
		txbIntitule = new JTextField();
		txbIntitule.setBounds(200, 20, 380, 19);
		txbIntitule.setName("Intitulé");
		add(txbIntitule);
		
		lblUrl = new JLabel("Adresse URL :");
		lblUrl.setBounds(95, 52, 110, 15);
		add(lblUrl);
		
		txbUrl = new JTextField();
		txbUrl.setBounds(200, 50, 380, 19);
		txbUrl.setName("Adresse URL");
		add(txbUrl);
		
		lblTags = new JLabel("Tag :");
		lblTags.setOpaque(true);
		lblTags.setBounds(80, 102, 40, 15);
		add(lblTags);
		
		txbTag = new JTextField();
		txbTag.setBounds(120, 100, 180, 19);
		txbTag.setName("Tag");
		txbTag.addKeyListener(controlleurPrincipal);
		add(txbTag);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(190, 130, 100, 19);
		btnAjouter.addActionListener(controlleurPrincipal);
		add(btnAjouter);
		
		lblSelectUnTag = new JLabel("Sélectionnez un tag,");
		lblSelectUnTag.setBounds(60, 200, 175, 15);
		add(lblSelectUnTag);
		
		lblCliquerIci = new JLabel("puis cliquez ici :");
		lblCliquerIci.setBounds(60, 230, 118, 15);
		add(lblCliquerIci);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(180, 228, 110, 19);
		btnSupprimer.addActionListener(controlleurPrincipal);
		add(btnSupprimer);
		
		lblPourLeSuppr = new JLabel("pour le supprimer de la liste");
		lblPourLeSuppr.setBounds(60, 260, 205, 15);
		add(lblPourLeSuppr);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(470, 340, 125, 25);
		btnEnregistrer.addActionListener(controlleurPrincipal);
		add(btnEnregistrer);
		
		initialiserLeModele();
		
		//Définition du tableau qui contiendra les tags ajoutés par l'utilisateur
		tableTags = new JTable(leModele);
		
		scrollPane = new JScrollPane(tableTags, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(340, 90, 370, 230);
		add(scrollPane);
		
		lblErreur = new JLabelErreur();
		add(lblErreur);
		
		txbIntitule.requestFocus();
	}//fin constructeur

	protected void initialiserLeModele()
	{
		leModele = new ModeleTableAjout();
	}
	
	/**
	 * Réinitialise tous les composants du panel
	 */
	@Override
	public void reinitialiser() {
		txbIntitule.setText(null);
		txbUrl.setText(null);
		txbTag.setText(null);
		leModele.removeAll();
		txbIntitule.requestFocus();
	}//fin reinitialiser()
}//fin classe