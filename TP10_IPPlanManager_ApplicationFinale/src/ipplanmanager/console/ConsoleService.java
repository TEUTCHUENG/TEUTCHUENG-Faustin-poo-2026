/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.console;

import ipplanmanager.model.BesoinReseau;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleService {

    private Scanner scanner;

    public ConsoleService() {
        scanner = new Scanner(System.in);
    }

    public String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide. Entrez un nombre entier.");
            }
        }
    }

    public ArrayList<BesoinReseau> saisirBesoins() {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        int nombreBesoins = saisirEntier("Nombre de besoins réseau à saisir : ");
        for (int i = 1; i <= nombreBesoins; i++) {
            System.out.println();
            System.out.println("Saisie du besoin " + i);
            String nom = saisirTexte("Nom du service ou département : ");
            int hotes = saisirEntier("Nombre d'hôtes demandés : ");
            besoins.add(new BesoinReseau(nom, hotes));
        }
        return besoins;
    }

    public void afficherMenu() {
        System.out.println();
        System.out.println("===== MENU IPPLAN-MANAGER =====");
        System.out.println("1. Saisir les besoins et générer un plan complet");
        System.out.println("2. Charger les besoins depuis un fichier CSV");
        System.out.println("3. Quitter");
    }
}