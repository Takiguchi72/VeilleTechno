package classes;

public class Tag {
	/* **********************************
	 * A T T R I B U T S
	 * ******************************* */
	private int id;
	private String libelle;
	
	/* **********************************
	 * A C C E S S E U R S
	 * ******************************* */
	/**
	 * Retourne l'id du Tag
	 * @return Id du Tag [Integer]
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Modifie la valeur id du Tag à partir du paramètre
	 * @param Le nouvel id du Tag [Integer]
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Retourne le libelle du Tag
	 * @return Libelle du Tag [String]
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * Modifie la valeur id du Tag à partir du paramètre
	 * @param Le nouveau libelle du Tag [String]
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/* **********************************
	 * C O N S T R U C T E U R S
	 * ******************************* */
	/**
	 * Instancie un objet de la classe Tag, avec 0 pour l'id, et "" pour le libelle.
	 */
	public Tag()
	{
		id 		= 0;
		libelle = "";
	}//fin Tag()
	
	/**
	 * Instancie un objet de la classe Tag à partir des paramètres
	 * @param L'id du Tag [Integer]
	 * @param Le libelle du Tag [String]
	 */
	public Tag(int id, String libelle)
	{
		this.id		 = id;
		this.libelle = libelle;
	}//fin Tag(...)
	
	/* **********************************
	 * M É T H O D E S
	 * ******************************* */
	/**
	 * Retourne une chaine contenant les informations du Tag
	 * @return [String]
	 */
	@Override
	public String toString()
	{
		return "Id : " + id + ", Libelle : " + libelle;
	}//fin toString
}//fin classe