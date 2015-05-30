package vues;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import classes.JLabelErreur;
import classes.Url;
import classes.Utilisateur;
import controlleur.Controlleur;
import dao.UrlDAO;

public class JPanelSupprimerUrl extends JPanel {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	protected JComboBox<String> cbbUrls;
	protected List<Url> listeUrlDeLUtilisateur;
	protected int indexDeLUrlAModifier = 0;
	protected JButton btnSupprimer;
	protected JSeparator separator;
	protected JLabel lblMessage;
	protected JLabel lblMPIntitule;
	protected JLabel lblMPAdresse;
	protected JButton btnAnnuler;
	protected JButton btnValider;
	protected JLabelErreur lblErreur;
	
	public JComboBox<String> getCbbUrls() {
		return cbbUrls;
	}

	public List<Url> getListeUrlDeLUtilisateur() {
		return listeUrlDeLUtilisateur;
	}

	public int getIndexDeLUrlAModifier() {
		return indexDeLUrlAModifier;
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	public JLabel getLblMPIntitule() {
		return lblMPIntitule;
	}

	public JLabel getLblMPAdresse() {
		return lblMPAdresse;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public JLabelErreur getLblErreur() {
		return lblErreur;
	}

	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	public JPanelSupprimerUrl(Controlleur controlleurPrincipal) {
		setLayout(null);
		
		listeUrlDeLUtilisateur = new ArrayList<Url>();
		
		JLabel lblNewLabel = new JLabel("Sélectionnez une Url :");
		lblNewLabel.setBounds(50, 30, 176, 15);
		add(lblNewLabel);
		
		cbbUrls = new JComboBox<String>();
		cbbUrls.setBounds(215, 25, 380, 24);
		add(cbbUrls);
		
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(Color.RED);
		btnSupprimer.setBounds(605, 25, 117, 25);
		btnSupprimer.addActionListener(controlleurPrincipal);
		add(btnSupprimer);
		
		separator = new JSeparator();
		separator.setBounds(230, 90, 325, 1);
		add(separator);
		
		lblMessage = new JLabel("Êtes vous sûr de vouloir supprimer le marque-page suivant ?");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(140, 130, 503, 24);
		add(lblMessage);
		
		lblMPIntitule = new JLabel("Libelle");
		lblMPIntitule.setHorizontalAlignment(SwingConstants.CENTER);
		lblMPIntitule.setBounds(210, 190, 370, 24);
		add(lblMPIntitule);
		
		lblMPAdresse = new JLabel("https://adresse.adresse/adresse");
		lblMPAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMPAdresse.setBounds(110, 230, 575, 24);
		add(lblMPAdresse);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(180, 320, 117, 25);
		btnAnnuler.addActionListener(controlleurPrincipal);
		add(btnAnnuler);
		
		btnValider = new JButton("Supprimer");
		btnValider.setBackground(new Color(255, 0, 0));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBounds(495, 320, 117, 25);
		btnValider.addActionListener(controlleurPrincipal);
		add(btnValider);
		
		lblErreur = new JLabelErreur();
		add(lblErreur);
		
		reinitialiserCombobox(controlleurPrincipal.getUtilisateurConnecte());
		
		afficherPartieValidation(false);
	}//fin constructeur
	
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
	 * 
	 * @param valeur
	 */
	public void afficherPartieValidation(boolean valeur)
	{
		//on affiche ou on cache la partie en fonction du paramètre
		separator.setVisible(valeur);
		lblMessage.setVisible(valeur);
		lblMPIntitule.setVisible(valeur);
		lblMPAdresse.setVisible(valeur);
		btnAnnuler.setVisible(valeur);
		btnValider.setVisible(valeur);
		
		//On vérouille ou dévérouille la liste déroulante en fonction du paramètre
		cbbUrls.setEnabled(!valeur);
		btnSupprimer.setEnabled(!valeur);
		
		//Si le paramètre est "false", on réinitialise la liste déroulante
		if(!valeur)
			cbbUrls.setSelectedIndex(0);
		
		//on cache le label d'erreurs
		lblErreur.setVisible(false);
	}//fin afficherPartieValidation
}
