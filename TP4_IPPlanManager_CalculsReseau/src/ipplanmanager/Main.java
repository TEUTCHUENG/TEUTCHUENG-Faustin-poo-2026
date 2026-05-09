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
        InfrastructureReseau infrastructure =new InfrastructureReseau("Infrastructure YFY");
        ReseauIP reseauAdmin =new ReseauIP("192.168.1.0",24,"Réseau Administration");
        ReseauIP reseauTechnique = new ReseauIP("172.16.0.0",16,"Réseau Technique");
        ReseauIP reseauWifi =new ReseauIP("10.0.0.0",8,"Réseau WiFi");
        SousReseau admin =new SousReseau("ADMIN",reseauAdmin);
        SousReseau tech =new SousReseau("TECH",reseauTechnique);
        SousReseau wifi =new SousReseau("WIFI",reseauWifi);
        
           // Réseaux supplémentaires avec différents CIDR
        ReseauIP reseau1 = new ReseauIP("192.168.10.0",25,"Réseau Comptabilité");
        ReseauIP reseau2 = new ReseauIP("192.168.20.0",26,"Réseau Direction");

        ReseauIP reseau3 = new ReseauIP("192.168.30.0",27,"Réseau Sécurité");

        SousReseau compta   = new SousReseau("COMPTA", reseau1);
        SousReseau direction = new SousReseau("DIRECTION", reseau2);
        SousReseau securite  = new SousReseau("SECURITE", reseau3);
        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        infrastructure.ajouterSousReseau(compta);
        infrastructure.ajouterSousReseau(direction);
        infrastructure.ajouterSousReseau(securite);
        infrastructure.afficher();
        
        // =====test estReseauPrive =====
    System.out.println("===== TEST RESEAU PRIVE =====");
    System.out.println("192.168.1.0 prive ? " + CalculateurReseau.estReseauPrive("192.168.1.0"));
    System.out.println("172.16.0.0 prive ? "  + CalculateurReseau.estReseauPrive("172.16.0.0"));
    System.out.println("10.0.0.0 prive ? "    + CalculateurReseau.estReseauPrive("10.0.0.0"));
    System.out.println("8.8.8.8 prive ? "     + CalculateurReseau.estReseauPrive("8.8.8.8"));

    }
}
