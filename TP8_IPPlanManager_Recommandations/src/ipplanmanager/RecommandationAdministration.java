/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
public class RecommandationAdministration implements RegleRecommandation {
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ADMIN") || nom.contains("ADMINISTRATION")) {
            return new Recommandation("Accès Administration restreint","ÉLEVÉE","Le VLAN " + vlan.getNom() + " doit être accessible uniquement aux administrateurs réseau.");
        }
        return null;
    }
}
