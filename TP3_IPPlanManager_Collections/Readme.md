# TP3 - Collections et composition

## Objectif
Introduction des collections et des relations entre objets.

## Notions étudiées
- Composition
- ArrayList
- Collections
- Parcours de listes avec for-each
- Relations entre objets

## Tests réalisés
- Infrastructure avec 3 sous-réseaux (ADMIN, TECH, WIFI)
- Routeur avec 2 interfaces
- Switch, serveur, point d'accès WiFi, postes clients ajoutés
- Recherche de SRV_DNS : trouvé et affiché correctement
- Recherche de INCONNU : Équipement introuvable affiché

## Difficultés rencontrées
- Comprendre la différence entre une interface unique (TP2)
  et une collection d'interfaces (TP3)
- Comprendre le fonctionnement de la boucle for-each

## Réponses aux questions

1. La composition est le fait qu'un objet contient d'autres objets.
   Exemple : une infrastructure contient des équipements.

2. On utilise ArrayList car le nombre d'objets n'est pas fixe.
   On peut ajouter ou supprimer dynamiquement des éléments.

3. Une variable simple contient un seul objet.
   Une collection peut en contenir plusieurs sans limite fixe.

4. Un équipement réel comme un routeur possède plusieurs
   interfaces physiques : eth0, eth1, GigabitEthernet, etc.

5. Une infrastructure réseau réelle est toujours découpée
   en plusieurs sous-réseaux pour des raisons de sécurité,
   de performance et d'organisation.

6. La boucle for-each parcourt automatiquement tous les éléments
   d'une collection sans gérer manuellement un index.

7. InfrastructureReseau regroupe tous les objets du projet.
   Elle devient le point central qui orchestre tout le système.

8. Les collections permettent de gérer un nombre variable d'objets,
   ce qui est indispensable dans toute application professionnelle
   réelle où les données évoluent en permanence.
   ## Résultat des test
   Infrastructure : Infrastructure YFY

===== SOUS-R�SEAUX =====
Sous-r�seau : ADMIN
Reseau : 192.168.1.0/24
Description : R�seau administration

Sous-r�seau : TECH
Reseau : 192.168.2.0/24
Description : R�seau technique

Sous-r�seau : WIFI
Reseau : 192.168.3.0/24
Description : R�seau WiFi

===== �QUIPEMENTS =====
Nom : R1_EDGE
Type : Routeur
Nombre d'interfaces : 2
Interface : eth0
Adresse IP : 192.168.1.1
Etat : active

Interface : eth1
Adresse IP : 10.0.0.1
Etat : active


Nom : SW_PRINCIPAL
Type : Switch
Nombre d'interfaces : 1
Interface : eth0
Adresse IP : 192.168.1.2
Etat : active


Nom : SRV_DNS
Type : Serveur
Nombre d'interfaces : 1
Interface : eth0
Adresse IP : 192.168.1.10
Etat : active


Nom : AP_WIFI
Type : Point d'acc�s WiFi
Nombre d'interfaces : 1
Interface : wlan0
Adresse IP : 192.168.3.1
Etat : active


Nom : PC_ADMIN
Type : Poste client
Nombre d'interfaces : 1
Interface : eth0
Adresse IP : 192.168.1.50
Etat : inactive


===== RECHERCHE =====
Nom : SRV_DNS
Type : Serveur
Nombre d'interfaces : 1
Interface : eth0
Adresse IP : 192.168.1.10
Etat : active


�quipement introuvable.