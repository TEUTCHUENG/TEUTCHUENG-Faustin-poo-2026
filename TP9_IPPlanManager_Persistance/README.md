# TP9 - Persistance et organisation professionnelle

## Objectif

Ajouter la lecture et l'écriture de fichiers afin de sauvegarder les
besoins, les plans, les VLANs, les recommandations et les rapports.

## Notions étudiées

Persistance, fichiers CSV, lecture de fichier, écriture de fichier,
repository, service, packages, architecture professionnelle.

## Fichiers utilisés

### Fichiers d'entrée
- `exports/besoins.csv` : besoins du scénario principal
- `exports/besoins_pme.csv` : besoins du scénario PME

### Fichiers générés
- `exports/plan_adressage.csv` : plan VLSM généré
- `exports/vlans.csv` : VLANs générés
- `exports/recommandations.txt` : recommandations techniques
- `exports/rapport_pme.txt` : rapport complet de la PME
- `exports/besoins_sauvegarde.csv` : sauvegarde des besoins chargés

## Scénarios testés

### Scénario 1 - Principal
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

## Difficultés rencontrées

- Correction des imports après le déplacement des classes dans les nouveaux packages
- Problème d'encodage des accents dans la console NetBeans
- Erreur de réseau insuffisant corrigée en précisant le masque /16
- Correction de l'extension du fichier besoins.csv créé avec le Bloc-notes

## Réponses aux questions

**1. Qu'est-ce que la persistance des données ?**
La persistance des données signifie que les informations produites par
une application sont sauvegardées dans un support durable (fichier,
base de données) afin qu'elles ne disparaissent pas à l'arrêt du
programme.

**2. Pourquoi une application professionnelle doit-elle sauvegarder ses résultats ?**
Pour permettre à l'administrateur de consulter, partager ou réutiliser
les résultats plus tard sans avoir à relancer le programme. Cela
garantit aussi une traçabilité des décisions techniques.

**3. Quelle est la différence entre un fichier CSV et un rapport texte ?**
Un fichier CSV est structuré avec des séparateurs pour être traité par
un logiciel comme Excel. Un rapport texte est lisible directement par
un humain et présente les informations de manière organisée et
compréhensible.

**4. Pourquoi a-t-on créé un package repository ?**
Pour regrouper toutes les classes responsables de la lecture et de
l'écriture des données. Cela sépare l'accès aux données de la logique
métier et rend le projet plus organisé.

**5. Pourquoi a-t-on créé un package service ?**
Pour regrouper toutes les classes qui réalisent des traitements métier
comme le calcul VLSM, la gestion des VLANs et les recommandations.
Cela évite de mélanger les responsabilités.

**6. Pourquoi ne faut-il pas écrire tout le code dans la classe Main ?**
Parce que cela rendrait le code illisible, difficile à maintenir et
impossible à réutiliser. En séparant les responsabilités dans des
classes spécialisées, chaque classe a un rôle précis et peut être
modifiée indépendamment.

**7. Pourquoi le fichier besoins.csv rend-il l'application plus flexible ?**
Parce qu'il permet de changer les besoins sans modifier le code Java.
Il suffit de modifier le fichier CSV et relancer le programme pour
obtenir un nouveau plan d'adressage.

**8. Pourquoi la séparation en packages améliore-t-elle la maintenabilité du projet ?**
Parce qu'elle permet de localiser rapidement une classe selon son rôle.
Si on veut modifier la persistance, on va dans repository. Si on veut
modifier un traitement, on va dans service. Cela facilite aussi le
travail en équipe.