/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
public class RecommandationGrandVLAN implements RegleRecommandation {
@Override
public Recommandation analyser(VLAN vlan) {
if (vlan.getReseauAssocie() != null &&
vlan.getReseauAssocie().getCapacite() > 200) {
return new Recommandation("VLAN de grande taille","MOYENNE","Le VLAN " + vlan.getNom() + " possède une grandecapacité. Il faut surveiller les broadcasts.");
}
return null;
}
}