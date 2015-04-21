package tests;

import classes.Tag;
import classes.Url;
import dao.TagDAO;
import dao.UrlDAO;
import dao.UtilisateurDAO;

public class TestUrlDAO {

	public static void main(String[] args) {
		UrlDAO bddUrl = new UrlDAO();
		
		/*
		//On créer trois tags pour la suite
		TagDAO bddTag = new TagDAO();
		Tag tag1 = bddTag.create(new Tag(0,"test1"));
		Tag tag2 = bddTag.create(new Tag(0,"test2"));
		Tag tag3 = bddTag.create(new Tag(0,"test3"));
		*/
		
		/* #################################################### *
		 * P R E M I E R   T E S T   -   Url sans tags associés *
		 * #################################################### */
		System.out.println(" ~ P R E M I E R   T E S T ~\n");
		//On créer une Url
		Url u = new Url(0, "test", "http://test.test/", new UtilisateurDAO().read("f.thierry"));
		
		Url urlCreee = bddUrl.create(u);
		
		//On vérifie qu'elle a été créée
		if(urlCreee.getId() != 0)
		{
			System.out.println("Création réussie !\n" + urlCreee.toString());
			
			//On récupère l'url créée
			Url urlRecuperee = bddUrl.read(urlCreee);
			
			//On vérifie que l'url a bien été récupérée
			if(urlRecuperee.getId() != 0 && urlRecuperee.getIntitule().equals("test") && urlRecuperee.getAdresse().equals("http://test.test/") && urlRecuperee.getCreateur().getIdentifiant().equals(new UtilisateurDAO().read("f.thierry").getIdentifiant()))
			{
				System.out.println("Récupération réussie !\n" + urlRecuperee.toString());
				
				//On modifie l'url
				urlRecuperee.setIntitule("un autre test");
				urlRecuperee.setAdresse("http://un.autre.test/");
				
				Url urlModifiee = bddUrl.update(urlRecuperee);
				
				//On vérifie les modifications
				if(urlModifiee.getIntitule().equals("un autre test") && urlModifiee.getAdresse().equals("http://un.autre.test/"))
				{
					System.out.println("Modification réussie !\n" + urlModifiee.toString());
					
					//On supprime l'url
					bddUrl.delete(urlModifiee);
					
					//On essaie de récupérer l'url supprimée
					Url urlSupprimee = new Url();
					urlSupprimee = bddUrl.read(urlModifiee.getId());
					
					if(urlSupprimee.getId() == 0 && urlSupprimee.getIntitule().equals("") && urlSupprimee.getAdresse().equals(""))
					{
						System.out.println("Suppression réussie !");
					}
					else
					{
						System.out.println("Echec de la suppression...");
					}
				}
				else
				{
					System.out.println("Echec de la modification...");
				}
			}
			else
			{
				System.out.println("Echec de la récupération...");
			}
		}
		else
		{
			System.out.println("Echec de la création...");
		}
		
		/* ###################################################### *
		 * D E U X I È M E   T E S T   -   Url avec tags associés *
		 * ###################################################### */
		System.out.println("\n\n ~ D E U X I È M E   T E S T ~\n");
		
		//On créer une Url
		Url u2 = new Url(0, "test", "http://test.test/", new UtilisateurDAO().read("f.thierry"));
		
		//On va associer des tags à l'url
		TagDAO bddTag = new TagDAO();
		Tag tag1 = bddTag.read(1);
		Tag tag2 = bddTag.read(2);
		Tag tag3 = bddTag.read(3);
		
		u2.getListeTagsAssocies().add(tag1);
		u2.getListeTagsAssocies().add(tag3);
		System.out.println("\n   Liste des tags associés de u2 :\n" + u2.getListeTagsAssocies() + "\n");
		
		Url urlCreee2 = bddUrl.create(u2);

		//On vérifie qu'elle a été créée
		if(urlCreee2.getId() != 0)
		{
			System.out.println("Création réussie !\n" + urlCreee2.toString());
			
			if(urlCreee2.getListeTagsAssocies().size() == 2)
			{
				System.out.println("   Les tags ont bien été associés !");
				
				//On récupère l'url créée
				Url urlRecuperee2 = bddUrl.read(urlCreee2);
				System.out.println("   Liste tags associés récupérée :\n" + urlRecuperee2.getListeTagsAssocies() + "\n");
				
				//On vérifie que l'url a bien été récupérée
				if(urlRecuperee2.getId() != 0 && urlRecuperee2.getIntitule().equals("test") && urlRecuperee2.getAdresse().equals("http://test.test/")
						&& urlRecuperee2.getCreateur().getIdentifiant().equals(new UtilisateurDAO().read("f.thierry").getIdentifiant()))
				{
					System.out.println("Récupération réussie !\n" + urlRecuperee2.toString());

					if(urlRecuperee2.getListeTagsAssocies().size() == 2)
					{
						//On modifie l'url
						urlRecuperee2.setIntitule("un autre test");
						urlRecuperee2.setAdresse("http://un.autre.test/");
						urlRecuperee2.getListeTagsAssocies().remove(1);		//On supprime tag3 de la liste
						urlRecuperee2.getListeTagsAssocies().add(tag2);		//On ajoute tag2 à la liste
						
						Url urlModifiee2 = bddUrl.update(urlRecuperee2);
						
						//On vérifie les modifications
						if(urlModifiee2.getIntitule().equals("un autre test") && urlModifiee2.getAdresse().equals("http://un.autre.test/"))
						{
							System.out.println("Modification réussie !\n" + urlModifiee2.toString());
							
							//On supprime l'url
							bddUrl.delete(urlModifiee2);
							
							//On essaie de récupérer l'url supprimée
							Url urlSupprimee2 = new Url();
							urlSupprimee2 = bddUrl.read(urlModifiee2.getId());
							
							if(urlSupprimee2.getId() == 0 && urlSupprimee2.getIntitule().equals("") && urlSupprimee2.getAdresse().equals(""))
							{
								System.out.println("Suppression réussie !");
							}
							else
							{
								System.out.println("Echec de la suppression...");
							}
						}
						else
						{
							System.out.println("Echec de la modification...");
						}
					}
					else
					{
						System.out.println("Echec de la récupération des tags associés...");
					}
				}
				else
				{
					System.out.println("Echec de la récupération...");
				}
			}
			else
			{
				System.out.println("Echec de l'association des tags...");
			}
		}
		else
		{
			System.out.println("Echec de la création...");
		}

	}//fin test
}//fin classe