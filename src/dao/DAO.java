package dao;

/**
 * Class : DAO
 * @author takiguchi
 * @version 1
 */

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
	/* **********
	 * Attributes
	 ************/
	public Connection connect = ConnexionPostgreSQL.getInstance();

	/* *******
	 * Methods
	 *********/
	
	/**
	 * Abstract method to create a new <T> object
	 * @param The new <T> object to create [T]
	 * @return The new <T> object
	 */
	public abstract T create(T obj);
	
	/**
	 * Abstract method to read a <T> object
	 * @param The object code [integer]
	 * @return Return the <T> object
	 */
	public abstract T read(int code);
	
	/**
	 * Abstract method to read a <T> object
	 * @param The object [T]
	 * @return Return the <T> object
	 */
	public abstract T read(T obj);
	
	/**
	 * Abstract method to update a <T> object
	 * @param The <T> object to update [T]
	 * @return The <T> object updated
	 */
	public abstract T update(T obj);
	
	/**
	 * Abstract method to delete a <T> object
	 */
	public abstract void delete(T obj);
	
	/**
	 * Abstract method to get all the Table
	 * @return A <T> list
	 */
	public abstract List<T> selectAll();
	
	/**
	 * Abstract method to get the elements that match to the criterion
	 * @param critere
	 * @return
	 * @throws Exception
	 */
	public abstract List<T> selectCorrespondantA(String critere) throws Exception;
	
}//end Class