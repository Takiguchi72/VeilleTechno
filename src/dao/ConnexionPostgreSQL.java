package dao;
/**
 * Class : ConnexionPostgreSQL
 * @author takiguchi
 * @version 1
 */
import java.sql.Connection;
import dao.DonneesConnexion;
import java.sql.DriverManager;

public class ConnexionPostgreSQL {
	/**
	 * Retourne un objet de type Connection, correspondant à la connexion établie avec PostgreSQL
	 * @return La Connexion [sql.Connection]
	 */
	public static Connection getInstance()
	{
		//Localisation du driver JDBC
		try{
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Où est votre pilote JDBC pour PostgreSQL ? "
					+ "Rentrez son chemin dans la liste des bibliothèques JRE !");
			e.printStackTrace();
		}//Fin catch
		//Création d'un objet de type Connection
		Connection theConnection = null;
		try{
			//Connexion à la basse
			theConnection = DriverManager.getConnection("jdbc:postgresql://" + DonneesConnexion.getAddress() + "/veilletechno", DonneesConnexion.getLogin(), DonneesConnexion.getMdp());
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion à la base de donnée :\n" + e);
		}//Fin catch
		return theConnection;
	}//end getInstance()
	/**
	 * Create a prepared connection to the postgresql database
	 * @return The preared connection [sql.Connection]
	 */
	public static Connection getPreparedInstance()
	{
		//Localisation du driver JDBC
		try{
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
		}//Fin catch
		//Création d'un objet de type Connection
		Connection theConnection = null;
		try{
			//Connexion à la basse
			theConnection = DriverManager.getConnection("jdbc:postgresql://" + DonneesConnexion.getAddress() + "/fthierry", DonneesConnexion.getLogin(), DonneesConnexion.getMdp());
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion à la base de donnée :\n" + e);
		}//Fin catch
		return theConnection;
	}//Fin getPreparedInstance()
}//end class