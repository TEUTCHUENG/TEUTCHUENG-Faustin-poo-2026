# TP6 - VLAN et segmentation logique

## Objectif
Mettre en place la gestion des VLANs et associer automatiquement
les sous-réseaux générés aux VLANs.

## Notions étudiées
- Segmentation logique
- VLAN
- Gestionnaires métier
- Collections
- Associations entre objets
- Architecture métier

## Scénarios testés
- Scénario 1 : Entreprise (192.168.1.0)
  TECHNIQUE 120, WIFI 80, ADMINISTRATION 50, SERVEURS 20
- Scénario 2 : Université (172.16.0.0)
  ETUDIANTS 500, ENSEIGNANTS 120, LABORATOIRES 60,
  WIFI_PUBLIC 200, SERVEURS 30

## Résultats obtenus
===== IPPlan-Manager : TP6 - VLANs =====

===== VLANS G�N�R�S =====
VLAN ID : 10
Nom : TECHNIQUE
Description : VLAN du service TECHNIQUE
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes

VLAN ID : 20
Nom : WIFI
Description : VLAN du service WIFI
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes

VLAN ID : 30
Nom : ADMINISTRATION
Description : VLAN du service ADMINISTRATION
ADMINISTRATION -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes

VLAN ID : 40
Nom : SERVEURS
Description : VLAN du service SERVEURS
SERVEURS -> 192.168.2.64/27 | Plage : 192.168.2.65 - 192.168.2.94 | Capacite : 30 hotes


===== TEST DE RECHERCHE VLAN =====
VLAN ID : 20
Nom : WIFI
Description : VLAN du service WIFI
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes

===== SCENARIO UNIVERSITE =====
Nombre total de VLANs : 5

===== VLANS GENERES =====
VLAN ID : 10
Nom : ETUDIANTS
Description : VLAN du service ETUDIANTS
ETUDIANTS -> 172.16.0.0/23 | Plage : 172.16.0.1 - 172.16.1.254 | Capacite : 510 hotes

VLAN ID : 20
Nom : WIFI_PUBLIC
Description : VLAN du service WIFI_PUBLIC
WIFI_PUBLIC -> 172.16.2.0/24 | Plage : 172.16.2.1 - 172.16.2.254 | Capacite : 254 hotes

VLAN ID : 30
Nom : ENSEIGNANTS
Description : VLAN du service ENSEIGNANTS
ENSEIGNANTS -> 172.16.3.0/25 | Plage : 172.16.3.1 - 172.16.3.126 | Capacite : 126 hotes

VLAN ID : 40
Nom : LABORATOIRES
Description : VLAN du service LABORATOIRES
LABORATOIRES -> 172.16.3.128/26 | Plage : 172.16.3.129 - 172.16.3.190 | Capacite : 62 hotes

VLAN ID : 50
Nom : SERVEURS
Description : VLAN du service SERVEURS
SERVEURS -> 172.16.3.192/27 | Plage : 172.16.3.193 - 172.16.3.222 | Capacite : 30 hotes

===== VLANS CRITIQUES (plus de 100 hotes) =====
VLAN critique detecte :
VLAN 10 - ETUDIANTS - 510 hotes

VLAN critique detecte :
VLAN 20 - WIFI_PUBLIC - 254 hotes

VLAN critique detecte :
VLAN 30 - ENSEIGNANTS - 126 hotes

## Difficultés rencontrées
- Comprendre la relation entre VLAN et ResultatVLSM
- Comprendre le rôle du gestionnaire métier

## Réponses aux questions

1. Les VLANs permettent de segmenter logiquement le réseau,
   réduire les broadcasts, améliorer la sécurité et les performances.

2. Chaque VLAN correspond à un domaine logique différent.
   Un sous-réseau distinct lui est attribué pour éviter les conflits.

3. La séparation logique isole les flux entre départements.
   Un attaquant dans le VLAN WIFI ne peut pas accéder aux SERVEURS.

4. GestionnaireVLAN centralise toutes les opérations sur les VLANs :
   ajout, recherche, affichage, détection des VLANs critiques.

5. Un VLAN est directement lié à un sous-réseau calculé.
   ResultatVLSM contient toutes les informations réseau du VLAN.

6. Le nombre de VLANs n'est pas fixe. ArrayList permet d'en
   ajouter dynamiquement selon les besoins.

7. Chaque classe a une responsabilité précise. Cela rend le code
   plus lisible, maintenable et évolutif.

8. Le projet combine maintenant calculs réseau, gestion de
   collections, associations entre objets et logique métier,
   comme une vraie application professionnelle.