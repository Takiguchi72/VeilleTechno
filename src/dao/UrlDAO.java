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
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_url (id, adresse) VALUES (?, ?) ;");
			//On assigne des valeurs à la requête préparée
			prepare.setInt(1,obj.getId());
			prepare.setString(2, obj.getAdresse());
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
			ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM \"veilletechnologique\".t_url ;");
			while(result.next())
			{
				listeDUrl.add(new Url(result.getInt("id"), result.getString("intitule"), result.getString("adresse"), new UtilisateurDAO().read(result.getString("createur"))));
			}//fin while
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return listeDUrl;
	}//fin selectAll
}//fin classe