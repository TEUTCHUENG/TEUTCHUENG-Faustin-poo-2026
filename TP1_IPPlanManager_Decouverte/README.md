# TP1 - IPPlan-Manager

## Objectif du TP
Ce TP permet de découvrir les premières classes Java du projet IPPlan-Manager.

## Classes créées
- AdresseIP
- ReseauIP
- InterfaceReseau
- Equipement
- Main

## Travail réalisé
Création et affichage de plusieurs objets réseau : routeur, serveur, 
switch, point d'accès WiFi, postes clients, deux réseaux IP, 
interfaces actives et inactives, interface sans adresse IP.

## Réponses aux questions

1. Une adresse IP est représentée par une classe car elle peut évoluer :
   on pourra lui ajouter des validations, des méthodes de calcul, etc.
   Un simple String ne permettrait pas cela.

2. Une classe est le modèle. Un objet est créée à partir de ce modèle(la classe).

3. Le constructeur initialise les attributs de l'objet au moment 
   de sa création avec new.

4. InterfaceReseau contient un objet AdresseIP car une interface 
   réseau réelle possède une adresse IP. C'est une relation entre 
   deux entités du monde réel.

5. Equipement contient un objet InterfaceReseau car un équipement 
   réseau réel possède au moins une interface réseau.

6. La limite actuelle est qu'un équipement ne peut avoir qu'une 
   seule interface. Dans la réalité, un routeur en a plusieurs.

7. Cette version ne calcule pas automatiquement les sous-réseaux,
   les masques ou les plages d'adresses. Ce sont de simples 
   affichages sans logique de calcul.

   ## Résultat des test
   run:
===== IPPlan-Manager : TP1 =====
D�couverte des premi�res classes du projet

----- R�seau cr�� -----
R�seau : 192.168.1.0/24
Description : R�seau principal du laboratoire IRT

----- �quipements cr��s -----

Nom de l'�quipement : R1_EDGE
Type d'�quipement : Routeur
Interface : eth0
Adresse IP : 192.168.1.1
�tat : active

Nom de l'�quipement : SRV_DNS
Type d'�quipement : Serveur
Interface : eth0
Adresse IP : 192.168.1.10
�tat : active

Nom de l'�quipement : PC_ADMIN
Type d'�quipement : Poste client
Interface : wlan0
Adresse IP : 192.168.1.50
�tat : inactive
----- 2e reseau -----
R�seau : 172.168.5.0/16
Description : Reseau secondaire de gestion

----- Nouveaux equipements -----

Nom de l'�quipement : SWITCH1
Type d'�quipement : Switch
Interface : eth0
Adresse IP : 172.168.5.254
�tat : inactive

Nom de l'�quipement : WIFI
Type d'�quipement : Point d'acces WiFi
Interface : wlan0
Adresse IP : 172.168.5.200
�tat : active

Nom de l'�quipement : PC_client1
Type d'�quipement : Poste client
Interface : eth1
Adresse IP : non configur�e
�tat : inactive
BUILD SUCCESSFUL (total time: 0 seconds)
