package gsm;

// Classe BTS = Base Transceiver Station (station de base)
// Une BTS gere plusieurs MS (utilisateurs) et peut avoir plusieurs cellules
public class BTS implements Affichable {

    // --- Attributs de la BTS ---
    private int    numero;
    private String emplacement;
    private double hauteur;           // hauteur de l'antenne en metres
    private String typeMilieu;        // "urbain" ou "rural"
    private double rayonCouverture;   // rayon de la cellule en km
    private double puissance;         // puissance d'emission en Watts
    private int    nbMaxUtilisateurs; // capacite maximale
    private int    nbCellules;        // nombre de cellules gerees par cette BTS

    // Tableau des utilisateurs attaches a cette BTS
    private MS[] utilisateurs;
    private int  nbUtilisateurs;      // nombre actuel d'utilisateurs

    // --- Constructeur ---
    public BTS(int numero, String emplacement, double hauteur, String typeMilieu,
               double rayonCouverture, double puissance, int nbMaxUtilisateurs, int nbCellules) {
        this.numero              = numero;
        this.emplacement         = emplacement;
        this.hauteur             = hauteur;
        this.typeMilieu          = typeMilieu;
        this.rayonCouverture     = rayonCouverture;
        this.puissance           = puissance;
        this.nbMaxUtilisateurs   = nbMaxUtilisateurs;
        this.nbCellules          = nbCellules;
        this.utilisateurs        = new MS[nbMaxUtilisateurs];
        this.nbUtilisateurs      = 0;
    }

    // --- Getters ---
    public int    getNumero()            { return numero; }
    public String getEmplacement()       { return emplacement; }
    public String getTypeMilieu()        { return typeMilieu; }
    public int    getNbUtilisateurs()    { return nbUtilisateurs; }
    public int    getNbMaxUtilisateurs() { return nbMaxUtilisateurs; }
    public int    getNbCellules()        { return nbCellules; }

    // Verifie si la BTS est saturee (plus de place)
    public boolean estSaturee() {
        return nbUtilisateurs >= nbMaxUtilisateurs;
    }

    // Ajoute un MS a cette BTS
    public void ajouterMS(MS ms) throws ReseauException {
        if (ms == null) {
            throw new ReseauException("Erreur : le MS est null.");
        }
        if (estSaturee()) {
            throw new ReseauException("BTS " + numero + " saturee ! Impossible d'ajouter " + ms);
        }
        // Verifier que le MS n'est pas deja dans cette BTS
        for (int i = 0; i < nbUtilisateurs; i++) {
            if (utilisateurs[i].getMsisdn().equals(ms.getMsisdn())) {
                throw new ReseauException("Le MS " + ms.getMsisdn() + " est deja attache a BTS " + numero);
            }
        }
        utilisateurs[nbUtilisateurs] = ms;
        nbUtilisateurs++;
        System.out.println("  [OK] " + ms + " attache a BTS " + numero);
    }

    // Supprime un MS par son numero de telephone (MSISDN)
    public void supprimerMS(String msisdn) throws ReseauException {
        for (int i = 0; i < nbUtilisateurs; i++) {
            if (utilisateurs[i].getMsisdn().equals(msisdn)) {
                System.out.println("  [OK] " + utilisateurs[i] + " retire de BTS " + numero);
                // On decale le tableau pour combler le trou
                for (int j = i; j < nbUtilisateurs - 1; j++) {
                    utilisateurs[j] = utilisateurs[j + 1];
                }
                utilisateurs[nbUtilisateurs - 1] = null;
                nbUtilisateurs--;
                return;
            }
        }
        throw new ReseauException("MS " + msisdn + " introuvable dans BTS " + numero);
    }

    // Recherche un MS par son MSISDN, retourne null s'il n'est pas trouve
    public MS rechercherMS(String msisdn) {
        for (int i = 0; i < nbUtilisateurs; i++) {
            if (utilisateurs[i].getMsisdn().equals(msisdn)) {
                return utilisateurs[i];
            }
        }
        return null;
    }

    // Methode de l'interface Affichable
    @Override
    public void afficher() {
        System.out.println("=== BTS N " + numero + " ===");
        System.out.println("  Emplacement : " + emplacement);
        System.out.println("  Hauteur     : " + hauteur + " m");
        System.out.println("  Milieu      : " + typeMilieu);
        System.out.println("  Rayon       : " + rayonCouverture + " km");
        System.out.println("  Puissance   : " + puissance + " W");
        System.out.println("  Cellules    : " + nbCellules);
        System.out.println("  Utilisateurs: " + nbUtilisateurs + " / " + nbMaxUtilisateurs);
        System.out.println("  Etat        : " + (estSaturee() ? "SATUREE" : "Disponible"));
        System.out.println("  Liste MS :");
        if (nbUtilisateurs == 0) {
            System.out.println("    (aucun utilisateur)");
        } else {
            for (int i = 0; i < nbUtilisateurs; i++) {
                System.out.println("    - " + utilisateurs[i] + " [" + utilisateurs[i].getTypeAppareil() + "]");
            }
        }
    }

    @Override
    public String toString() {
        return "BTS#" + numero + " (" + emplacement + ")";
    }
}
