# TP7 - Validations avancées et détection des conflits

## Objectif
Ajouter des validations avancées pour détecter les incohérences dans un plan d'adressage.

## Notions étudiées
Exceptions personnalisées, try/catch, throw, validation réseau, détection de chevauchement, conflit VLAN, robustesse logicielle.

## Exceptions créées
- AdresseIPInvalideException : adresse IP mal formatée
- ConflitVLANException : identifiant VLAN déjà utilisé
- ChevauchementReseauException : deux sous-réseaux se chevauchent
- ReseauInsuffisantException : capacité du réseau de départ insuffisante

## Scénarios testés
1. Plan d'adressage normal avec 4 besoins (ADMINISTRATION 50, TECHNIQUE 120, WIFI 80, SERVEURS 20)
2. Conflit VLAN : tentative d'ajout de deux VLANs avec l'identifiant 10
3. Adresse IP invalide : utilisation de 192.168.300.0
4. Chevauchement de réseaux : 192.168.1.0/25 et 192.168.1.64/26
5. Réseau insuffisant : besoins trop grands pour 192.168.1.0/25

## Résultats obtenus
===== IPPlan-Manager : TP7 - Validations avancees =====

--- Test 1 : Plan d'adressage normal ---
Plan genere :
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes
ADMINISTRATION -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes
SERVEURS -> 192.168.2.64/27 | Plage : 192.168.2.65 - 192.168.2.94 | Capacite : 30 hotes
Validation termin�e : aucun conflit critiqued�tect�.

--- Test 2 : Conflit VLAN ---
Liste des VLANs :
VLAN ID : 10
Nom : ADMINISTRATION
Description : VLAN Administration
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes
VLAN ID : 20
Nom : TECHNIQUE
Description : VLAN Technique
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes

Tentative d'ajout d'un VLAN avec ID deja utilise...
Erreur VLAN : Conflit VLAN : l'identifiant10 est d�j� utilis�.

--- Test 3 : Adresse IP invalide (192.168.300.0) ---
Validation termin�e : aucun conflit critiqued�tect�.

--- Test 4 : Chevauchement de reseaux ---
Erreur de chevauchement detectee : Chevauchement d�tect� entre RESEAU_A et RESEAU_B

--- Test 5 : Reseau insuffisant ---
Erreur : Reseau insuffisant : le reseau 192.168.1.0/25 peut contenir 126 h�tes, mais les besoins necessitent 448 adresses.

## Difficultés rencontrées
- Compréhension du mécanisme des exceptions personnalisées
- Gestion du format d'adresse avec CIDR (/24) dans les conversions
- Synchronisation des méthodes entre les différentes classes

## Réponses aux questions

### 1. Pourquoi les validations avancées sont-elles indispensables dans un outil IPAM ?
Les validations avancées sont indispensables car un plan d'adressage incorrect peut provoquer des incidents réels dans une infrastructure réseau : conflits d'adresses IP, erreurs de routage inter-VLAN, pannes de services, machines inaccessibles. Un outil IPAM professionnel doit garantir la cohérence du plan avant son déploiement.

### 2. Quelle est la différence entre une erreur simple et une exception en Java ?
Une erreur simple empêche la compilation ou provoque un arrêt brutal. Une exception est un mécanisme qui signale une erreur pendant l'exécution tout en permettant de la traiter via try/catch sans arrêter brutalement le programme.

### 3. Pourquoi crée-t-on des exceptions personnalisées ?
Pour rendre les messages d'erreur plus clairs et spécifiques au domaine métier, et pour pouvoir catcher spécifiquement certains types d'erreurs.

### 4. Quel est le rôle du bloc try/catch ?
Le bloc try contient le code susceptible de produire une erreur. Le bloc catch capture l'exception et permet de la traiter sans que le programme ne s'arrête brutalement.

### 5. Pourquoi deux VLANs ne doivent-ils pas avoir le même identifiant dans une même infrastructure ?
Car l'identifiant VLAN est utilisé par les commutateurs pour isoler le trafic. Deux VLANs avec le même ID provoqueraient des mélanges de trafic et des problèmes de sécurité.

### 6. Pourquoi deux sous-réseaux ne doivent-ils pas se chevaucher ?
Car une même adresse IP appartiendrait à deux réseaux différents, créant des ambiguïtés de routage et des conflits d'adresses.

### 7. Pourquoi transforme-t-on les adresses IP en entiers pour comparer des plages réseau ?
Car une adresse IP est un nombre 32 bits. La conversion en entier permet des comparaisons numériques simples pour détecter les chevauchements.

### 8. Pourquoi la classe ValidateurPlanAdressage doit-elle être séparée du moteur VLSM ?
Pour respecter le principe de responsabilité unique : le moteur génère le plan, le validateur vérifie sa cohérence. Cette séparation rend le code plus modulaire et maintenable.