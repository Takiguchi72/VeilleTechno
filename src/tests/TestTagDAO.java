package tests;

import classes.Tag;
import dao.TagDAO;

public class TestTagDAO {

	public static void main(String[] args) {
		TagDAO bdd = new TagDAO();
		
		//On créer un tag
		System.out.println("Création du tag");
		Tag t = new Tag();
		t.setLibelle("test");
		
		Tag resultatCreation = bdd.create(t);
		
		//On vérifie qu'il a été créé (id!=0)
		if(resultatCreation.getId() != 0)
		{
			System.out.println("Création réussie !\n" + resultatCreation.toString());
			
			//On récupère le tag créé
			Tag tagRecupere = bdd.read(t);
			
			//On vérifie que le tag est bien récupéré
			if(tagRecupere.getId() != 0 && tagRecupere.getLibelle().equals("test"))
			{
				System.out.println("Récupération réussie !\n" + tagRecupere.toString());
				
				//On modifie le tag
				tagRecupere.setLibelle("un autre test");
				Tag tagModifie = bdd.update(tagRecupere);
				
				//On vérifie les modifs
				if(tagModifie.getId()!=0 && tagModifie.getLibelle().equals("un autre test"))
				{
					System.out.println("Modification réussie !\n" + tagModifie.toString());
					
					//On supprime le tag
					bdd.delete(tagModifie);
					
					//On essaie de le récupérer
					Tag tagSupprime = new Tag();
					tagSupprime = bdd.read(tagModifie.getId());
					
					if(tagSupprime.getId() == 0 && tagSupprime.getLibelle().equals(""))
					{
						System.out.println("Suppression réussie !");
					}
					else
					{
						System.out.println("Echec de la suppression");
					}
				}
				else
				{
					System.out.println("Echec de la modification");
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
	}
}