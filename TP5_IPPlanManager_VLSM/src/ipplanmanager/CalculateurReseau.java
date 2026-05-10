/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Faustin
 */
public class CalculateurReseau {
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
        return 0;
    }
    int bitsHotes = 32 - cidr;
    if (bitsHotes == 0) {
        return 1;
    }
    return (int) Math.pow(2, bitsHotes) - 2;
    }
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
        int capacite = calculerNombreHotes(cidr);
            if (capacite >= nombreHotes) {
                return cidr;
            }
        }
        return -1;
    }
    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int octet1 = (masque >>> 24) & 255;
        int octet2 = (masque >>> 16) & 255;
        int octet3 = (masque >>> 8) & 255;
        int octet4 = masque & 255;
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
    public static int convertirIpEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(parties[i]);
        }
        return resultat;
    }
    public static String convertirEntierEnIp(int valeur) {
        int octet1 = (valeur >>> 24) & 255;
        int octet2 = (valeur >>> 16) & 255;
        int octet3 = (valeur >>> 8) & 255;
        int octet4 = valeur & 255;
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }
    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        int valeur = convertirIpEnEntier(adresseReseau);
        return convertirEntierEnIp(valeur + 1);
    }

    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        int valeur = convertirIpEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        return convertirEntierEnIp(valeur + tailleBloc - 2);
    }
}
