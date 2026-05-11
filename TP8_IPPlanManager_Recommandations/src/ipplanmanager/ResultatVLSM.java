/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masque;
    private int capacite;
    private int hotesDemandes;
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masque, int capacite) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masque = masque;
        this.capacite = capacite;
        this.hotesDemandes = 0;
    }
    
    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, String masque, int capacite, int hotesDemandes) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masque = masque;
        this.capacite = capacite;
        this.hotesDemandes = hotesDemandes;
    }
    
    public String getNomBesoin() {
        return nomBesoin;
    }
    
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public int getCidr() {
        return cidr;
    }
    
    public String getMasque() {
        return masque;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public int getHotesDemandes() {
        return hotesDemandes;
    }
    
    public void setHotesDemandes(int hotesDemandes) {
        this.hotesDemandes = hotesDemandes;
    }
    
    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr + 
                          " | Masque : " + masque + " | Capacite : " + capacite + " hôtes");
    }
}