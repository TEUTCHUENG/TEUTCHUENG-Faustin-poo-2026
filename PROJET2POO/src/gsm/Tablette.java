package gsm;

// Tablette herite de MS (heritage)
// C'est un autre type de MS avec une taille d'ecran
public class Tablette extends MS {

    private double tailleEcran; // en pouces

    // Constructeur
    public Tablette(String nom, String prenom, String motDePasse,
                    String msisdn, String imsi, double tailleEcran) {
        super(nom, prenom, motDePasse, msisdn, imsi);
        this.tailleEcran = tailleEcran;
    }

    // Polymorphisme : on redefini getTypeAppareil()
    @Override
    public String getTypeAppareil() {
        return "Tablette (" + tailleEcran + " pouces)";
    }

    // On redefini afficher()
    @Override
    public void afficher() {
        System.out.println("--- Tablette ---");
        System.out.println("  Nom     : " + nom + " " + prenom);
        System.out.println("  Ecran   : " + tailleEcran + " pouces");
        System.out.println("  MSISDN  : " + msisdn);
        System.out.println("  IMSI    : " + imsi);
        System.out.println("  Appels  : " + nbAppels);
    }
}
