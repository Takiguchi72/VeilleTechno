package controlleur;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErrorManagement {
	/**
	 * It will adapt the error color in terms of the error level then it will show it
	 * @param The error label which need to be modified [JLabel]
	 * @param The error message to show [String]
	 * @param The error level ; 0:Error (red); 1:Warning (orange) ; Else:Error
	 */
	public static void showError(JLabel label, String message, int errorLevel)
	{
		//In terms of the error level, the error color will be change
		switch (errorLevel)
		{
		case 1: label.setForeground(Color.RED);
			label.setText("<html>Erreur : " + message + "</html>");
			break;
		case 2:	label.setForeground(new Color(230, 168, 17));	//couleur orange-foncé
			label.setText("<html>Avertissement : " + message + "</html>");
			break;
		default : label.setForeground(Color.RED);
			label.setText("<html>Erreur : " + message + "</html>");
		break;
		}//fin switch()
		label.setVisible(true);
	}//fin afficherErreur()

	/**
	 * It will clear and hide the error label
	 * @param The error label to clear [JLabel]
	 */
	public static void hideErrorField(JLabel label)
	{
		label.setText(null);
		label.setVisible(false);
	}//end hideErrorField
	
	/**
	 * It will clear and request focus for the field in parameters
	 * @param The field to clear and request focus [JTextField]
	 */
	public static void clearAndFocusField(JTextField field)
	{
		field.setText("");
		field.requestFocus();
	}//end clearAndFocusField()
	
	/**
	 * Throw a new exception if the field in parameters is empty
	 * @param Le test field to control [JTextField]
	 * @throws Throw a new exception if the field is empty [Exception]
	 */
	public static void checkEmptyField(JTextField field) throws Exception
	{
		//si le champ est null, on lève une exception
    	if(field.getText().isEmpty())
        {
    		field.requestFocus();
    		throw new Exception("Svp, Remplissez le champ \"" + field.getName() + "\" : <br />Ce champ est vide !",new Throwable("empty"));
        }//fin if
	}//end checkEmptyField()
}//end Class
