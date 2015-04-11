package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes.Utilisateur;

public class UtilisateurDAO  {
	public Connection connect = ConnexionPostgreSQL.getInstance();
	
	/**
	 * Insert dans la base de données un nouvel Utilisateur à partir de celui passé en paramètre
	 * @param L'Utilisateur à insérer dans la base de données [Utilisateur]
	 * @return L'Utilisateur que l'on vient d'insérer dans la base de données [Utilisateur]
	 */
	public Utilisateur create(Utilisateur obj)
	{
		try {
			//On prépare la requête d'insertion
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_users (identifiant, email) VALUES (?, ?) ;");
			//On assigne des valeurs à la requête préparée
			prepare.setString(1, obj.getIdentifiant());
			prepare.setString(2, obj.getEmail());
			//On exécute la requête
			prepare.executeUpdate();
			obj = this.read(obj.getIdentifiant());
		} catch ( SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return obj;
	}//fin create
	
	/**
	 * Récupère l'Utilisateur correspondant à l'identifiant passé en paramètre, depuis la base de données.
	 * @param L'identifiant de l'Utilisateur à récupérer [String]
	 * @return L'Utilisateur récupéré [Utilisateur] 
	 */
	public Utilisateur read(String identifiant)
	{
		//On créer une Url vide
		Utilisateur user = new Utilisateur();
		try{
			//On exécute la requête permettant de récupérer l'Url grâce à son id
			ResultSet result = this.connect
								.createStatement(
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE)
								.executeQuery("SELECT * FROM \"veilletechnologique\".t_users WHERE identifiant = '" + identifiant + "';");
			if(result.first())
				user = new Utilisateur(identifiant, result.getString("email"), result.getString("passwd"));
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return user;
	}//fin read
	
	/**
	 * Modifie un Utilisateur dans la base de données depuis celui qui est passé en paramètre
	 * @param L'Utilisateur ayant pour valeurs celles à modifier dans la base [Utilisateur]
	 * @return L'Utilisateur modifiée [Utilisateur]
	 */
	public Utilisateur update(Utilisateur obj)
	{
		try {
			this.connect.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE
						).executeUpdate(
								"UPDATE \"veilletechnologique\".t_users " +
								"SET email = '" + obj.getEmail() + "' " +
								"WHERE identifiant = '" + obj.getIdentifiant() + "';");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
		return obj;
	}//fin update
	
	/**
	 * Supprime un Utilisateur dans la base de données
	 */
	public void delete(Utilisateur obj)
	{
		try {
			this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
							"DELETE FROM \"veilletechnologique\".t_users " +
							"WHERE identifiant = '" + obj.getIdentifiant() + "' ;");
		} catch (SQLException ex) {
            ex.printStackTrace();
		}//end Catch
	}//fin delete
	
	/**
	 * Retourne la liste de tous les Utilisateurs de la base de données
	 * @return La liste de toutes les Utilisateurs de la base de données [ArrayList<Utilisateur>]
	 */
	public List<Utilisateur> selectAll()
	{
		List<Utilisateur> listeDUtilisateurs = new ArrayList<Utilisateur>();
		try {
			ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM \"veilletechnologique\".t_users ;");
			while(result.next())
			{
				listeDUtilisateurs.add(new Utilisateur(result.getString("identifiant"), result.getString("email"), result.getString("passwd")));
			}//fin while
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return listeDUtilisateurs;
	}//fin selectAll
}
