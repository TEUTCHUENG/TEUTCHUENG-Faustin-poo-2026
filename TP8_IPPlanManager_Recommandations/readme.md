# TP8 - Moteur de recommandations

## Objectif
Ajouter un moteur de recommandations capable d'analyser un plan VLAN et de proposer des conseils techniques.

## Notions étudiées
Interfaces Java, polymorphisme, règles métier, moteur de recommandations, séparation des responsabilités, extensibilité logicielle.

## Classes créées
- Recommandation : objet représentant un conseil (titre, priorité, message)
- RegleRecommendation : interface définissant le contrat des règles
- RecommandationWifiInvite : détecte les VLANs WiFi et recommande l'isolation
- RecommandationServeurs : détecte les VLANs Serveurs et recommande une protection renforcée
- RecommandationGrandVLAN : détecte les VLANs de plus de 200 hôtes
- RecommandationAdministration : détecte les VLANs d'administration
- RecommandationMargeAdresse : détecte les marges d'adresses insuffisantes
- MoteurRecommandation : applique toutes les règles sur tous les VLANs

## Scénarios testés

### Scénario 1 : Réseau campus
- ETUDIANTS : 500 hôtes
- WIFI_INVITES : 200 hôtes
- ENSEIGNANTS : 120 hôtes
- LABORATOIRES : 60 hôtes
- SERVEURS : 30 hôtes
- Réseau de départ : 10.10.0.0/22

### Scénario 2 : Réseau entreprise
- ADMINISTRATION : 50 hôtes
- WIFI_INVITES : 120 hôtes
- SERVEURS : 20 hôtes
- CAMERAS : 80 hôtes
- VOIP : 60 hôtes
- Réseau de départ : 192.168.1.0/23

## Recommandations obtenues
===== IPPlan-Manager : TP8 - Recommandations =====

--- Sc�nario 1 : R�seau campus ---
Plan VLAN genere :
Liste des VLANs :
VLAN ID : 10
Nom : ETUDIANTS
Description : VLAN ETUDIANTS
ETUDIANTS -> 10.10.0.0/23 | Masque : 255.255.254.0 | Capacite : 510 h�tes
VLAN ID : 20
Nom : WIFI_INVITES
Description : VLAN WIFI_INVITES
WIFI_INVITES -> 10.10.2.0/24 | Masque : 255.255.255.0 | Capacite : 254 h�tes
VLAN ID : 30
Nom : ENSEIGNANTS
Description : VLAN ENSEIGNANTS
ENSEIGNANTS -> 10.10.3.0/25 | Masque : 255.255.255.128 | Capacite : 126 h�tes
VLAN ID : 40
Nom : LABORATOIRES
Description : VLAN LABORATOIRES
LABORATOIRES -> 10.10.3.128/26 | Masque : 255.255.255.192 | Capacite : 62 h�tes
VLAN ID : 50
Nom : SERVEURS
Description : VLAN SERVEURS
SERVEURS -> 10.10.3.192/27 | Masque : 255.255.255.224 | Capacite : 30 h�tes

Recommandations proposees :
[MOYENNE] VLAN de grande taille : Le VLAN ETUDIANTS poss�de une grandecapacit�. Il faut surveiller les broadcasts.
[�LEV�E] Isolation du WiFi : Le VLAN WIFI_INVITES doit �tre isol� des VLANs internes sensibles.
[MOYENNE] VLAN de grande taille : Le VLAN WIFI_INVITES poss�de une grandecapacit�. Il faut surveiller les broadcasts.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN ENSEIGNANTS a une marge de seulement 6 h�tes (120 demand�s, 126 disponibles). Pr�voir une marge plus confortable si le r�seau est susceptible d'�voluer.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN LABORATOIRES a une marge de seulement 2 h�tes (60 demand�s, 62 disponibles). Pr�voir une marge plus confortable si le r�seau est susceptible d'�voluer.
[�LEV�E] Protection du VLAN Serveurs : Le VLAN SERVEURS doit �tre prot�g� pardes ACL et surveill� en priorit�.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN SERVEURS a une marge de seulement 0 h�tes (30 demand�s, 30 disponibles). Pr�voir une marge plus confortable si le r�seau est susceptible d'�voluer.

