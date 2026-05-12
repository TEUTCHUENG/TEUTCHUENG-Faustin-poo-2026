package gsm;

// Smartphone herite de MS (heritage)
// C'est un type particulier de MS avec une marque et un systeme d'exploitation
public class Smartphone extends MS {

    private String marque;
    private String systeme; // Android, iOS...

    // Constructeur : on appelle d'abord le constructeur de MS avec super()
    public Smartphone(String nom, String prenom, String motDePasse,
                      String msisdn, String imsi, String marque, String systeme) {
        super(nom, prenom, motDePasse, msisdn, imsi);
        this.marque  = marque;
        this.systeme = systeme;
    }

    // On redefini getTypeAppareil() = polymorphisme
    @Override
    public String getTypeAppareil() {
        return "Smartphone " + marque + " (" + systeme + ")";
    }

    // On redefini afficher() pour ajouter les infos du smartphone
    @Override
    public void afficher() {
        System.out.println("--- Smartphone ---");
        System.out.println("  Nom     : " + nom + " " + prenom);
        System.out.println("  Marque  : " + marque);
        System.out.println("  OS      : " + systeme);
        System.out.println("  MSISDN  : " + msisdn);
        System.out.println("  IMSI    : " + imsi);
        System.out.println("  Appels  : " + nbAppels);
    }
}
