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
    public static void main(String[] args) throws ReseauInsuffisantException {
        System.out.println("===== IPPlan-Manager : TP8 - Recommandations =====");

        // Scénario 1 : Réseau campus
        System.out.println("\n--- Scénario 1 : Réseau campus ---");
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("ETUDIANTS", 500));
        besoins1.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins1.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins1.add(new BesoinReseau("LABORATOIRES", 60));
        besoins1.add(new BesoinReseau("SERVEURS", 30));

        MoteurVLSM moteurVLSM1 = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats1 = moteurVLSM1.genererPlan("10.10.0.0/22", besoins1);

        GestionnaireVLAN gestionnaireVLAN1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        try {
            for (ResultatVLSM resultat : resultats1) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaireVLAN1.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("Plan VLAN genere :");
        gestionnaireVLAN1.afficherTousLesVLans();

        MoteurRecommandation moteur1 = new MoteurRecommandation();
        moteur1.ajouterRegle(new RecommandationWifiInvite());
        moteur1.ajouterRegle(new RecommandationServeurs());
        moteur1.ajouterRegle(new RecommandationGrandVLAN());
        moteur1.ajouterRegle(new RecommandationAdministration());
        moteur1.ajouterRegle(new RecommandationMargeAdresse());

        ArrayList<Recommandation> recommandations1 = moteur1.analyserVLANs(gestionnaireVLAN1.getVlans());

        System.out.println("\nRecommandations proposees :");
        moteur1.afficherRecommandations(recommandations1);

        // Scénario 2 : Travail demandé section 18
        System.out.println("\n--- Scénario 2 : ADMINISTRATION, WIFI_INVITES, SERVEURS, CAMERAS, VOIP ---");
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 120));
        besoins2.add(new BesoinReseau("SERVEURS", 20));
        besoins2.add(new BesoinReseau("CAMERAS", 80));
        besoins2.add(new BesoinReseau("VOIP", 60));

        MoteurVLSM moteurVLSM2 = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats2 = moteurVLSM2.genererPlan("192.168.1.0/23", besoins2);

        GestionnaireVLAN gestionnaireVLAN2 = new GestionnaireVLAN();
        numeroVLAN = 10;
        try {
            for (ResultatVLSM resultat : resultats2) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaireVLAN2.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("Plan VLAN genere :");
        gestionnaireVLAN2.afficherTousLesVLans();

        MoteurRecommandation moteur2 = new MoteurRecommandation();
        moteur2.ajouterRegle(new RecommandationWifiInvite());
        moteur2.ajouterRegle(new RecommandationServeurs());
        moteur2.ajouterRegle(new RecommandationGrandVLAN());
        moteur2.ajouterRegle(new RecommandationAdministration());
        moteur2.ajouterRegle(new RecommandationMargeAdresse());

        ArrayList<Recommandation> recommandations2 = moteur2.analyserVLANs(gestionnaireVLAN2.getVlans());

        System.out.println("\nRecommandations proposees :");
        moteur2.afficherRecommandations(recommandations2);
    }
}