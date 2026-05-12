package gsm;

// Classe de test : elle teste toutes les autres classes
// C'est ici qu'on lance le programme (methode main)
public class TestReseau {

    public static void main(String[] args) {

        System.out.println("   PROJET 2 POO - Simulation Reseau GSM");

        try {

            // -----------------------------------------------
            // ETAPE 1 : Creer le reseau GSM
            // -----------------------------------------------
            System.out.println("\n--- Etape 1 : Creation du reseau ---");

            Reseau reseau = new Reseau("GSM-4G",1710.0,1805.0,"TDMA",50.0,150.0,10.0);
          
            // ETAPE 2 : Creer 3 BTS et les ajouter au reseau
            System.out.println("\n--- Etape 2 : Creation des BTS ---");

            // BTS(numero, emplacement, hauteur, milieu, rayon, puissance, nbMaxMS, nbCellules)
            BTS bts1 = new BTS(1, "pete-bandjoun", 30.0, "urbain", 1.5, 20.0, 3, 3);
            BTS bts2 = new BTS(2, "BAHAM-Centre",   25.0, "urbain", 1.2, 15.0, 2, 2);
            BTS bts3 = new BTS(3, "BAFOUSSAM",  20.0, "rural",  5.0, 10.0, 2, 1);

            reseau.ajouterBTS(bts1);
            reseau.ajouterBTS(bts2);
            reseau.ajouterBTS(bts3);

            // ETAPE 3 : Creer des utilisateurs (MS)

            System.out.println("\n--- Etape 3 : Creation des utilisateurs ---");

            // Deux smartphones (sous-classe de MS)
            Smartphone ms1 = new Smartphone("teutchueng",  "faustin", "pass1","0551111111", "603010000001","Samsung", "Android");

            Smartphone ms2 = new Smartphone("kako", "ismael",  "pass2","0552222222", "603010000002","Apple", "iOS");

            // Une tablette (sous-classe de MS)
            Tablette ms3 = new Tablette("faustin", "bienvenu", "pass3","0553333333", "603010000003",10.1);

            // Un simple MS (classe de base)
            MS ms4 = new MS("diane", "ismael", "pass4", "0554444444", "603010000004");

            // ETAPE 4 : Attacher les MS aux BTS
            System.out.println("\n--- Etape 4 : Attachement des MS aux BTS ---");

            // On verifie d'abord que la BTS n'est pas saturee avant d'ajouter
            if (ms1.peutSAttacher(bts1)) bts1.ajouterMS(ms1);
            if (ms2.peutSAttacher(bts1)) bts1.ajouterMS(ms2);
            if (ms3.peutSAttacher(bts1)) bts1.ajouterMS(ms3);
            if (ms4.peutSAttacher(bts2)) bts2.ajouterMS(ms4);

            // ETAPE 5 : Afficher les infos (interface Affichable)
            System.out.println("\n--- Etape 5 : Affichage des utilisateurs ---");
            ms1.afficher();
            ms2.afficher();
            ms3.afficher();
            ms4.afficher();

            System.out.println("\n--- Affichage des BTS ---");
            bts1.afficher();
            bts2.afficher();
            bts3.afficher();

            // ETAPE 6 : Polymorphisme
            //           On appelle getTypeAppareil() sur des MS differents
            //           Chaque type retourne une reponse differente
            System.out.println("\n--- Etape 6 : Polymorphisme ---");
            MS[] tousLesMS = { ms1, ms2, ms3, ms4 };
            for (MS ms : tousLesMS) {
                System.out.println("  " + ms + " --> Type : " + ms.getTypeAppareil());
            }

            // ETAPE 7 : Simulation d'appels entre MS
            System.out.println("\n--- Etape 7 : Appels ---");
            ms1.appeler(ms2); // ms1 appelle ms2
            ms3.appeler(ms2); // ms3 appelle ms2
            ms4.appeler(ms1); // ms4 appelle ms1

            System.out.println();
            ms1.afficherAppelsRecus();
            ms2.afficherAppelsRecus();

            // ETAPE 8 : Statistiques du reseau
            System.out.println("\n--- Etape 8 : Statistiques reseau ---");
            reseau.afficher();
            System.out.println();
            reseau.afficherPerformances();

            System.out.println("\n  BTS urbaines : " + reseau.calculerNbBTS("urbain"));
            System.out.println("  BTS rurales  : " + reseau.calculerNbBTS("rural"));
            System.out.println("  Total abonnes: " + reseau.calculerNbAbonnes());

            // ETAPE 9 : Localisation d'un utilisateur
            System.out.println("\n--- Etape 9 : Localisation ---");

            String[] numeros = { ms1.getMsisdn(), ms4.getMsisdn(), "0599999999" };
            for (String num : numeros) {
                BTS trouvee = reseau.localiserUtilisateur(num);
                if (trouvee != null) {
                    System.out.println("  " + num + " --> " + trouvee);
                } else {
                    System.out.println("  " + num + " --> Non trouve dans le reseau.");
                }
            }
            // ETAPE 10 : Suppression d'un MS
            System.out.println("\n--- Etape 10 : Suppression MS ---");
            bts1.supprimerMS(ms2.getMsisdn());
            bts1.afficher();

            // ETAPE 11 : Test des exceptions (gestion des erreurs)
            System.out.println("\n--- Etape 11 : Test des exceptions ---");

            // Test 1 : BTS saturee (bts1 a max 3, elle en a deja 2 apres suppression de ms2)
            //          On remplit bts1 puis on essaie d'en ajouter un de plus
            Smartphone msExtra = new Smartphone("teutchueng", "faustin", "xxx",
                                                 "0599000001", "603010999001",
                                                 "Huawei", "android");
            bts1.ajouterMS(msExtra); // OK, ca passe (3/3)

            try {
                Smartphone msOverflow = new Smartphone("sarah", "nono", "yyy",
                                                        "0599000002", "603010999002",
                                                        "Xiaomi", "Android");
                bts1.ajouterMS(msOverflow); // Doit lever une exception
            } catch (ReseauException e) {
                System.out.println("  [EXCEPTION] " + e.getMessage());
            }

            // Test 2 : Supprimer un MS inexistant
            try {
                bts2.supprimerMS("0000000000");
            } catch (ReseauException e) {
                System.out.println("  [EXCEPTION] " + e.getMessage());
            }

            // Test 3 : Appeler un MS null
            try {
                ms1.appeler(null);
            } catch (ReseauException e) {
                System.out.println("  [EXCEPTION] " + e.getMessage());
            }

            // Test 4 : Supprimer une BTS inexistante
            try {
                reseau.supprimerBTS(99);
            } catch (ReseauException e) {
                System.out.println("  [EXCEPTION] " + e.getMessage());
            }
            System.out.println("   Fin des tests - Programme termine avec succes");

        } catch (ReseauException e) {
            // Si une erreur inattendue se produit, on l'affiche ici
            System.out.println("[ERREUR] " + e.getMessage());
        }
    }
}
