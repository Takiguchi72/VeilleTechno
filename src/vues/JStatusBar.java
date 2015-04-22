package vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class JStatusBar extends JPanel {
	private JLabel lblMenuActif;
	private JLabel image;
	private JLabel lblImage;
	
	
	
	public JLabel getLblMenuActif() {
		return lblMenuActif;
	}

	public void setLblMenuActif(JLabel lblMenuActif) {
		this.lblMenuActif = lblMenuActif;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public JLabel getLblImage() {
		return lblImage;
	}

	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}


	public JStatusBar() {
		super();
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(this.getWidth(), 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//setBackground(Color.white);
		
		lblMenuActif = new JLabel(" Recherches ");
		lblMenuActif.setBounds(5, 5, 82, 15);
		lblMenuActif.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblMenuActif);
		
		JSeparator separateur1 = new JSeparator(SwingConstants.VERTICAL);
		separateur1.setBounds(15, 0, 1, 16);
		add(separateur1);
		
		//On instancie l'image à partir de l'image dans le dossier "images" en la redimensionnant à 15x15
		image = new JLabel(new ImageIcon(new ImageIcon("./images/point_rouge.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		image.setHorizontalAlignment(SwingConstants.LEFT);
		image.setBounds(0, 0, 15, 15);
		add(image);

		lblImage = new JLabel(" Déconnecté ");
		lblImage.setForeground(Color.red);
		lblImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblImage.setBounds(125, 0, 100, 15);
		add(lblImage);
		
		setVisible(true);
	}
}
