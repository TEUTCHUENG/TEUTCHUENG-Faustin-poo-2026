/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
    public class RecommandationWifiInvite implements RegleRecommandation {
@Override
public Recommandation analyser(VLAN vlan) {
if (vlan.getNom().toUpperCase().contains("WIFI")) {
return new Recommandation("Isolation du WiFi","ÉLEVÉE","Le VLAN " + vlan.getNom() + " doit être isolé des VLANs internes sensibles.");
}
return null;
}
}

