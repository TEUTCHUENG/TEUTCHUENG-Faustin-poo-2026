package gsm;

// Classe Reseau = le reseau GSM complet
// Elle contient toutes les BTS du reseau
public class Reseau implements Affichable {

    // --- Attributs du reseau ---
    private String nom;
    private double bandUplink;       // frequence montante en MHz
    private double bandDownlink;     // frequence descendante en MHz
    private String typeAcces;        // ex: TDMA, FDMA, CDMA
    private double debitMaxUplink;   // en Mbps
    private double debitMaxDownlink; // en Mbps
    private double maxDelai;         // delai maximum en ms

    // Tableau des BTS du reseau (max 20 BTS)
    private BTS[] tableauBTS;
    private int   nbBTS;

    // --- Constructeur ---
    public Reseau(String nom, double bandUplink, double bandDownlink,
                  String typeAcces, double debitMaxUplink, double debitMaxDownlink, double maxDelai) {
        this.nom             = nom;
        this.bandUplink      = bandUplink;
        this.bandDownlink    = bandDownlink;
        this.typeAcces       = typeAcces;
        this.debitMaxUplink  = debitMaxUplink;
        this.debitMaxDownlink = debitMaxDownlink;
        this.maxDelai        = maxDelai;
        this.tableauBTS      = new BTS[20];
        this.nbBTS           = 0;
    }

    // --- Getter ---
    public String getNom()  { return nom; }
    public int    getNbBTS(){ return nbBTS; }

    // Ajoute une BTS au reseau
    public void ajouterBTS(BTS bts) throws ReseauException {
        if (nbBTS >= 20) {
            throw new ReseauException("Le reseau est plein, impossible d'ajouter une BTS.");
        }
        if (bts == null) {
            throw new ReseauException("Erreur : la BTS est null.");
        }
        tableauBTS[nbBTS] = bts;
        nbBTS++;
        System.out.println("  [OK] " + bts + " ajoutee au reseau.");
    }

    // Supprime une BTS par son numero
    public void supprimerBTS(int numero) throws ReseauException {
        for (int i = 0; i < nbBTS; i++) {
            if (tableauBTS[i].getNumero() == numero) {
                System.out.println("  [OK] " + tableauBTS[i] + " supprimee.");
                for (int j = i; j < nbBTS - 1; j++) {
                    tableauBTS[j] = tableauBTS[j + 1];
                }
                tableauBTS[nbBTS - 1] = null;
                nbBTS--;
                return;
            }
        }
        throw new ReseauException("BTS numero " + numero + " introuvable.");
    }

    // Recherche une BTS par son numero
    public BTS rechercherBTS(int numero) {
        for (int i = 0; i < nbBTS; i++) {
            if (tableauBTS[i].getNumero() == numero) {
                return tableauBTS[i];
            }
        }
        return null;
    }

    // Compte le nombre de BTS selon le type de milieu ("urbain" ou "rural")
    public int calculerNbBTS(String typeMilieu) {
        int count = 0;
        for (int i = 0; i < nbBTS; i++) {
            if (tableauBTS[i].getTypeMilieu().equalsIgnoreCase(typeMilieu)) {
                count++;
            }
        }
        return count;
    }

    // Calcule le nombre total d'abonnes dans tout le reseau
    public int calculerNbAbonnes() {
        int total = 0;
        for (int i = 0; i < nbBTS; i++) {
            total += tableauBTS[i].getNbUtilisateurs();
        }
        return total;
    }

    // Cherche dans quelle BTS se trouve un utilisateur (par son MSISDN)
    public BTS localiserUtilisateur(String msisdn) {
        for (int i = 0; i < nbBTS; i++) {
            if (tableauBTS[i].rechercherMS(msisdn) != null) {
                return tableauBTS[i];
            }
        }
        return null; // utilisateur non trouve
    }

    // Affiche les performances du reseau
    public void afficherPerformances() {
        System.out.println("=== Performances du reseau " + nom + " ===");
        System.out.println("  Debit max Uplink   : " + debitMaxUplink + " Mbps");
        System.out.println("  Debit max Downlink : " + debitMaxDownlink + " Mbps");
        System.out.println("  Delai max          : " + maxDelai + " ms");
        System.out.println("  Nombre de BTS      : " + nbBTS);
        System.out.println("  Nombre d'abonnes   : " + calculerNbAbonnes());
    }

    // Methode de l'interface Affichable
    @Override
    public void afficher() {
        System.out.println("=== Reseau : " + nom + " ===");
        System.out.println("  Bande Uplink   : " + bandUplink + " MHz");
        System.out.println("  Bande Downlink : " + bandDownlink + " MHz");
        System.out.println("  Type d'acces   : " + typeAcces);
        System.out.println("  Nb BTS         : " + nbBTS);
        System.out.println("  Nb abonnes     : " + calculerNbAbonnes());
        System.out.println("  Liste des BTS  :");
        for (int i = 0; i < nbBTS; i++) {
            System.out.println("    - " + tableauBTS[i]);
        }
    }

    @Override
    public String toString() {
        return "Reseau[" + nom + "]";
    }
}
