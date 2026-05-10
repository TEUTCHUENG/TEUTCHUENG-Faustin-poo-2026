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
        System.out.println("===== IPPlan-Manager : TP6 - VLANs =====");
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats =
        moteur.genererPlan("192.168.1.0", besoins);
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(
            numeroVLAN,
            resultat.getNomBesoin(),
            resultat,"VLAN du service " + resultat.getNomBesoin());
            gestionnaire.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
        System.out.println();
        System.out.println("===== VLANS GÉNÉRÉS =====");
        gestionnaire.afficherTousLesVLANs();
        System.out.println();
        System.out.println("===== TEST DE RECHERCHE VLAN =====");
        VLAN vlanRecherche =
        gestionnaire.rechercherVLAN(20);
        if (vlanRecherche != null) {
            vlanRecherche.afficher();
        } else {
            System.out.println("VLAN introuvable.");
        }
            // ===== SECTION 15 : Scénario Université =====
    System.out.println();
    System.out.println("===== SCENARIO UNIVERSITE =====");

    ArrayList<BesoinReseau> besoinsUniv = new ArrayList<>();
    besoinsUniv.add(new BesoinReseau("ETUDIANTS", 500));
    besoinsUniv.add(new BesoinReseau("ENSEIGNANTS", 120));
    besoinsUniv.add(new BesoinReseau("LABORATOIRES", 60));
    besoinsUniv.add(new BesoinReseau("WIFI_PUBLIC", 200));
    besoinsUniv.add(new BesoinReseau("SERVEURS", 30));

    ArrayList<ResultatVLSM> resultatsUniv =moteur.genererPlan("172.16.0.0", besoinsUniv);

    GestionnaireVLAN gestionnaireUniv = new GestionnaireVLAN();
    int numeroVLANUniv = 10;
    for (ResultatVLSM resultat : resultatsUniv) {
        VLAN vlan = new VLAN(numeroVLANUniv,resultat.getNomBesoin(),resultat, "VLAN du service " + resultat.getNomBesoin());
        gestionnaireUniv.ajouterVLAN(vlan);
        numeroVLANUniv += 10;
    }

    System.out.println("Nombre total de VLANs : " +gestionnaireUniv.obtenirNombreVLANs());
    System.out.println();
    System.out.println("===== VLANS GENERES =====");
    gestionnaireUniv.afficherTousLesVLANs();

    System.out.println("===== VLANS CRITIQUES (plus de 100 hotes) =====");
    gestionnaireUniv.afficherVLANsCritiques();
    }
}

