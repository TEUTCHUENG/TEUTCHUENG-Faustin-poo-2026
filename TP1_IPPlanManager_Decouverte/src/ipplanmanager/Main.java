/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Découverte des premières classes du projet");
        System.out.println();
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient = new AdresseIP("192.168.1.50");
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0",ipServeur);
        InterfaceReseau interfaceClient = new InterfaceReseau("wlan0",ipClient);
        interfaceRouteur.activer();
        interfaceServeur.activer();
        Equipement routeur = new Equipement("R1_EDGE", "Routeur",interfaceRouteur);
        Equipement serveur = new Equipement("SRV_DNS", "Serveur",interfaceServeur);
        Equipement client = new Equipement("PC_ADMIN", "Poste client", interfaceClient);
        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0",24,"Réseau principal du laboratoire IRT");
        System.out.println("----- Réseau créé -----");
        reseauPrincipal.afficher();
        System.out.println();
        System.out.println("----- Équipements créés -----");
        System.out.println();
        routeur.afficher();
        System.out.println();
        serveur.afficher();
        System.out.println();
        client.afficher();
        
        
// Deuxieme réseau
        ReseauIP deuxiemereseaux = new ReseauIP("172.168.5.0",16,"Reseau secondaire de gestion");
// Interface sans adresse IP
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null);
// Interface inactive
        InterfaceReseau interfaceinactive = new InterfaceReseau("eth0", new AdresseIP("172.168.5.254"));
        interfaceinactive.desactiver();
// Switch
        Equipement swi = new Equipement("SWITCH1", "Switch", interfaceinactive);
// Point d'acces WiFi
        InterfaceReseau interfaceWifi = new InterfaceReseau("wlan0", new AdresseIP("172.168.5.200"));
        interfaceWifi.activer();
        Equipement wifi = new Equipement("WIFI", "Point d'acces WiFi", interfaceWifi);
// Poste client supplementaire
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth1", null);
        Equipement client2 = new Equipement("PC_client1", "Poste client", interfaceClient2);
// Affichages
        System.out.println("----- 2e reseau -----");
        deuxiemereseaux.afficher();
        System.out.println();
        System.out.println("----- Nouveaux equipements -----");
        System.out.println();
        swi.afficher();
        System.out.println();
        wifi.afficher();
        System.out.println();
        client2.afficher();
}

}
