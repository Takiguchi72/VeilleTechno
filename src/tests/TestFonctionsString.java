package tests;

/**
 * Tests unitaire des fonctions du package util du projet
 */

import static org.junit.Assert.*;
import static util.FonctionsString.*;
import org.junit.Before;
import org.junit.Test;

public class TestFonctionsString {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test unitaire de la fonction getTableauEnString()
	 */
	@Test
	public void testGetTablauEnString() {
		String tableau[] = new String[2];
		tableau[0] = "coucou";
		tableau[1] = "cava?";
		String resultatObtenu = getTableauEnString(tableau);
		String resultatAttendu = "coucou;cava?;";
		assertEquals("Resultat obtenu = \"" + resultatObtenu + "\"", true, resultatObtenu.equals(resultatAttendu));
	}//fin testGetTablauEnString

	/**
	 * Test unitaire de la fonction decouperUneChaine()
	 */
	@Test
	public void testDecouperUneChaine()
	{
		String chaineDepart = "ceci est un test";
		String chaineObtenue = getTableauEnString(decouperUneChaine(chaineDepart));
		String chaineAttendue = "ceci;est;un;test;";
		assertEquals("Résultat obtenu : \"" + chaineObtenue + "\"", true, chaineObtenue.equals(chaineAttendue));
	}//fin testDecouperUneChaine
	
	/**
	 * Test unitaire de la fonction getClausesWhere
	 */
	@Test
	public void testGetClausesWhere()
	{
		String resultatObtenu = getClausesWhere("test4 test2 test3");
		String resultatAttendu = 
				  "WHERE intitule LIKE '%test1%' OR adresse LIKE '%test1%' "
			    + "OR createur LIKE '%test1%'"
				+ "OR id IN (SELECT id_url FROM \"veilletechnologique\".t_ligne_url_tag "
						+ "WHERE id_tag IN (SELECT id FROM \"veilletechnologique\".t_tag WHERE libelle LIKE '%test2%')) "
				+ "OR id IN (SELECT id_url FROM \"veilletechnologique\".t_ligne_url_tag "
						+ "WHERE id_tag IN (SELECT id FROM \"veilletechnologique\".t_tag WHERE libelle LIKE '%test3%')) ";
		
		assertEquals("Résultat obtenu : \"" + resultatObtenu + "\"", true, resultatObtenu.equals(resultatAttendu));
	}
	
	/**
	 * Test unitaire de la fonction md5()
	 */
	@Test
	public void testMd5()
	{
		String resultat = "";
		try { resultat = md5("passe"); } catch (Exception ex) { System.out.println(ex.getMessage()); }
		assertEquals("Le résultat diffère de ce qui était attendu...", "b89f7a5ff3e3a225d572dac38b2a67f7", resultat);
	}
}//fin classe
