# TP10 - Application finale IPPlan-Manager

## Objectif

Assembler toutes les fonctionnalités développées dans les TPs précédents
afin de produire une application console complète de planification
d'adressage IP.

## Fonctionnalités réalisées

Saisie utilisateur, génération VLSM, création de VLANs, validation du
plan, recommandations techniques, sauvegarde CSV, génération de rapport,
chargement des besoins depuis un fichier CSV.

## Organisation du projet

- `model` : classes métier (BesoinReseau, ResultatVLSM, VLAN, Recommandation)
- `service` : traitements (MoteurVLSM, GestionnaireVLAN, MoteurRecommandation, etc.)
- `repository` : lecture et écriture de fichiers
- `exception` : exceptions personnalisées
- `console` : interaction avec l'utilisateur
- `main` : classe de lancement

## Scénarios testés

### Scénario 1 - Campus IRT
- ETUDIANTS : 500 hôtes
- WIFI_INVITES : 200 hôtes
- ENSEIGNANTS : 120 hôtes
- LABORATOIRES : 60 hôtes
- SERVEURS : 30 hôtes

### Scénario 2 - PME
- ADMINISTRATION : 50 hôtes
- COMPTABILITE : 20 hôtes
- WIFI_INVITES : 80 hôtes
- SERVEURS : 15 hôtes
- VOIP : 40 hôtes

### Scénario 3 - Entreprise multi-services
- TECHNIQUE : 120 hôtes
- DIRECTION : 25 hôtes
- CAMERAS : 60 hôtes
- SUPPORT : 35 hôtes
- INVITES : 100 hôtes

## Fichiers générés

- `exports/Campus_IRT_plan.csv`
- `exports/Campus_IRT_vlans.csv`
- `exports/Campus_IRT_recommandations.txt`
- `exports/Campus_IRT_rapport.txt`
- `exports/PME_plan.csv`
- `exports/PME_vlans.csv`
- `exports/PME_recommandations.txt`
- `exports/PME_rapport.txt`
- `exports/Entreprise_Multi_plan.csv`
- `exports/Entreprise_Multi_vlans.csv`
- `exports/Entreprise_Multi_recommandations.txt`
- `exports/Entreprise_Multi_rapport.txt`

## Difficultés rencontrées

- Correction des méthodes dupliquées générées automatiquement par NetBeans
- Correction des imports après organisation en packages
- Gestion séparée des exceptions ConflitVLANException et IOException

## Réponses aux questions

**1. Pourquoi le TP10 représente-t-il une application plus complète ?**
Parce qu'il assemble toutes les fonctionnalités développées séparément
dans les TPs précédents en une seule application cohérente et utilisable.

**2. Quel est le rôle de ApplicationIPPlanManager ?**
C'est l'orchestrateur de l'application. Il coordonne tous les services :
saisie, génération VLSM, VLANs, validation, recommandations, sauvegarde.

**3. Pourquoi la classe Main doit-elle rester courte ?**
Parce que la logique principale ne doit pas être dans Main. Main sert
uniquement à lancer l'application. Cela respecte le principe de
séparation des responsabilités.

**4. Pourquoi séparer les packages ?**
Pour organiser les classes selon leur rôle. Cela facilite la
maintenance, la lecture et l'évolution du projet.

**5. Pourquoi la saisie est-elle dans ConsoleService ?**
Pour séparer l'interaction utilisateur de la logique métier. Si on
change d'interface (graphique par exemple), on ne touche pas aux
services métier.

**6. Pourquoi valider l'adresse réseau avant le plan VLSM ?**
Pour éviter de générer un plan basé sur une adresse incorrecte qui
produirait des résultats invalides.

**7. Pourquoi les recommandations sont-elles exécutées après les VLANs ?**
Parce que les recommandations analysent les VLANs. Il faut donc que
les VLANs soient créés avant de pouvoir les analyser.

**8. Pourquoi la sauvegarde rend-elle l'application exploitable ?**
Parce que les résultats peuvent être consultés, partagés et réutilisés
après l'arrêt du programme.

**9. Pourquoi le rapport technique est-il important ?**
Parce qu'il présente les résultats de manière lisible pour un
administrateur réseau ou un enseignant, sans nécessiter de relancer
le programme.

**10. Quelles améliorations futures ?**
- Interface graphique
- Base de données pour stocker les plans
- Authentification utilisateur
- Export en PDF
- Comparaison de plusieurs plans d'adressage

## Conclusion personnelle

Ce projet m'a permis de comprendre comment la POO permet de construire
une application complète et professionnelle. Chaque classe a un rôle
précis, les packages organisent le code, et les interfaces permettent
d'étendre l'application facilement. La progression des TPs montre
comment une application se construit étape par étape.