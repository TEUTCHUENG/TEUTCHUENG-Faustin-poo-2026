/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

/**
 *
 * @author Faustin
 */
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {

    public void genererRapportComplet(
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<VLAN> vlans,
            ArrayList<Recommandation> recommandations,
            String cheminFichier) throws IOException {

        FileWriter writer = new FileWriter(cheminFichier);

        writer.write("===== RAPPORT TECHNIQUE IPPLAN-MANAGER =====\n\n");

        writer.write("1. Besoins exprimes par l'utilisateur\n");
        for (BesoinReseau besoin : besoins) {
            writer.write("- " + besoin.getNom() + " : " 
                + besoin.getNombreHotes() + " hotes\n");
        }

        writer.write("\n2. Plan d'adressage propose\n");
        for (ResultatVLSM resultat : resultats) {
            writer.write("- " + resultat.getNomBesoin()
                + " : " + resultat.getAdresseReseau()
                + "/" + resultat.getCidr()
                + " | Capacite : " + resultat.getCapacite()
                + " hotes\n");
        }

        writer.write("\n3. VLANs proposes\n");
        for (VLAN vlan : vlans) {
            writer.write("- VLAN " + vlan.getId()
                + " | " + vlan.getNom());
            if (vlan.getReseauAssocie() != null) {
                writer.write(" | " + vlan.getReseauAssocie().getAdresseReseau()
                    + "/" + vlan.getReseauAssocie().getCidr());
            }
            writer.write("\n");
        }

        writer.write("\n4. Recommandations techniques\n");
        if (recommandations.isEmpty()) {
            writer.write("Aucune recommandation particuliere.\n");
        } else {
            for (Recommandation recommandation : recommandations) {
                writer.write("- [" + recommandation.getPriorite() + "] "
                    + recommandation.getTitre()
                    + " : " + recommandation.getMessage() + "\n");
            }
        }

        writer.close();
    }
}