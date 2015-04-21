package vues;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.JLabelErreur;
import controlleur.Controlleur;

import java.awt.Color;

public class JPanelConnexion extends JPanel implements JPanelPersonnalise{
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private JLabel lblIdentifiant;
	private JTextField txbIdentifiant;
	private JLabel lblMotDePasse;
	private JPasswordField pswdField;
	private JButton btnConnexion;
	private JLabel lblErreur;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	
	/**
	 * Accesseur de l'attribut txbIdentifiant
	 * @return L'attribut txbIdentifiant [JTextField]
	 */
	public JTextField getTxbIdentifiant()
	{
		return txbIdentifiant;
	}//fin getTxbIdentifiant
	
	/**
	 * Accesseur de l'attribut pswdField
	 * @return L'attribut pswdField [JPasswordField]
	 */
	public JPasswordField getPswdField()
	{
		return pswdField;
	}//fin getPswdField
	
	/**
	 * Accesseur de l'attribut btnConnexion
	 * @return L'attribut btnConnexion [JButton]
	 */
	public JButton getBtnConnexion()
	{
		return btnConnexion;
	}//fin getBtnConnexion
	
	/**
	 * Accesseur de l'attribut lblErreur
	 * @return L'attribut lblErreur [JLabel]
	 */
	public JLabel getLblErreur()
	{
		return lblErreur;
	}//fin getLblErreur
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	
	/**
	 * Constructeur par défaut
	 * @param controlleurPrincipal - Le controlleur qui sera ajouté à chaque élément pour gérer les évènements [Controlleur]
	 */
	public JPanelConnexion(Controlleur controlleurPrincipal) {
		setLayout(null);
		
		lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFocusable(false);
		lblIdentifiant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentifiant.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIdentifiant.setBounds(220, 131, 130, 22);
		add(lblIdentifiant);
		
		txbIdentifiant = new JTextField();
		txbIdentifiant.setBounds(368, 133, 244, 22);
		txbIdentifiant.setName("Identifiant");
		add(txbIdentifiant);
		
		lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFocusable(false);
		lblMotDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotDePasse.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMotDePasse.setBounds(178, 182, 172, 22);
		add(lblMotDePasse);
		
		pswdField = new JPasswordField();
		pswdField.setBounds(368, 184, 244, 22);
		pswdField.setName("Mot de passe");
		add(pswdField);
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(470, 250, 110, 22);
		btnConnexion.addActionListener(controlleurPrincipal);
		add(btnConnexion);
		
		lblErreur = new JLabelErreur();
		add(lblErreur);
	}//fin constructeur
	
	@Override
	public void reinitialiser()
	{
		txbIdentifiant.setText(null);
		pswdField.setText(null);
		lblErreur.setVisible(false);
	}
}//fin classe
