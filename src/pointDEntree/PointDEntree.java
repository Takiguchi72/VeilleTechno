package pointDEntree;

import vues.FenetrePrincipale;
import controlleur.Controlleur;

public class PointDEntree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Controlleur controlleurPrincipal = new Controlleur();
			FenetrePrincipale fenetrePrincipale = new FenetrePrincipale(controlleurPrincipal);
			controlleurPrincipal.ajouterFenetrePrincipale(fenetrePrincipale);
		} catch (Exception e) {
			e.printStackTrace();
		}//fin catch
	}//fin main
}//fin classe