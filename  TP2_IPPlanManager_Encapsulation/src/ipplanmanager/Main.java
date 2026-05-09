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

        System.out.println("===== TP2 : Encapsulation =====");

        // --- Adresses IP ---
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");       // cas invalide
        AdresseIP ip3 = new AdresseIP(null);     // cas invalide
        AdresseIP ip4 = new AdresseIP("10.0.0.1");
        AdresseIP ip5 = new AdresseIP("172.16.0.1");

        // --- Interfaces ---
        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau interface2 = new InterfaceReseau("", ip2);   // nom invalide
        InterfaceReseau interface3 = new InterfaceReseau("eth1", ip4);
        InterfaceReseau interface4 = new InterfaceReseau("fa0/0", ip5);

        interface1.activer();
        interface3.activer();

        // --- Equipements ---
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interface1);
        Equipement serveur = new Equipement("", "", interface2);     // cas invalide
        Equipement switch1 = new Equipement("SW1_CORE", "Switch", interface3);
        Equipement firewall = new Equipement("FW1", "Firewall", interface4);

        // --- Réseaux ---
        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Réseau principal");
        ReseauIP reseau2 = new ReseauIP("", 55, "");     // cas invalide
        ReseauIP reseau3 = new ReseauIP("10.0.0.0", 8, "Réseau interne");
        ReseauIP reseau4 = new ReseauIP("172.16.0.0", 16, "Réseau DMZ");

        // --- Modification avec setters ---
        System.out.println("\n--- Modification avec setters ---");
        routeur.setNom("R1_EDGE_MODIFIE");
        reseau2.setAdresseReseau("192.168.2.0");
        reseau2.setMasqueCidr(28);
        reseau2.setDescription("Réseau corrigé");
        ip3.setValeur("10.10.10.10");

        // --- Affichages ---
        System.out.println("\n----- Réseau 1 -----");
        reseau1.afficher();

        System.out.println("\n----- Réseau 2 (corrigé) -----");
        reseau2.afficher();

        System.out.println("\n----- Réseau 3 -----");
        reseau3.afficher();

        System.out.println("\n----- Réseau 4 -----");
        reseau4.afficher();

        System.out.println("\n----- Équipement 1 : Routeur -----");
        routeur.afficher();

        System.out.println("\n----- Équipement 2 : Serveur (invalide) -----");
        serveur.afficher();

        System.out.println("\n----- Équipement 3 : Switch -----");
        switch1.afficher();

        System.out.println("\n----- Équipement 4 : Firewall -----");
        firewall.afficher();
        // --- Test estAdresseLocale() ---
        System.out.println("\n--- Test estAdresseLocale() ---");
        System.out.println(ip1.getValeur() + " est locale ? " + ip1.estAdresseLocale());
        System.out.println(ip4.getValeur() + " est locale ? " + ip4.estAdresseLocale());
        System.out.println(ip5.getValeur() + " est locale ? " + ip5.estAdresseLocale());
    }
}
