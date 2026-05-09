# TP2 - Encapsulation

## Objectif
Introduction de l'encapsulation et des validations.

## Notions étudiées
- private
- getters
- setters
- validation
- this

## Tests réalisés
- Adresse IP vide → remplacée par 0.0.0.0
- Adresse IP null → remplacée par 0.0.0.0
- Masque CIDR invalide (55) → remplacé par 24
- Nom d'équipement vide → remplacé par equipement_inconnu
- Nom d'interface vide → remplacé par interface_inconnue

## Difficultés rencontrées
- Comprendre la différence entre appeler le setter dans le constructeur
  plutôt que d'affecter directement l'attribut.
- 

## Réponses aux questions

1. On utilise private pour empêcher toute modification directe
   des attributs depuis l'extérieur de la classe.

2. Un attribut public est accessible et modifiable partout.
   Un attribut privé n'est accessible que dans sa propre classe,
   via des getters et setters contrôlés.

3. Les getters permettent de lire les données.
   Les setters permettent de les modifier tout en validant
   les valeurs avant acceptation.

4. Dans un logiciel réseau, une donnée invalide comme une adresse
   IP vide ou un masque incorrect peut provoquer des pannes,
   des conflits ou des configurations réseau erronées.

5. Le mot-clé this désigne l'objet courant. Il permet de distinguer
   un attribut de la classe d'un paramètre portant le même nom.

6. Le constructeur appelle les setters pour réutiliser les règles
   de validation déjà écrites, évitant ainsi la duplication de code.

7. Un masque CIDR doit être compris entre 0 et 32.
   Un masque invalide comme 55 produirait des calculs de
   sous-réseau complètement faux.

8. L'encapsulation protège les données internes d'un objet.
   Elle garantit que seules des valeurs valides sont stockées,
   rendant le logiciel plus fiable et plus sûr.
    ## Résultat des test
===== TP2 : Encapsulation =====
Erreur : adresse IP invalide.
Erreur : adresse IP invalide.
Adresse reseau invalide.
Masque CIDR invalide.

--- Modification avec setters ---

----- R�seau 1 -----
Reseau : 192.168.1.0/24
Description : R�seau principal

----- R�seau 2 (corrig�) -----
Reseau : 192.168.2.0/28
Description : R�seau corrig�

----- R�seau 3 -----
Reseau : 10.0.0.0/8
Description : R�seau interne

----- R�seau 4 -----
Reseau : 172.16.0.0/16
Description : R�seau DMZ

----- �quipement 1 : Routeur -----
Nom : R1_EDGE_MODIFIE
Type : Routeur
Interface : eth0
Adresse IP : 192.168.1.1
Etat : active

----- �quipement 2 : Serveur (invalide) -----
Nom : equipement_inconnu
Type : Type inconnu
Interface : interface_inconnue
Adresse IP : 0.0.0.0
Etat : inactive

----- �quipement 3 : Switch -----
Nom : SW1_CORE
Type : Switch
Interface : eth1
Adresse IP : 10.0.0.1
Etat : active

----- �quipement 4 : Firewall -----
Nom : FW1
Type : Firewall
Interface : fa0/0
Adresse IP : 172.16.0.1
Etat : inactive

--- Test estAdresseLocale() ---
192.168.1.1 est locale ? true
10.0.0.1 est locale ? false
172.16.0.1 est locale ? false