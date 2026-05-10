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
        System.out.println("===== IPPlan-Manager : TP5 - Moteur VLSM=====");
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        besoins.add(new BesoinReseau("DIRECTION", 10));
        System.out.println();
        System.out.println("Besoins exprimés par l'utilisateur :");
        for (BesoinReseau besoin : besoins) {
            besoin.afficher();
        }
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats =
        moteur.genererPlan("192.168.1.0", besoins);
        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }
        // ===== SCENARIO 2 : Petite entreprise =====
        System.out.println();
        System.out.println("===== SCENARIO 2 : Petite entreprise =====");
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMIN", 25));
        besoins2.add(new BesoinReseau("COMPTABILITE", 12));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 40));
        besoins2.add(new BesoinReseau("SERVEURS", 8));

        System.out.println("Besoins exprimes :");
        for (BesoinReseau besoin : besoins2) {
            besoin.afficher();
        }

        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("10.0.0.0", besoins2);
        System.out.println("Plan d'adressage propose :");
        for (ResultatVLSM resultat : resultats2) {
            resultat.afficher();
        }

        // ===== SCENARIO 3 : Campus =====
        System.out.println();
        System.out.println("===== SCENARIO 3 : Campus =====");
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("ETUDIANTS", 500));
        besoins3.add(new BesoinReseau("PERSONNEL", 120));
        besoins3.add(new BesoinReseau("LABORATOIRE", 60));
        besoins3.add(new BesoinReseau("ADMINISTRATION", 40));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC", 200));

        System.out.println("Besoins exprimes :");
        for (BesoinReseau besoin : besoins3) {
            besoin.afficher();
        }

        ArrayList<ResultatVLSM> resultats3 = moteur.genererPlan("172.16.0.0", besoins3);
        System.out.println("Plan d'adressage propose :");
        for (ResultatVLSM resultat : resultats3) {
         resultat.afficher();
        }
    }
}
