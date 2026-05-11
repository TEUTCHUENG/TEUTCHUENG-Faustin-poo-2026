/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.exception.ReseauInsuffisantException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart,
    ArrayList<BesoinReseau> besoins) throws ReseauInsuffisantException {

        String ipDepart = adresseDepart;
        if (adresseDepart.contains("/")) {
            String[] parties = adresseDepart.split("/");
            ipDepart = parties[0];
        }

        verifierCapaciteReseau(adresseDepart, besoins);

        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        int adresseCourante = CalculateurReseau.convertirIpEnEntier(ipDepart);

        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            ResultatVLSM resultat = new ResultatVLSM(
                besoin.getNom(), adresseReseau, cidr, masque, capacite, 
                besoin.getNombreHotes());
            resultats.add(resultat);
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }
        return resultats;
    }

    private void verifierCapaciteReseau(String adresseDepart,
    ArrayList<BesoinReseau> besoins) throws ReseauInsuffisantException {
        int cidrDepart = 24;
        if (adresseDepart.contains("/")) {
            String[] parties = adresseDepart.split("/");
            cidrDepart = Integer.parseInt(parties[1]);
        }

        int tailleBlocDepart = CalculateurReseau.calculerTailleBloc(cidrDepart);
        int totalAdressesNecessaires = 0;

        for (BesoinReseau besoin : besoins) {
            int cidrOptimal = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidrOptimal);
            totalAdressesNecessaires += tailleBloc;
        }

        if (totalAdressesNecessaires > tailleBlocDepart) {
            int capaciteHotes = CalculateurReseau.calculerNombreHotes(cidrDepart);
            throw new ReseauInsuffisantException(
                "Reseau insuffisant : le reseau " + adresseDepart +
                " peut contenir " + capaciteHotes + " hôtes, " +
                "mais les besoins necessitent " + totalAdressesNecessaires + " adresses."
            );
        }
    }
}