# TP4 - Calculs réseau

## Objectif
Introduction des calculs automatiques réseau.

## Notions étudiées
- Méthodes statiques
- Calculs réseau
- CIDR
- Logique algorithmique
- Classes utilitaires

## Tests réalisés
- 192.168.1.0/24 → Classe C, 254 hôtes
- 172.16.0.0/16 → Classe B, 65534 hôtes
- 10.0.0.0/8 → Classe A, 16777214 hôtes
- 192.168.10.0/25 → Classe C, 126 hôtes
- 192.168.20.0/26 → Classe C, 62 hôtes
- 192.168.30.0/27 → Classe C, 30 hôtes
- 192.168.1.0 privé → true
- 172.16.0.0 privé → true
- 10.0.0.0 privé → true
- 8.8.8.8 privé → false

## Difficultés rencontrées
- Comprendre la formule 2^(32-CIDR)-2
- Comprendre le fonctionnement des méthodes statiques

## Réponses aux questions

1. On crée une classe utilitaire pour regrouper les calculs
   en un seul endroit et éviter les répétitions dans le code.

2. static signifie que la méthode appartient à la classe
   elle-même. On peut l'appeler sans créer d'objet.

3. Les calculs réseau automatisent la planification IP,
   évitent les erreurs humaines et accélèrent les déploiements.

4. Le CIDR indique le nombre de bits réservés pour la partie
   réseau. Il détermine la taille du réseau.

5. Plus le masque est grand, moins il reste de bits pour
   les hôtes, donc moins d'adresses disponibles.

6. Les adresses privées sont réservées aux réseaux internes.
   Elles ne sont pas routables sur Internet.

7. Séparer la logique métier des calculs rend le code plus
   lisible, maintenable et réutilisable.

8. L'automatisation évite les erreurs manuelles et permet
   de gérer de grandes infrastructures efficacement.

## Résultat du run
Infrastructure : Infrastructure YFY

===== SOUS-R�SEAUX =====
Sous-r�seau : ADMIN
R�seau : 192.168.1.0/24
Description : R�seau Administration
Classe r�seau : Classe C
Masque d�cimal : 255.255.255.0
Capacit� maximale : 254 h�tes

Sous-r�seau : TECH
R�seau : 172.16.0.0/16
Description : R�seau Technique
Classe r�seau : Classe B
Masque d�cimal : 255.255.0.0
Capacit� maximale : 65534 h�tes

Sous-r�seau : WIFI
R�seau : 10.0.0.0/8
Description : R�seau WiFi
Classe r�seau : Classe A
Masque d�cimal : 255.0.0.0
Capacit� maximale : 16777214 h�tes

Sous-r�seau : COMPTA
R�seau : 192.168.10.0/25
Description : R�seau Comptabilit�
Classe r�seau : Classe C
Masque d�cimal : 255.255.255.128
Capacit� maximale : 126 h�tes

Sous-r�seau : DIRECTION
R�seau : 192.168.20.0/26
Description : R�seau Direction
Classe r�seau : Classe C
Masque d�cimal : 255.255.255.192
Capacit� maximale : 62 h�tes

Sous-r�seau : SECURITE
R�seau : 192.168.30.0/27
Description : R�seau S�curit�
Classe r�seau : Classe C
Masque d�cimal : 255.255.255.224
Capacit� maximale : 30 h�tes

===== �QUIPEMENTS =====
===== TEST RESEAU PRIVE =====
192.168.1.0 prive ? true
172.16.0.0 prive ? true
10.0.0.0 prive ? true
8.8.8.8 prive ? false