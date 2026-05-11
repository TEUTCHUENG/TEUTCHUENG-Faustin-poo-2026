/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager.service;
import ipplanmanager.repository.BesoinRepository;
import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.FichierPlanRepository;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {

    private ConsoleService console;
    private MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation moteurRecommandation;
    private FichierPlanRepository fichierRepository;
    private RapportService rapportService;

    public ApplicationIPPlanManager() {
        console = new ConsoleService();
        moteurVLSM = new MoteurVLSM();
        gestionnaireVLAN = new GestionnaireVLAN();
        validateur = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository = new FichierPlanRepository();
        rapportService = new RapportService();

        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
    }

    public void demarrer() {
    boolean continuer = true;
    while (continuer) {
        console.afficherMenu();
        int choix = console.saisirEntier("Choix : ");
        switch (choix) {
            case 1:
                executerGenerationComplete();
                
                break;
            case 2:
                executerDepuisFichier();
                break;
            case 3:
                System.out.println("Au revoir !");
                continuer = false;
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }
}

    private void executerGenerationComplete() {
        try {
            String nomProjet = console.saisirTexte("Nom du projet réseau : ");
            String adresseDepart = console.saisirTexte("Adresse réseau de départ : ");

            CalculateurReseau.verifierAdresseIP(adresseDepart);

            ArrayList<BesoinReseau> besoins = console.saisirBesoins();

            ArrayList<ResultatVLSM> resultats =
                moteurVLSM.genererPlan(adresseDepart, besoins);

            validateur.verifierChevauchements(resultats);
            validateur.verifierAdresses(resultats);
            validateur.afficherValidationReussie();

            gestionnaireVLAN = new GestionnaireVLAN();
            genererVLANs(resultats);

            ArrayList<Recommandation> recommandations =
                moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());

            afficherResultats(resultats, recommandations);

            sauvegarderResultats(nomProjet, besoins, resultats, recommandations);

        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP : " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur de chevauchement : " + e.getMessage());
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur de fichier : " + e.getMessage());
        }
    }
    private void executerDepuisFichier() {
    try {
        String cheminFichier = console.saisirTexte(
            "Chemin du fichier CSV (ex: exports/besoins.csv) : ");

        BesoinRepository besoinRepository = new BesoinRepository();
        ArrayList<BesoinReseau> besoins =
            besoinRepository.chargerBesoins(cheminFichier);

        if (besoins.isEmpty()) {
            System.out.println("Aucun besoin trouvé dans le fichier.");
            return;
        }

        System.out.println(besoins.size() + " besoin(s) chargé(s) depuis le fichier.");

        String nomProjet = console.saisirTexte("Nom du projet réseau : ");
        String adresseDepart = console.saisirTexte("Adresse réseau de départ : ");

        CalculateurReseau.verifierAdresseIP(adresseDepart);

        ArrayList<ResultatVLSM> resultats =
            moteurVLSM.genererPlan(adresseDepart, besoins);

        validateur.verifierChevauchements(resultats);
        validateur.verifierAdresses(resultats);
        validateur.afficherValidationReussie();

        gestionnaireVLAN = new GestionnaireVLAN();
        genererVLANs(resultats);

        ArrayList<Recommandation> recommandations =
            moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());

        afficherResultats(resultats, recommandations);

        sauvegarderResultats(nomProjet, besoins, resultats, recommandations);

    } catch (AdresseIPInvalideException e) {
        System.out.println("Erreur d'adresse IP : " + e.getMessage());
    } catch (ChevauchementReseauException e) {
        System.out.println("Erreur de chevauchement : " + e.getMessage());
    } catch (ConflitVLANException e) {
        System.out.println("Erreur VLAN : " + e.getMessage());
    } catch (IOException e) {
        System.out.println("Erreur de fichier : " + e.getMessage());
    }
}

    private void genererVLANs(ArrayList<ResultatVLSM> resultats)
    throws ConflitVLANException {
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(
                numeroVLAN,
                resultat.getNomBesoin(),
                resultat,
                "VLAN " + resultat.getNomBesoin()
            );
            gestionnaireVLAN.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
    }

    private void afficherResultats(
            ArrayList<ResultatVLSM> resultats,
            ArrayList<Recommandation> recommandations) {

        System.out.println();
        System.out.println("Plan d'adressage proposé :");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }

        System.out.println();
        System.out.println("VLANs générés :");
        gestionnaireVLAN.afficherTousLesVLANs();

        System.out.println();
        System.out.println("Recommandations proposées :");
        moteurRecommandation.afficherRecommandations(recommandations);
    }

    private void sauvegarderResultats(
            String nomProjet,
            ArrayList<BesoinReseau> besoins,
            ArrayList<ResultatVLSM> resultats,
            ArrayList<Recommandation> recommandations) throws IOException {

        String prefixe = "exports/" + nomProjet.replace(" ", "_");

        fichierRepository.sauvegarderPlanCSV(
            resultats, prefixe + "_plan.csv");
        fichierRepository.sauvegarderVLANsCSV(
            gestionnaireVLAN.getVlans(), prefixe + "_vlans.csv");
        fichierRepository.sauvegarderRecommandations(
            recommandations, prefixe + "_recommandations.txt");
        rapportService.genererRapportComplet(
            besoins, resultats,
            gestionnaireVLAN.getVlans(),
            recommandations,
            prefixe + "_rapport.txt"
        );

        System.out.println();
        System.out.println("Fichiers sauvegardés dans le dossier exports.");
    }
}