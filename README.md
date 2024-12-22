# Jeu d'Aventure en Java

### Scénario envisagé

---

## Fait par :
- Feriel MEHENNI  
- Maxime VERNOUX  
- Mohamed-Amine EZ-ZAOUI  
- Mathias CAZALS  

---

## Scénario Envisagé : Mystères de l'Artefact Perdu

### Table des matières
1. [Histoire](#histoire)  
2. [Contexte](#contexte)  
3. [Description Fonctionnelle](#description-fonctionnelle)  
   - [Carte et Zones](#carte-et-zones)  
   - [Objets, Indices et Fragments](#objets-indices-et-fragments)  
   - [Les Personnages](#les-personnages)  
   - [Commandes Disponibles](#commandes-disponibles)  
   - [Situations Gagnantes et Perdantes](#situations-gagnantes-et-perdantes)  
   - [Déroulement d'une Partie Gagnante](#déroulement-dune-partie-gagnante)  
4. [Enigmes](#enigmes)  
5. [Checkstyle](#checkstyle)  

---

## Histoire

En 1958, en pleine Guerre froide, Dwight D. Eisenhower fonda le DARPA (Defense Advanced Research Projects Agency), une agence du département de la Défense des États-Unis. Son objectif était clair : être à la pointe de l'innovation technologique pour garantir la suprématie militaire américaine. Cette vision prit une dimension particulièrement ambitieuse en 1989, lorsque le département de la Défense chargea le DARPA de développer un programme d’armement dont le but est d’assurer l’annihilation de toute forme de vie en cas de défaite des forces armées américaines. 

Ce projet, mené dans le plus grand secret, devint un enjeu stratégique pour contrer une Union soviétique perçue comme une menace croissante. Les chercheurs travaillèrent sans relâche, explorant des domaines tels que la manipulation génétique, la bioéthique, et les interfaces homme-machine. Malgré les efforts déployés, les résultats restèrent infructueux, et en 1991, peu avant la fin officielle de la Guerre froide, le projet fut abruptement abandonné. Toutes les données furent classées Extremely Secret (Extrêmement Secret) et archivées dans des installations protégées par des mesures de sécurité extrême.

En 2006, le Docteur Edward Phillips, alors directeur du DARPA, obtient l'autorisation du département de la Défense pour relancer le projet. Motivé par les avancées récentes en biotechnologie, Phillips était convaincu que les échecs passés étaient dus à des limitations technologiques aujourd’hui surmontées. Pendant près de dix ans, le DARPA travailla sans relâche, expérimentant sur des cellules humaines et animales, repoussant les frontières éthiques.

En 2015, après de très nombreux échecs, les opérateurs du programme ont dévoilé un prototype. Celui-ci prenait la forme d’un virus, totalement inoffensif à l’état latent, mais capable, une fois activé, de tuer toute forme de vie en quelques heures. Lors d’essais sur des spécimens humains, les chercheurs ont découvert qu’ils pouvaient en faire bien plus qu’une simple arme de dissuasion : le virus pourrait devenir l’arme ultime de l’arsenal américain, capable d’éliminer avec précision n’importe quelle cible.


En 2022, après un piratage massif orchestré par un groupe de hackers anonymes, une partie des données ultra secrètes des projets du DARPA fut rendue publique. Parmi elles, l'existence du virus expérimental surnommé "Le Juge" fut révélée. Ce virus, conçu pour cibler et éliminer toute forme de vie avec une précision redoutable, déclencha une onde de choc mondiale. Des extraits de rapports internes du DARPA confirmèrent les tests inhumains menés sur des prisonniers et des cobayes humains, ainsi que les véritables intentions derrière ce projet : une arme capable de garantir l'hégémonie des États-Unis par la terreur absolue.
La panique s’installa immédiatement. Bien que le gouvernement américain s'efforçait de minimiser l’affaire, affirmant que le projet était sous contrôle et qu’il s’agissait avant tout d’une arme de dissuasion, la communauté internationale ne fut pas convaincue.

Les premières fissures apparurent au sein des alliances stratégiques des États-Unis. Des pays alliés, comme le Royaume-Uni et le Japon, exigèrent des explications, tandis que des adversaires comme la Russie et la Chine accusèrent Washington de préparer un génocide global en cas de conflit.
Aux États-Unis, les révélations déclenchent des protestations massives. Le gouvernement, pour réprimer la dissidence et les menaces internes, utilisa "Le Juge" de manière ciblée, éliminant des militants et opposants politiques. Ces assassinats secrets alimentèrent une colère populaire grandissante.
En réponse, des mouvements anti-gouvernementaux se développèrent, et les États-Unis s’enfoncèrent dans une spirale de répression, de soulèvements, et de divisions politiques.

En 2025, l’instabilité atteint un point critique. Des États clés proclamèrent leur indépendance, l’armée se fragmenta, et des mutineries éclatèrent. La loi martiale ne suffit plus à contenir les révoltes. Sur la scène internationale, la peur d’une utilisation du virus mena à une militarisation mondiale, à l’effondrement des alliances, et à des conflits régionaux.

En 2030, Le Juge n'avait jamais été déployé à grande échelle, mais sa simple existence avait suffi à provoquer une série de guerres régionales alimentées par la peur et la méfiance. Les gouvernements, pris entre des insurrections internes et des conflits internationaux, s’effondrèrent les uns après les autres. Les États-Unis, ravagés par la guerre civile et l’instabilité politique, entraînèrent dans leur chute l’ordre mondial, plongeant la planète dans une ère de chaos, d’anarchie, et de luttes pour le pouvoir.


---

## Contexte

Après des décennies de reconstruction, la société humaine a retrouvé une certaine stabilité. De grandes cités ont émergé au milieu des ruines, et des alliances ont permis à des régions entières de renouer avec un semblant d’ordre. Cependant, les cicatrices de l’effondrement mondial sont encore visibles, et des factions s’affrontent pour le contrôle des ressources, des territoires et des vestiges du passé. 

Au cœur de cette reconstruction, un artefact ancien refait surface : L'Oculus du Jugement. Ce dispositif, dont l’origine exacte se perd dans les brumes du passé, est réputé pour avoir la capacité de contrôler un pouvoir ancien, oublié de tous. Bien que personne ne sache précisément ce que cet artefact active, une chose est certaine : son contrôle pourrait faire basculer l’équilibre fragile du monde.

Cet artéfact d’une puissance inégalée a été volé et fragmenté en plusieurs morceaux dispersé dans différentes zones, gardés par des énigmes et des obstacles. Avec la menace d’une faction hostile qui souhaite se la procurer, le joueur incarne un jeune aventurier mandaté nommé Kael Soren pour récupérer les fragments et reconstituer l'artefact avant qu'il ne tombe entre de mauvaises mains.

L'objectif principal est de réunir les fragments de L’Oculus du Jugement tout en évitant des pièges, en résolvant des énigmes et en récoltant des indices et des objets . L'histoire se déroule dans plusieurs zones uniques qui offrent différents niveaux de difficulté dans le monde des Terres des Héritages Perdus.


---

## Description Fonctionnelle

### Carte et Zones

Le jeu comporte six zones connectées par des sorties directionnelles. Chaque zone est unique, avec des énigmes, des objets à récupérer, et des obstacles à surmonter.

#### 1. Nouvelle-Dauréa
- Une cité lumineuse marquée par des ruines anciennes et des habitants amicaux. Cette zone offre des indices sur l'artefact.

#### 2. La Clairière des Souvenirs
- Une forêt mystérieuse peuplée de créatures magiques et protégée par Tarjan, un gardien ancestral.

#### 3. La Zone Désertique
- Un désert aride où chaque pas peut être mortel. Les tempêtes de sable cachent un fragment de l'artefact.

#### 4. Camp Tarsis
- Un campement fortifié contrôlé par des marchands étranges. Cette zone propose des échanges d'objets contre des indices.

#### 5. La Grotte
- Une caverne obscure pleine de dangers, abritant un fragment essentiel.

#### 6. Laboratoire Abandonné
- Le lieu d'origine du chaos mondial, rempli de mystères scientifiques et de traces du passé.

---

### Objets, Indices et Fragments

- **Carte** : Permet au joueur de visualiser l'agencement des zones.
- **Eau Bénite** : Utilisée pour détruire les ronces magiques.
- **Corde** : Indispensable pour franchir des gouffres ou obstacles physiques.
- **Potion de Temps** : Accorde un temps supplémentaire pour terminer le jeu.
- **Fragments d'Artefact** : Dissimulés dans chaque zone, ils sont indispensables pour réussir la quête.

---

### Les Personnages

- **Kael Soren** : Héros principal, un aventurier charismatique.
- **Tarjan** : Gardien de la forêt, protecteur des secrets anciens.
- **Kosaraju** : Un marchand énigmatique au Camp Tarsis.
- **Ravok le Dévastateur** : Un ennemi redoutable cherchant à s'emparer de l'artefact.

---

### Commandes Disponibles

- **Q** : Quitter le jeu.
- **N, S, E, O** : Se déplacer dans les directions cardinales.
- **P** : Prendre un objet.
- **D** : Déposer un objet.
- **I** : Ouvrir l'inventaire actuel.
- **A** : Accéder à l'aide.
- **R** : Récupérer un fragment spécifique.

---

### Situations Gagnantes et Perdantes

#### Situation Gagnante
- Assemblage complet de l'artefact dans le laboratoire.

#### Situations Perdantes
- Dépassement du temps limite.
- Mort par un ennemi ou un piège.
- Oubli d'un fragment essentiel.

---

### Déroulement d'une Partie Gagnante

1. Explorer Nouvelle-Dauréa pour obtenir des indices clés.
2. Passer par la Clairière des Souvenirs et convaincre Tarjan d'aider.
3. Traverser la Zone Désertique pour récupérer le fragment caché.
4. Faire des échanges avec Kosaraju au Camp Tarsis.
5. Survivre à la Grotte et récupérer un autre fragment.
6. Assembler l'artefact dans le Laboratoire Abandonné.

---

## Enigmes

### Cadenas
Indices :
- `317 => 0 Correct | 1 Mal placé`
- `240 => 1 Correct | 1 Mal placé`
- `017 => 0 Correct | 0 Mal placé`

**Solution** : `234`

### Simon
- Répéter une séquence de couleurs qui s'allonge à chaque tour.

### Juste Prix
- Deviner un code à 4 chiffres en un nombre limité de tentatives.

---

## Checkstyle

- Standards de codage respectés.
- Vérification automatique des conventions pour garantir un code propre.

---

**Fin du document.**

