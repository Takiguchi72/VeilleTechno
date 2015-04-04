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
}//fin classe
