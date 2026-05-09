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

        InfrastructureReseau infrastructure =
                new InfrastructureReseau("Infrastructure YFY");

        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Réseau administration");
        ReseauIP reseauTech  = new ReseauIP("192.168.2.0", 24, "Réseau technique");

        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech  = new SousReseau("TECH",  reseauTech);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);

        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");

        InterfaceReseau eth0 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau eth1 = new InterfaceReseau("eth1", ip2);

        eth0.activer();
        eth1.activer();

        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(eth0);
        routeur.ajouterInterface(eth1);

        infrastructure.ajouterEquipement(routeur);

        infrastructure.afficher();

        // ===== SECTION 14 : travail demandé =====

        // Switch
        AdresseIP ip3 = new AdresseIP("192.168.1.2");
        InterfaceReseau eth2 = new InterfaceReseau("eth0", ip3);
        eth2.activer();
        Equipement sw = new Equipement("SW_PRINCIPAL", "Switch");
        sw.ajouterInterface(eth2);

        // Serveur
        AdresseIP ip4 = new AdresseIP("192.168.1.10");
        InterfaceReseau eth3 = new InterfaceReseau("eth0", ip4);
        eth3.activer();
        Equipement serveur = new Equipement("SRV_DNS", "Serveur");
        serveur.ajouterInterface(eth3);

        // Troisième sous-réseau WiFi
        ReseauIP reseauWifi = new ReseauIP("192.168.3.0", 24, "Réseau WiFi");
        SousReseau wifi = new SousReseau("WIFI", reseauWifi);
        infrastructure.ajouterSousReseau(wifi);

        // Point d'accès WiFi
        AdresseIP ip5 = new AdresseIP("192.168.3.1");
        InterfaceReseau wlan0 = new InterfaceReseau("wlan0", ip5);
        wlan0.activer();
        Equipement apWifi = new Equipement("AP_WIFI", "Point d'accès WiFi");
        apWifi.ajouterInterface(wlan0);

        // Poste client supplémentaire avec interface inactive
        AdresseIP ip6 = new AdresseIP("192.168.1.50");
        InterfaceReseau eth4 = new InterfaceReseau("eth0", ip6);
        Equipement client = new Equipement("PC_USER1", "Poste client");
        client.ajouterInterface(eth4);

        // Ajout dans l'infrastructure
        infrastructure.ajouterEquipement(sw);
        infrastructure.ajouterEquipement(serveur);
        infrastructure.ajouterEquipement(apWifi);
        infrastructure.ajouterEquipement(client);

        // ===== SECTION 15 : recherche =====
        System.out.println("===== RECHERCHE =====");
        infrastructure.rechercherEquipement("SRV_DNS");
        System.out.println();
        infrastructure.rechercherEquipement("INCONNU");
    }
}