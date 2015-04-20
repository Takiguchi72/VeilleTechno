package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Tag;
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
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_url (intitule, adresse, createur) VALUES (?,?,?);");
			
			//On affecte une valeur aux variables de la requête préparée
			prepare.setString(1, obj.getIntitule());
			prepare.setString(2, obj.getAdresse());
			prepare.setString(3, obj.getCreateur().getIdentifiant());
			
			//On exécute la requête
			prepare.executeUpdate();
			
			//On récupère l'id de l'objet qui vient d'être créé
			obj.setId(this.read(obj).getId());
			//Url urlCreee = this.read(obj);
			
			//Si la liste de obj n'est pas vide, c'est que des tags sont associés à cette url
			//donc on va insérer une ligne dans t_ligne_url_tag pour chaque tag associé
			if(obj.getListeTagAssocies().size() > 0)
			{
				//Pour chaque Tag de la liste,
				for(int i = 0 ; i < obj.getListeTagAssocies().size() ; i++)
				{
					//On créer la requête d'insertion
					PreparedStatement otherPrepare = this.connect.prepareStatement("INSERT INTO \"veilletechnologique\".t_ligne_url_tag (id_url, id_tag) VALUES (?,?)");
					
					//On affecte les valeurs
					otherPrepare.setInt(1, obj.getId());
					otherPrepare.setInt(2, obj.getListeTagAssocies().get(i).getId());
					
					//On exécute la requete
					otherPrepare.executeUpdate();
				}//fin for
			}//fin if
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
		Url obj = new Url();
		try{
			//On exécute la requête permettant de récupérer l'Url grâce à son id
			ResultSet result = this.connect
								.createStatement(
										ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE)
								.executeQuery("SELECT * FROM \"veilletechnologique\".t_url WHERE id=" + id + ";");
			//Si la requête retourne un résultat <=> L'url ayant pour id la valeur de la variable "id" existe, et est donc retournée
			if(result.first())
			{
				//On va modifier les attributs de l'objet précédament créé, pour lui affecter les valeurs récupérées via la bdd
				obj = new Url(id, result.getString("intitule"), result.getString("adresse"), new UtilisateurDAO().read(result.getString("createur")));
				
				//On va récupérer les tags associés à l'Url, pour alimenter la liste de l'objet
				PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM t_tag WHERE id IN (SELECT id_tag FROM t_ligne_url_tag WHERE id_url=?");
				
				//On affecte les valeurs
				prepare.setInt(1, obj.getId());
				
				//On exécute la requête
				result = prepare.executeQuery();
				
				//On créer une liste vide
				List<Tag> listeTagAssocies = new ArrayList<Tag>();
				
				//Pour chaque tuple retourné par la requête,
				while(result.next())
				{
					//On ajoute dans la liste le tag qui est associé à l'Url
					listeTagAssocies.add(new Tag(result.getInt("id"), result.getString("libelle")));
				}//fin while
				
				//On remplace la liste de tags de l'Url (qui est normalement vide) par celle générée via la requête
				obj.setListeTagAssocies(listeTagAssocies);
			}//fin if
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return obj;
	}//fin read
	
	/**
	 * Récupère l'Url correspondant à l'intitulé, l'adresse et le créateur de l'objet en paramètres, depuis la base de données.
	 * @param L'Url à récupérer [Url]
	 * @return L'Url récupéré [Url]
	 */
	public Url read(Url url)
	{
		Url obj = new Url();
		try {
			//On prépare la requête
			PreparedStatement prepare = this.connect.prepareStatement("SELECT * FROM \"veilletechnologique\".t_url WHERE intitule=? AND adresse=? AND createur=?;");
			
			//On affecte une valeur aux variables de la requête préparée
			prepare.setString(1, url.getIntitule());
			prepare.setString(2, url.getAdresse());
			prepare.setString(3, url.getCreateur().getIdentifiant());
			
			//On exécute la requête
			ResultSet result = prepare.executeQuery();
			
			//On récupère l'id de l'objet qui vient d'être créé
			if(result.first())
			{
				obj = new Url(	result.getInt("id"), 
						result.getString("intitule"), 
						result.getString("adresse"), 
						new UtilisateurDAO().read(result.getString("createur"))	);
				
				//On va récupérer les tags associés à l'Url, pour alimenter la liste de l'objet
				prepare = this.connect.prepareStatement("SELECT * FROM t_tag WHERE id IN (SELECT id_tag FROM t_ligne_url_tag WHERE id_url=?");
				
				//On affecte les valeurs
				prepare.setInt(1, obj.getId());
				
				//On exécute la requête
				result = prepare.executeQuery();
				
				//On créer une liste vide
				List<Tag> listeTagAssocies = new ArrayList<Tag>();
				
				//Pour chaque tuple retourné par la requête,
				while(result.next())
				{
					//On ajoute dans la liste le tag qui est associé à l'Url
					listeTagAssocies.add(new Tag(result.getInt("id"), result.getString("libelle")));
				}//fin while
				
				//On remplace la liste de tags de l'Url (qui est normalement vide) par celle générée via la requête
				obj.setListeTagAssocies(listeTagAssocies);
			}//fin if
		} catch (SQLException ex) {
			ex.printStackTrace();
		}//fin catch
		return obj;
	}//fin read
	
	/**
	 * Modifie une Url dans la base de données depuis l'Url passée en paramètre
	 * @param L'Url ayant pour valeurs celles à modifier dans la base [Url]
	 * @return L'Url modifiée [Url]
	 */
	public Url update(Url obj)
	{
		try {
			//On prépare la requête
			PreparedStatement prepare = this.connect.prepareStatement("UPDATE \"veilletechnologique\".t_url SET intitule=?, adresse=? WHERE id=?;");
			
			//On affecte les valeurs
			prepare.setString(1, obj.getIntitule());
			prepare.setString(2, obj.getAdresse());
			prepare.setInt(3, obj.getId());
			
			//On exécute la requete
			prepare.executeUpdate();
			
			//Si la liste de tags associés est supérieure à 0, on va modifier les associations entre l'Url à modifier
			//et les tags qui lui sont associés, dans t_ligne_url_tag
			if(obj.getListeTagAssocies().size() > 0)
			{
				//On va dans un premier temps, vérifier quelles sont les associations entre l'url et les tags qui sont à supprimer
				
				//On récupère la liste des ID des tags associés à l'Url (dans t_ligne_url_tag)
				prepare = this.connect.prepareStatement("SELECT id_tag FROM \"veilletechnologique\".t_ligne_url_tag WHERE id_url=?");
				prepare.setInt(1, obj.getId());
				ResultSet result = prepare.executeQuery();
				
				//Pour chaque ID, on va regarder si le tag correspondant figure bien dans la liste des tags associés (à l'objet).
				while(result.next())
				{
					//On créer un booleen qui va permettre de savoir à la fin de la boucle,
					//si le tag figure dans la liste des tags associés à l'Url
					boolean trouve = false;
					
					//On récupère le tag correspondant à l'association en cours (de la boucle)
					Tag tagDeLaBoucle = new TagDAO().read(result.getInt("id_tag"));
					
					//On parcourt la liste des tags associés à l'url pour savoir si le tag correspondant à l'association en cours est dans la liste
					for(int i = 0 ; i < obj.getListeTagAssocies().size() ; i++)
					{
						//Si le tag correspond à un tag de la liste, on met le bouléen à "true" et on stope la boucle
						if(tagDeLaBoucle.equals(obj.getListeTagAssocies().get(i)))
						{
							trouve = true;
							break;
						}//fin if
					}//fin for
					
					//Si le tag correspondant à l'association n'est pas dans la liste des tags de l'url,
					if(trouve == false)
					{
						//On va supprimer l'association entre ce tag et l'url
						prepare = this.connect.prepareStatement("DELETE FROM \"veilletechnologique\".t_ligne_url_tag WHERE id_url=? AND id_tag=? ;");
						prepare.setInt(1, obj.getId());
						prepare.setInt(2, tagDeLaBoucle.getId());
						prepare.executeUpdate();
					}//fin if
				}//fin while
				
				//Ensuite, on va vérifier quelles sont les nouvelles associations, à créer
				//TODO
			}//fin if
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
				listeDUrls.add(new Url( result.getInt("id"), 
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