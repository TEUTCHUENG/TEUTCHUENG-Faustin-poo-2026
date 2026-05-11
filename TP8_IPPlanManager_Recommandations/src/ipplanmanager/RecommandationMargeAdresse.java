/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */

public class RecommandationMargeAdresse implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        ResultatVLSM reseau = vlan.getReseauAssocie();
        if (reseau != null && reseau.getHotesDemandes() > 0) {
            int hotesDemandes = reseau.getHotesDemandes();
            int capacite = reseau.getCapacite();
            int marge = capacite - hotesDemandes;
            
            if (marge < 10) {
                return new Recommandation("Marge d'adresses insuffisante","MOYENNE","Le VLAN " + vlan.getNom() + " a une marge de seulement " + marge + " hôtes (" + hotesDemandes + " demandés, " + capacite + " disponibles). " +"Prévoir une marge plus confortable si le réseau est susceptible d'évoluer.");
            }
        }
        return null;
    }
}

