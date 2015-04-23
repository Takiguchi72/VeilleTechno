package vues;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import classes.ModeleTableAjout;
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
	protected int IndexDeLUrlAModifier = 0;
	
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
		return IndexDeLUrlAModifier;
	}

	public void setIndexDeLUrlAModifier(int indexDeLUrlAModifier) {
		IndexDeLUrlAModifier = indexDeLUrlAModifier;
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
		leModele = new ModeleTableAjout();
		
		txbUrl.setBounds(150, 90, 480, 19);
		txbIntitule.setBounds(150, 60, 480, 19);
		
		listeUrlDeLUtilisateur = controlleurPrincipal.getListeUrl().selectDe(controlleurPrincipal.getUtilisateurConnecte());
		
		JLabel lblSelectionUrl = new JLabel("Selectionnez une Url :");
		lblSelectionUrl.setBounds(70, 30, 176, 15);
		add(lblSelectionUrl);
		
		cbbUrls = new JComboBox<String>();
		cbbUrls.setBounds(250, 25, 345, 24);
		cbbUrls.addActionListener(controlleurPrincipal);
		add(cbbUrls);
		
		verouillerPartieModifier(true);
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
	
	/**
	 * Initialise les zones de saisies à partir du marque page sélectionné dans la liste déroulante
	 * @param L'index du marque page dans la liste [int]
	 */
	public void initialiserPartieModifier(int index)
	{
		super.getTxbIntitule().setText(listeUrlDeLUtilisateur.get(index).getIntitule());
		super.getTxbUrl().setText(listeUrlDeLUtilisateur.get(index).getAdresse());
	}//fin initialiserPartieModifier
}//fin classe
