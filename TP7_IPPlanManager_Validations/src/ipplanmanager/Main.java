/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;
import java.util.ArrayList;
/**
 *
 * @author Faustin
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP7 - Validations avancees =====");

        System.out.println("\n--- Test 1 : Plan d'adressage normal ---");
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));

        MoteurVLSM moteur = new MoteurVLSM();
        try {
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0/23", besoins);

            System.out.println("Plan genere :");
            for (ResultatVLSM resultat : resultats) {
                resultat.afficher();
            }

            ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();

        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur de capacite : " + e.getMessage());
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }

        System.out.println("\n--- Test 2 : Conflit VLAN ---");
        try {
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0/23", besoins);

            GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
            VLAN vlan10 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
            VLAN vlan20 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
            VLAN vlan10Erreur = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi avec ID deja utilise");

            gestionnaire.ajouterVLAN(vlan10);
            gestionnaire.ajouterVLAN(vlan20);
            gestionnaire.afficherTousLesVLans();

            System.out.println("\nTentative d'ajout d'un VLAN avec ID deja utilise...");
            gestionnaire.ajouterVLAN(vlan10Erreur);

        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n--- Test 3 : Adresse IP invalide (192.168.300.0) ---");
        try {
            ArrayList<BesoinReseau> besoinsTestInvalide = new ArrayList<>();
            besoinsTestInvalide.add(new BesoinReseau("TEST_INVALIDE", 10));

            MoteurVLSM moteurTestInvalide = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultatsInvalide = moteurTestInvalide.genererPlan("192.168.300.0/24", besoinsTestInvalide);

            ValidateurPlanAdressage validateurInvalide = new ValidateurPlanAdressage();
            validateurInvalide.verifierAdresses(resultatsInvalide);
            validateurInvalide.afficherValidationReussie();

        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP detectee : " + e.getMessage());
        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n--- Test 4 : Chevauchement de reseaux ---");
        try {
            ArrayList<ResultatVLSM> resultatsChevauchement = new ArrayList<>();
            resultatsChevauchement.add(new ResultatVLSM("RESEAU_A", "192.168.1.0", 25, "255.255.255.128", 126));
            resultatsChevauchement.add(new ResultatVLSM("RESEAU_B", "192.168.1.64", 26, "255.255.255.192", 62));

            ValidateurPlanAdressage validateurChevauchement = new ValidateurPlanAdressage();
            validateurChevauchement.verifierChevauchements(resultatsChevauchement);
            validateurChevauchement.afficherValidationReussie();

        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de chevauchement detectee : " + e.getMessage());
        }

        System.out.println("\n--- Test 5 : Reseau insuffisant ---");
        try {
            ArrayList<BesoinReseau> besoinsTropLourds = new ArrayList<>();
            besoinsTropLourds.add(new BesoinReseau("SERVICE_A", 200));
            besoinsTropLourds.add(new BesoinReseau("SERVICE_B", 100));
            besoinsTropLourds.add(new BesoinReseau("SERVICE_C", 50));

            MoteurVLSM moteurTest5 = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultatsTest5 = moteurTest5.genererPlan("192.168.1.0/25", besoinsTropLourds);

            for (ResultatVLSM r : resultatsTest5) {
                r.afficher();
            }
        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}