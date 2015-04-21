package vues;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import controlleur.Controlleur;

public class JPanelModifierUrl extends JPanelAjoutUrl {
	
	public JPanelModifierUrl(Controlleur controlleurPrincipal)
	{
		super(controlleurPrincipal);
		
		JLabel lblSelectionUrl = new JLabel("Selectionnez une Url :");
		lblSelectionUrl.setBounds(70, 30, 176, 15);
		add(lblSelectionUrl);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(250, 25, 250, 24);
		add(comboBox);
		
	}
}