--- Sc�nario 2 : ADMINISTRATION, WIFI_INVITES, SERVEURS, CAMERAS, VOIP ---
Plan VLAN genere :
Liste des VLANs :
VLAN ID : 10
Nom : WIFI_INVITES
Description : VLAN WIFI_INVITES
WIFI_INVITES -> 192.168.1.0/25 | Masque : 255.255.255.128 | Capacite : 126 h�tes
VLAN ID : 20
Nom : CAMERAS
Description : VLAN CAMERAS
CAMERAS -> 192.168.1.128/25 | Masque : 255.255.255.128 | Capacite : 126 h�tes
VLAN ID : 30
Nom : VOIP
Description : VLAN VOIP
VOIP -> 192.168.2.0/26 | Masque : 255.255.255.192 | Capacite : 62 h�tes
VLAN ID : 40
Nom : ADMINISTRATION
Description : VLAN ADMINISTRATION
ADMINISTRATION -> 192.168.2.64/26 | Masque : 255.255.255.192 | Capacite : 62 h�tes
VLAN ID : 50
Nom : SERVEURS
Description : VLAN SERVEURS
SERVEURS -> 192.168.2.128/27 | Masque : 255.255.255.224 | Capacite : 30 h�tes

Recommandations proposees :
[�LEV�E] Isolation du WiFi : Le VLAN WIFI_INVITES doit �tre isol� des VLANs internes sensibles.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN WIFI_INVITES a une marge de seulement 6 h�tes (120 demand�s, 126 disponibles). Pr�voir une marge plus confortable si le r�seau est susceptible d'�voluer.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN VOIP a une marge de seulement 2 h�tes (60 demand�s, 62 disponibles). Pr�voir une marge plus confortable si le r�seau est susceptible d'�voluer.
[�LEV�E] Acc�s Administration restreint : Le VLAN ADMINISTRATION doit �tre accessible uniquement aux administrateurs r�seau.
[�LEV�E] Protection du VLAN Serveurs : Le VLAN SERVEURS doit �tre prot�g� pardes ACL et surveill� en priorit�.
## Difficultés rencontrées
- Compréhension du concept d'interface Java
- Gestion du polymorphisme avec des règles variées
- Adaptation de ResultatVLSM pour conserver le nombre d'hôtes demandés
- Calcul de la marge d'adresses pour RecommandationMargeAdresse

## Réponses aux questions

### 1. Quel est le rôle d'un moteur de recommandations dans un outil IPAM ?
Le moteur de recommandations analyse un plan d'adressage et produit des conseils techniques pour améliorer la sécurité, la performance et la maintenabilité du réseau. Il transforme l'outil en assistant technique.

### 2. Pourquoi utilise-t-on une interface pour les règles de recommandation ?
Pour définir un contrat commun garantissant que chaque règle possède une méthode analyser() standardisée. Cela permet d'ajouter des règles sans modifier le moteur.

### 3. Quelle est la différence entre une classe concrète et une interface ?
Une interface définit des signatures de méthodes sans implémentation. Une classe concrète contient l'implémentation complète. Une classe peut implémenter plusieurs interfaces.

### 4. Pourquoi la méthode analyser() peut-elle retourner null ?
Pour distinguer les cas normaux (null) des cas où un problème est détecté (objet Recommandation).

### 5. Pourquoi le moteur de recommandations illustre-t-il le polymorphisme ?
Le moteur manipule des objets RegleRecommendation sans connaître leur classe concrète. Un même appel produit des comportements différents selon l'objet.

### 6. Pourquoi créer une classe par règle au lieu de tout mettre dans Main ?
Respect du principe de responsabilité unique : code plus lisible, maintenable et extensible sans modifier l'existant.

### 7. Pourquoi un VLAN WiFi invité doit-il être isolé des réseaux internes ?
Les utilisateurs invités ne sont pas de confiance. Sans isolation, ils pourraient accéder aux ressources internes, posant un risque de sécurité.

### 8. Pourquoi les VLANs de grande taille doivent-ils être surveillés ?
Ils génèrent beaucoup de broadcast, dégradent les performances, augmentent la surface d'attaque et compliquent la supervision.