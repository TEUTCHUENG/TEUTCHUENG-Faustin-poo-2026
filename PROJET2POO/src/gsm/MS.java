package gsm;

// Classe MS = Mobile Station (l'utilisateur du reseau)
// Elle implemente Affichable pour avoir la methode afficher()
public class MS implements Affichable {

    // --- Les attributs de l'utilisateur ---
    protected String nom;
    protected String prenom;
    protected String motDePasse;
    protected String msisdn;        // Numero de telephone
    protected String imsi;          // Numero de la carte SIM

    // Tableau pour stocker les appels recus (max 10)
    protected String[] appelsRecus;
    protected int nbAppels;

    // --- Constructeur ---
    public MS(String nom, String prenom, String motDePasse, String msisdn, String imsi) {
        this.nom         = nom;
        this.prenom      = prenom;
        this.motDePasse  = motDePasse;
        this.msisdn      = msisdn;
        this.imsi        = imsi;
        this.appelsRecus = new String[10];
        this.nbAppels    = 0;
    }

    // --- Getters ---
    public String getNom()    { return nom; }
    public String getPrenom() { return prenom; }
    public String getMsisdn() { return msisdn; }
    public String getImsi()   { return imsi; }

    // Verifie si le mot de passe est correct
    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }

    // Verifie si ce MS peut s'attacher a une BTS (la BTS ne doit pas etre saturee)
    public boolean peutSAttacher(BTS bts) {
        return !bts.estSaturee();
    }

    // Appelle un autre utilisateur MS
    public void appeler(MS cible) throws ReseauException {
        if (cible == null) {
            throw new ReseauException("Erreur : l'utilisateur cible n'existe pas.");
        }
        if (cible.nbAppels >= 10) {
            throw new ReseauException("Erreur : la boite d'appels de " + cible.nom + " est pleine.");
        }
        // On enregistre l'appel dans la liste de la cible
        String info = "Appel de " + this.prenom + " " + this.nom + " (" + this.msisdn + ")";
        cible.appelsRecus[cible.nbAppels] = info;
        cible.nbAppels++;
        System.out.println("  Appel passe : " + this.msisdn + " --> " + cible.getMsisdn());
    }

    // Affiche tous les appels recus
    public void afficherAppelsRecus() {
        System.out.println("Appels recus de " + prenom + " " + nom + " :");
        if (nbAppels == 0) {
            System.out.println("  Aucun appel.");
        } else {
            for (int i = 0; i < nbAppels; i++) {
                System.out.println("  " + (i + 1) + ". " + appelsRecus[i]);
            }
        }
    }

    // Retourne le type d'appareil (peut etre redefini dans les sous-classes)
    public String getTypeAppareil() {
        return "Mobile";
    }

    // Methode de l'interface Affichable
    @Override
    public void afficher() {
        System.out.println("--- Utilisateur MS ---");
        System.out.println("  Nom     : " + nom + " " + prenom);
        System.out.println("  MSISDN  : " + msisdn);
        System.out.println("  IMSI    : " + imsi);
        System.out.println("  Appareil: " + getTypeAppareil());
        System.out.println("  Appels  : " + nbAppels);
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " [" + msisdn + "]";
    }
}
