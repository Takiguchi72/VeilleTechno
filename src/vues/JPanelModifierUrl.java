package vues;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import classes.ModeleTableModifier;
import classes.Tag;
import classes.Url;
import classes.Utilisateur;
import controlleur.Controlleur;
import dao.UrlDAO;

public class JPanelModifierUrl extends JPanelAjoutUrl {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	protected JComboBox<String> cbbUrls;
	protected List<Url> listeUrlDeLUtilisateur;
	protected int indexDeLUrlAModifier = 0;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'attribut cbbUrls
	 * @return L'attribut cbbUrls [JComboBox<String>]
	 */
	public JComboBox<String> getCbbUrls() {
		return cbbUrls;
	}

	/**
	 * Retourne l'attribut listeUrlDeLUtilisateur
	 * @return L'attribut listeUrlDeLUtilisateur [List<Url>]
	 */
	public List<Url> getListeUrlDeLUtilisateur() {
		return listeUrlDeLUtilisateur;
	}

	public void setListeUrlDeLUtilisateur(List<Url> listeUrlDeLUtilisateur) {
		this.listeUrlDeLUtilisateur = listeUrlDeLUtilisateur;
	}

	public int getIndexDeLUrlAModifier() {
		return indexDeLUrlAModifier;
	}

	public void setIndexDeLUrlAModifier(int indexDeLUrlAModifier) {
		this.indexDeLUrlAModifier = indexDeLUrlAModifier;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Initialise le module à partir des attributs du controlleur principal
	 * @param Le controlleur principal qui permettra d'initialiser les composants du module
	 */
	public JPanelModifierUrl(Controlleur controlleurPrincipal)
	{
		super(controlleurPrincipal);
		
		listeUrlDeLUtilisateur = controlleurPrincipal.getListeUrl().selectDe(controlleurPrincipal.getUtilisateurConnecte());
		
		JLabel lblSelectionUrl = new JLabel("Selectionnez une Url :");
		lblSelectionUrl.setBounds(70, 20, 176, 15);
		add(lblSelectionUrl);
		
		cbbUrls = new JComboBox<String>();
		cbbUrls.setBounds(235, 15, 380, 24);
		cbbUrls.addActionListener(controlleurPrincipal);
		add(cbbUrls);
		
		initialiserLeModele();
		
		//Définition du tableau qui contiendra les tags ajoutés par l'utilisateur
		tableTags.setModel(leModele);
		
		verouillerPartieModifier(true);
		
		replacerLaPartieParent();
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	/**
	 * Réinitialise les éléments de la liste déroulante
	 * @param L'utilisateur auquel on récupèrera les marques-page [Utilisateur]
	 */
	public void reinitialiserCombobox(Utilisateur createur)
	{
		//On supprime tous les éléments de la liste déroulante
		cbbUrls.removeAllItems();
		//On rafraichit la liste des Urls de l'utilisateur
		listeUrlDeLUtilisateur = new UrlDAO().selectDe(createur);
		//On ajoute un élément vide
		cbbUrls.addItem("Selectionnez le marque page à modifier");
		//On ajoute tous les Urls à la liste déroulante
		for(Url u : listeUrlDeLUtilisateur)
		{
			cbbUrls.addItem(u.getIntitule());
		}//fin foreach
	}//fin reinitialiserCombobox()
	
	/**
	 * Vérouille la partie "Modification d'un marque-page" du module
	 * @param true -> Vérouille les zones de saisies et les boutons
	 * @param false -> Dévérouille les zones de saisies et les boutons
	 */
	public void verouillerPartieModifier(boolean value)
	{
		txbIntitule.setEnabled(!value);
		txbUrl.setEnabled(!value);
		txbTag.setEnabled(!value);
		btnAjouter.setEnabled(!value);
		btnSupprimer.setEnabled(!value);
		btnEnregistrer.setEnabled(!value);
		
		txbIntitule.setText(null);
		txbUrl.setText(null);
		txbTag.setText(null);
		leModele.removeAll();
		lblErreur.setVisible(false);
	}//verouillerPartieModifier
	
	@Override
	protected void initialiserLeModele()
	{
		leModele = new ModeleTableModifier();
	}
	
	/**
	 * Initialise les zones de saisies à partir du marque page sélectionné dans la liste déroulante
	 * @param L'index du marque page dans la liste [int]
	 */
	public void initialiserPartieModifier(int index)
	{
		txbIntitule.setText(listeUrlDeLUtilisateur.get(index).getIntitule());
		txbUrl.setText(listeUrlDeLUtilisateur.get(index).getAdresse());
		
		//On vide la liste d'url
		leModele.removeAll();
		leModele.setListeTags(new ArrayList<Tag>(listeUrlDeLUtilisateur.get(indexDeLUrlAModifier).getListeTagsAssocies()));
		leModele.fireTableDataChanged();
//		System.out.println("Nombre de tags du modèle : " + leModele.getListeTags().size() + "\nNombre de tags de l'Url : " + listeUrlDeLUtilisateur.get(indexDeLUrlAModifier).getListeTagsAssocies().size());
	}//fin initialiserPartieModifier
	
	/**
	 * Réinitialise tous les composants du panel
	 */
	@Override
	public void reinitialiser() {
		txbIntitule.setText(null);
		txbUrl.setText(null);
		txbTag.setText(null);
		leModele.removeAll();
	}//fin reinitialiser()
	
	protected void replacerLaPartieParent()
	{
		replacerJLabel(lblIntitule);
		replacerJTextField(txbIntitule);
		
		replacerJLabel(lblUrl);
		replacerJTextField(txbUrl);
		
		replacerJLabel(lblTags);
		replacerJTextField(txbTag);
		replacerJButton(btnAjouter);
		
		replacerJLabel(lblSelectUnTag);
		replacerJLabel(lblCliquerIci);
		replacerJButton(btnSupprimer);
		replacerJLabel(lblPourLeSuppr);
		
		//scrollPane.setAlignmentY(scrollPane.getAlignmentY() + 30);
		scrollPane.setBounds(340, 120, 370, 230);
		//(340, 90, 370, 230);
		replacerJButton(btnEnregistrer);
	}
	
	protected void replacerJLabel(JLabel label)
	{
		label.setLocation(label.getLocation().x, label.getLocation().y + 30);
		//label.setAlignmentY(label.getAlignmentY() + 30);
	}
	
	protected void replacerJTextField(JTextField textField)
	{
		textField.setLocation(textField.getLocation().x, textField.getLocation().y + 30);
	}
	
	protected void replacerJButton(JButton button)
	{
		button.setLocation(button.getLocation().x, button.getLocation().y + 30);
		//button.setAlignmentY(button.getAlignmentY() + 30);
	}
}//fin classe