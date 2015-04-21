package classes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JLabelErreur extends JLabel {

	public JLabelErreur() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setForeground(Color.RED);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBounds(80, 435, 650, 30);
		setVisible(false);
	}
}