package controlleur;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import classes.Url;
import dao.DAO;
import dao.UrlDAO;
import vues.FenetrePrincipale;

public class Controlleur implements ActionListener, MouseListener {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private FenetrePrincipale laFenetre;
	private DAO<Url> listeUrl;
	private boolean isAlreadyOneClick;
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Retourne l'attribut listeUrl du Controlleur
	 * @return L'attribut listeUrl [DAO<Url>]
	 */
	public DAO<Url> getListeUrl() {
		return listeUrl;
	}//fin getListeUrl

	/**
	 * Constructeur par défaut
	 */
	public Controlleur()
	{
		super();
		listeUrl = new UrlDAO();
	}//fin constructeur
	
	/* **********************************
	 * M E T H O D E S
	 * ******************************* */
	/**
	 * Gestion des évènements du logiciel
	 * @param e - L'évenement détecté [ActionEvent]
	 */
	public void actionPerformed(ActionEvent e)
	{
		((Component) e.getSource()).addMouseListener(laFenetre.getPanelRecherche().getTableUrls().getMouseListeners()[0]);
		if(e.getSource() == laFenetre.getLaBarreDeMenu().getMnitQuitter())
		{
			System.exit(0);
		}//fin if
		//Si on clique sur le boutton "Connexion" du panel de connexion
		else if(e.getSource() == laFenetre.getPanelConnexion().getBtnConnexion())
		{
			try{
				//On vérifie que les champs ne sont pas vides
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getTxbIdentifiant());
				ErrorManagement.checkEmptyField(laFenetre.getPanelConnexion().getPswdField());
				
			} catch (Exception ex) {
				//On affiche l'erreur dans le label d'erreurs
				ErrorManagement.showError(laFenetre.getPanelConnexion().getLblErreur(), ex.getMessage(), 1);
			}//fin catch
		}//fin else if
	}//fin actionPerformed
	
	/**
	 * Permet de définir la fenetre principale du controlleur principal
	 * @param laFenetre - La fenetre principal à définir [FenetrePrincipale]
	 */
	public void ajouterFenetrePrincipale(FenetrePrincipale laFenetre)
	{
		this.laFenetre = laFenetre;
	}//fin ajouterFenetrePrincipale

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Début mouseClicked");
		if(e.getSource() == laFenetre.getPanelRecherche().getTableUrls())
		{
			isAlreadyOneClick = false;
			System.out.println("Check double click");
			if (isAlreadyOneClick) {
		        System.out.println("double click");
		        isAlreadyOneClick = false;
		    }//fin if
			else 
			{
		        isAlreadyOneClick = true;
		        Timer t = new Timer("doubleclickTimer", false);
		        t.schedule(new TimerTask() {
		        	
		            @Override
		            public void run() {
		            	try{
		            		wait(1);
		            	} catch (InterruptedException ex) {
		            		System.out.println("erreur " + ex.getMessage());
		            	}
		                isAlreadyOneClick = false;
		            }
		        }, 500);
		        
		        Point p = e.getPoint();
                int row = laFenetre.getPanelRecherche().getTableUrls().rowAtPoint(p);
                if (row >= 0)
                {
                	System.out.println("T'as cliqué sur la " + (row + 1) + "eme PUTAIN DE LIGNE");
                	System.out.println(laFenetre.getPanelRecherche().getTableUrls().getValueAt(row,2).toString());
                }//fin if
		    }//fin else
		}//fin if
	}//fin mouseClicked
}//fin classe