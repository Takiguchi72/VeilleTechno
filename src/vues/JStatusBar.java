package vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class JStatusBar extends JPanel {
	public static String[] modules = {"Recherches", "Connexion", "Ajout", "Modification", "Suppression", "Recherches personnelles"};
	private JLabel lblModuleActif;
	private JLabel image;
	private JLabel lblImage;
	
	public JLabel getLblModuleActif() {
		return lblModuleActif;
	}

//	public void setLblModuleActif(String string) {
//		this.lblModuleActif.setText();
//	}

	public JLabel getImage() {
		return image;
	}

	public JLabel getLblImage() {
		return lblImage;
	}


	public JStatusBar() {
		super();
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(this.getWidth(), 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//setBackground(Color.white);
		
		lblModuleActif = new JLabel(" Recherches ");
		lblModuleActif.setBounds(5, 5, 82, 15);
		lblModuleActif.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblModuleActif);
		
		JSeparator separateur1 = new JSeparator(SwingConstants.VERTICAL);
		separateur1.setBounds(15, 0, 1, 16);
		add(separateur1);
		
		//On instancie l'image à partir de l'image dans le dossier "images" en la redimensionnant à 15x15
		image = new JLabel(new ImageIcon(new ImageIcon("./images/point_rouge.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		image.setHorizontalAlignment(SwingConstants.RIGHT);
		image.setBounds(0, 0, 15, 15);
		add(image);

		lblImage = new JLabel(" Déconnecté ");
		lblImage.setForeground(Color.red);
		lblImage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImage.setBounds(125, 0, 100, 15);
		add(lblImage);
		
		setVisible(true);
	}//fin constructeur
	
	/**
	 * Change le status "Connecté" ou "Déconnecté" et recharge la bonne image
	 * @param value
	 */
	public void setConnecte(boolean value)
	{
		if(value == true)
		{
			image.setIcon(new ImageIcon(new ImageIcon("./images/point_vert.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
			lblImage.setText(" Connecté ");
			lblImage.setForeground(new Color(0,168,6));
		}//fin if
		else
		{
			image.setIcon(new ImageIcon(new ImageIcon("./images/point_rouge.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
			lblImage.setText(" Déconnecté ");
			lblImage.setForeground(Color.red);
//			setLblModuleActif("Recherches");
		}//fin else
	}//fin setConnecte
}//fin classe