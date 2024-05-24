package RaPizza.model;

// https://www.data.gouv.fr/fr/datasets/table-de-composition-nutritionnelle-des-aliments-ciqual/
// https://www.nutritionvalue.org/Apples%2C_without_skin%2C_raw_nutritional_value.html

public class NutriPackage {
	final int valeurCalorique; // kcal & kJ
	final float lipides, graissesSaturées, graissesMonoInsaturées, GraissesPolyInsaturées;
	final float glucides, sucre;
	/* Others */
	final float protéine, fibreAlimentaire, cholestérol, sodium, eau;

	final float vitamines[];// = new float[12]; // A, B1 .. K

	/* Minéraux */
	final float calcium, cuivre, fer, magnésium, manganèse, phosphore, potassium, sélénium, zinc;

	NutriPackage(int a, float b, float c, float d, float e, float f, float g, float h, float i, float j, float k, float l, float[] vitamines, float calcium, float cuivre, float fer, float magnésium, float manganèse, float phosphore, float potassium, float sélénium, float zinc) {
		this.valeurCalorique = a;
		this.lipides = b;
		this.graissesSaturées = c;
		this.graissesMonoInsaturées = d;
		this.GraissesPolyInsaturées = e;
		this.glucides = f;
		this.sucre = g;
		this.protéine = h;
		this.fibreAlimentaire = i;
		this.cholestérol = j;
		this.sodium = k;
		this.eau = l;
		this.vitamines = vitamines;

		this.calcium = calcium;
		this.cuivre = cuivre;
		this.fer = fer;
		this.magnésium = magnésium;
		this.manganèse = manganèse;
		this.phosphore = phosphore;
		this.potassium = potassium;
		this.sélénium = sélénium;
		this.zinc = zinc;
	}
}
