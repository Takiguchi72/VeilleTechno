package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Tag;

public class TagDAO extends DAO<Tag> {
	/**
	 * Insert dans la base de données un nouveau Tag à partir du Tag passé en paramètre
	 * @param Le Tag à insérer dans la base de données [Tag]
	 * @return Le Tag que l'on vient d'insérer dans la base de données [Tag]
	 */
	public Tag create(Tag obj)
	{
		try {
			//On prépare la requête d'insertion
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_tag (id, libelle) VALUES (?, ?) ;");
			//On assigne des valeurs à la requête préparée
			prepare.setInt(1,obj.getId());
			prepare.setString(2, obj.getLibelle());
			//On exécute la requête
			prepare.executeUpdate();
			obj = this.read(obj.getId());
		} catch ( SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return obj;
	}//fin create
	
	
	
	/**
	 * Récupère le Tag correspondant à l'id passé en paramètre, depuis la base de données.
	 * @param L'id du Tag à récupérer [Integer]
	 * @return Le Tag récupéré [Tag] 
	 */
	public Tag read(int id)
	{
		//On créer une Url vide
		Tag leTag = new Tag();
		try{
			//On exécute la requête permettant de récupérer l'Url grâce à son id
			ResultSet result = this.connect
								.createStatement(
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE)
								.executeQuery("SELECT * FROM \"veilletechnologique\".t_tag WHERE id=" + id + ";");
			if(result.first())
				leTag = new Tag(id, result.getString("libelle"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return leTag;
	}//fin read
	
	/**
	 * Modifie un Tag dans la base de données depuis le Tag passée en paramètre
	 * @param Le Tag ayant pour valeurs celles à modifier dans la base [Tag]
	 * @return Le Tag modifiée [Tag]
	 */
	public Tag update(Tag obj)
	{
		try {
			this.connect.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE
						).executeUpdate(
								"UPDATE \"veilletechnologique\".t_tag " +
								"SET adresse = '" + obj.getLibelle() + "' " +
								"WHERE id = " + obj.getId() + ";");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
		return obj;
	}//fin update
	
	/**
	 * Supprime un Tag dans la base de données
	 */
	public void delete(Tag obj)
	{
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM \"veilletechnologique\".t_tag " +
							"WHERE id = " + obj.getId() + " ;");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
	}//fin delete
	
	/**
	 * Retourne la liste de toutes les Tags de la base de données
	 * @return La liste de toutes les Tags de la base de données [ArrayList<Tag>]
	 */
	public List<Tag> selectAll()
	{
		List<Tag> listeDUrl = new ArrayList<Tag>();
		try {
			ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM \"veilletechnologique\".t_tag ;");
			while(result.next())
			{
				listeDUrl.add(new Tag(result.getInt("id"), result.getString("libelle")));
			}//fin while
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return listeDUrl;
	}//fin selectAll
}//fin classe