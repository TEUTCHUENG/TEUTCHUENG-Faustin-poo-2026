# TP5 - Moteur VLSM

## Objectif
Développer un moteur VLSM capable de proposer automatiquement
un plan d'adressage à partir des besoins exprimés.

## Notions étudiées
- VLSM
- Tri de collections
- Classe de service métier
- Calcul CIDR
- Conversion IP-entier
- Génération automatique de sous-réseaux

## Scénarios testés
- Scénario 1 : Entreprise standard (192.168.1.0)
  TECHNIQUE 120, WIFI 80, ADMINISTRATION 50, SERVEURS 20, DIRECTION 10
- Scénario 2 : Petite entreprise (10.0.0.0)
  ADMIN 25, COMPTABILITE 12, WIFI_INVITES 40, SERVEURS 8
- Scénario 3 : Campus (172.16.0.0)
  ETUDIANTS 500, PERSONNEL 120, LABORATOIRE 60, ADMINISTRATION 40, WIFI_PUBLIC 200

## Résultats obtenus
- Scénario 1 : TECHNIQUE /25, WIFI /25, ADMINISTRATION /26, SERVEURS /27, DIRECTION /28
- Scénario 2 : WIFI_INVITES /26, ADMIN /27, COMPTABILITE /28, SERVEURS /28
- Scénario 3 : ETUDIANTS /23, WIFI_PUBLIC /24, PERSONNEL /25, LABORATOIRE /26, ADMINISTRATION /26

## Difficultés rencontrées
- Comprendre la conversion d'une adresse IP en entier
- Comprendre le tri décroissant avec Collections.sort()
- Comprendre le calcul de la taille de bloc

## Réponses aux questions

1. Le VLSM attribue à chaque réseau exactement la taille dont
   il a besoin, évitant ainsi de gaspiller des adresses inutilement.

2. Si on commence par les petits besoins, l'espace restant
   peut être fragmenté et ne plus pouvoir accueillir les grands.

3. Un besoin est ce que l'utilisateur demande (ex: 50 hôtes).
   Un résultat VLSM est ce que l'application calcule et propose
   (ex: 192.168.2.0/26).

4. MoteurVLSM ne stocke pas de données. Elle applique une
   logique métier : trier, calculer, générer des résultats.

5. Les opérations sur les adresses IP (addition, avancement)
   sont impossibles sur des chaînes de caractères. On les
   convertit en entiers pour faire des calculs mathématiques.

6. calculerCidrPourHotes() trouve automatiquement le plus
   petit masque capable de contenir le nombre d'hôtes demandé.

7. L'adresse réseau identifie le réseau lui-même.
   L'adresse broadcast permet d'envoyer à toutes les machines.
   Ces deux adresses sont réservées et non attribuables.

8. Le moteur VLSM transforme IPPlan-Manager en un vrai outil
   de planification réseau automatique, utile en entreprise.
## Résultat du test
===== IPPlan-Manager : TP5 - Moteur VLSM=====

Besoins exprim�s par l'utilisateur :
Besoin : TECHNIQUE | H�tes demand�s : 120
Besoin : WIFI | H�tes demand�s : 80
Besoin : ADMINISTRATION | H�tes demand�s : 50
Besoin : SERVEURS | H�tes demand�s : 20
Besoin : DIRECTION | H�tes demand�s : 10

Plan d'adressage propos� :
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes
ADMINISTRATION -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes
SERVEURS -> 192.168.2.64/27 | Plage : 192.168.2.65 - 192.168.2.94 | Capacite : 30 hotes
DIRECTION -> 192.168.2.96/28 | Plage : 192.168.2.97 - 192.168.2.110 | Capacite : 14 hotes

===== SCENARIO 2 : Petite entreprise =====
Besoins exprimes :
Besoin : ADMIN | H�tes demand�s : 25
Besoin : COMPTABILITE | H�tes demand�s : 12
Besoin : WIFI_INVITES | H�tes demand�s : 40
Besoin : SERVEURS | H�tes demand�s : 8
Plan d'adressage propose :
WIFI_INVITES -> 10.0.0.0/26 | Plage : 10.0.0.1 - 10.0.0.62 | Capacite : 62 hotes
ADMIN -> 10.0.0.64/27 | Plage : 10.0.0.65 - 10.0.0.94 | Capacite : 30 hotes
COMPTABILITE -> 10.0.0.96/28 | Plage : 10.0.0.97 - 10.0.0.110 | Capacite : 14 hotes
SERVEURS -> 10.0.0.112/28 | Plage : 10.0.0.113 - 10.0.0.126 | Capacite : 14 hotes

===== SCENARIO 3 : Campus =====
Besoins exprimes :
Besoin : ETUDIANTS | H�tes demand�s : 500
Besoin : PERSONNEL | H�tes demand�s : 120
Besoin : LABORATOIRE | H�tes demand�s : 60
Besoin : ADMINISTRATION | H�tes demand�s : 40
Besoin : WIFI_PUBLIC | H�tes demand�s : 200
Plan d'adressage propose :
ETUDIANTS -> 172.16.0.0/23 | Plage : 172.16.0.1 - 172.16.1.254 | Capacite : 510 hotes
WIFI_PUBLIC -> 172.16.2.0/24 | Plage : 172.16.2.1 - 172.16.2.254 | Capacite : 254 hotes
PERSONNEL -> 172.16.3.0/25 | Plage : 172.16.3.1 - 172.16.3.126 | Capacite : 126 hotes
LABORATOIRE -> 172.16.3.128/26 | Plage : 172.16.3.129 - 172.16.3.190 | Capacite : 62 hotes
ADMINISTRATION -> 172.16.3.192/26 | Plage : 172.16.3.193 - 172.16.3.254 | Capacite : 62 