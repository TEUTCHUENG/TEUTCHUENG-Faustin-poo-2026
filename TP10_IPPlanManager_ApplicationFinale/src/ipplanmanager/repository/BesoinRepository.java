/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BesoinRepository {

    public ArrayList<BesoinReseau> chargerBesoins(String cheminFichier)
    throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
            new FileReader(cheminFichier)
        );

        // Lire et ignorer la première ligne (en-tête)
        String ligne = reader.readLine();

        // Lire les données ligne par ligne
        while ((ligne = reader.readLine()) != null) {
            String[] colonnes = ligne.split(";");
            if (colonnes.length == 2) {
                String nom = colonnes[0];
                int hotes = Integer.parseInt(colonnes[1]);
                besoins.add(new BesoinReseau(nom, hotes));
            }
        }

        reader.close();
        return besoins;
    }

    public void sauvegarderBesoins(ArrayList<BesoinReseau> besoins,
    String cheminFichier) throws IOException {
        FileWriter writer = new FileWriter(cheminFichier);
        writer.write("Nom;Hotes\n");
        for (BesoinReseau besoin : besoins) {
            writer.write(besoin.getNom() + ";"
                + besoin.getNombreHotes() + "\n");
        }
        writer.close();
    }
}