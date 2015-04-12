package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes.Url;

public class UrlDAO extends DAO<Url> {
	/**
	 * Insert dans la base de données une nouvelle Url à partir de l'Url passée en paramètre
	 * @param L'Url à insérer dans la base de données [Url]
	 * @return L'Url que l'on vient d'insérer dans la base de données [Url]
	 */
	public Url create(Url obj)
	{
		try {
			//On prépare la requête d'insertion
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_url (id, adresse) VALUES (?) ;");
			//On assigne des valeurs à la requête préparée
			prepare.setString(1, obj.getAdresse());
			//On exécute la requête
			prepare.executeUpdate();
			obj = this.read(obj.getId());
		} catch ( SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return obj;
	}//fin create
	
	/**
	 * Récupère l'Url correspondant à l'id passé en paramètre, depuis la base de données.
	 * @param L'id de l'url à récupérer [Integer]
	 * @return L'Url récupéré [Url] 
	 */
	public Url read(int id)
	{
		//On créer une Url vide
		Url lUrl = new Url();
		try{
			//On exécute la requête permettant de récupérer l'Url grâce à son id
			ResultSet result = this.connect
								.createStatement(
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE)
								.executeQuery("SELECT * FROM \"veilletechnologique\".t_url WHERE id=" + id + ";");
			if(result.first())
				lUrl = new Url(id, result.getString("intitule"), result.getString("adresse"), new UtilisateurDAO().read(result.getString("createur")));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return lUrl;
	}//fin read
	
	/**
	 *  Récupère l'Url correspondant à l'intitulé passé en paramètre, depuis la base de données.
	 * @param L'intitulé de l'url à récupérer [String]
	 * @return L'Url récupéré [Url] 
	 */
	public Url read(String intitule)
	{
		//On créer une Url vide
		Url lUrl = new Url();
		try{
			//On exécute la requête permettant de récupérer l'Url grâce à son id
			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
							.executeQuery("SELECT * FROM \"veilletechnologique\".t_url WHERE intitule=" + intitule + ";");
			if(result.first())
				lUrl = new Url(result.getInt("id"), intitule, result.getString("adresse"), new UtilisateurDAO().read(result.getString("createur")));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return lUrl;
	}
	
	/**
	 * Modifie une Url dans la base de données depuis l'Url passée en paramètre
	 * @param L'Url ayant pour valeurs celles à modifier dans la base [Url]
	 * @return L'Url modifiée [Url]
	 */
	public Url update(Url obj)
	{
		try {
			this.connect.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE
						).executeUpdate(
								"UPDATE \"veilletechnologique\".t_url " +
								"SET adresse = '" + obj.getAdresse() + "' " +
								"WHERE id = " + obj.getId() + ";");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
		return obj;
	}//fin update
	
	/**
	 * Supprime une Url dans la base de données
	 */
	public void delete(Url obj)
	{
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM \"veilletechnologique\".t_url " +
							"WHERE id = " + obj.getId() + " ;");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
	}//fin delete
	
	/**
	 * Retourne la liste de toutes les Urls de la base de données
	 * @return La liste de toutes les Urls de la base de données [ArrayList<Url>]
	 */
	public List<Url> selectAll()
	{
		List<Url> listeDUrl = new ArrayList<Url>();
		try {
			ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM \"veilletechnologique\".t_url ORDER BY intitule;");
			while(result.next())
			{
				listeDUrl.add(new Url(result.getInt("id"), result.getString("intitule"), result.getString("adresse"), new UtilisateurDAO().read(result.getString("createur"))));
			}//fin while
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return listeDUrl;
	}//fin selectAll
	
	/**
	 * Retourne la liste des Urls correspondant aux critères de selection passés en paramètres 
	 * @param Critère de selection [String]
	 * @return La liste d'Urls correspondant aux critères de selection [List<Url>]
	 */
	public List<Url> selectCorrespondantA(String clauseWhere) throws Exception
	{
		List<Url> listeDUrls = new ArrayList<Url>();
		//Si aucun critère n'est passé en paramètre, une exception est levée
		if (clauseWhere.equals(""))
			throw new Exception("Veuillez indiquer votre recherche", new Throwable("aucunCritere"));
			
		try {
			ResultSet result = this.connect
					.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM \"veilletechnologique\".t_url "		//getClausesWhere() correspond à la partie de la requête
								+ util.FonctionsString.getClausesWhere(clauseWhere)		//filtrant les résultats
								+ "ORDER BY id");	
			//Pour chaque tuple dans le résultat retourné par la bdd,
			while(result.next())
			{
				//On ajoute à la liste l'Url correspondant au tuple
				listeDUrls.add(new Url(  result.getInt("id"), 
										result.getString("intitule"), 
										result.getString("adresse"), 
										new UtilisateurDAO().read(result.getString("createur"))));
			}//fin while
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return listeDUrls;
	}//fin select
}//fin classe