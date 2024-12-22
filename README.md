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

Le jeu comporte six zones connectées par des sorties directionnelles (visibles ou invisibles et fermées ), certaines avec retour possible et d’autres sans retour.

#### 1. Nouvelle-Dauréa
- Le jeu commence dans la ville Nouvelle-Dauréa. Pour progresser, Soren doit résoudre une énigme afin d’obtenir un indice qui l'oriente vers la prochaine zone. Plusieurs objets sont disponibles dans cette zone : une carte, essentielle pour naviguer, ainsi qu'une clé végétale et de l'eau bénite, des objets mystiques ayant des pouvoirs spécifiques qui pourraient être utiles plus tard. Un point de commerce permet également au joueur d'acheter un fragment précieux, mais ce dernier est momentanément inaccessible, car gardé par le gardien Kosaraju. Un message s'affiche à l'écran pour rappeler au joueur que son sac ne peut contenir que cinq objets à la fois. Il lui est donc conseillé de faire le bon choix, sachant qu'il peut déposer des objets pour en prendre d'autres si la limite est atteinte. Enfin, deux sorties fermées sont présentes dans cette zone (N) et (E), permettant au joueur de choisir sa direction, mais il doit d'abord résoudre l'énigme et sélectionner les objets nécessaires pour la suite de son aventure.

#### 2. La Clairière des Souvenirs (Forêt mystique)
- Dans cette zone, certains chemins sont bloqués par des ronces, nécessitant un objet pour les franchir. Tarjan, le gardien de la forêt, présente une énigme à résoudre pour découvrir l’emplacement des objets suivants : le deuxième fragment, une corde, et un premier indice pour l'énigme finale. Deux sorties visibles sont présentes, marquées (S) et (O), et il est également possible de revenir dans cette zone.


#### 3. La Zone Désertique
- La zone désertique présente des énigmes basées sur les chiffres et des indices visuels pour récupérer un éclat de cristal. Cet éclat peut être vendu au point de commerce en échange du premier fragment et du deuxième indice pour l’énigme finale. Deux sorties visibles sont présentes, marquées (B) et (S), et un retour est possible vers les zones précédentes.

#### 4. Camp Tarsis
- Dans cette zone, Soren doit voler un troisième fragment caché dans un coffre sécurisé par une énigme. Le coffre contient aussi des objets utiles, comme une torche (indispensable pour la prochaine zone), une potion de temps, et un troisième indice. Le joueur doit bien choisir les objets à prendre, car le coffre est surveillé par Ravok le Dévastateur, membre de la faction Hostile. Un timer est en place : lorsque le soldat tourne le dos, le joueur peut ouvrir le coffre. Si le temps expire sans ouvrir le coffre, le soldat se retourne et tue l’aventurier. La sortie de la zone est une fosse invisible, accessible une fois l'énigme résolue. Le joueur y tombe et se retrouve dans une grotte, une pièce totalement noire. Il n'est plus possible de revenir dans cette zone ni dans les zones précédentes.

#### 5. La Grotte
- Dans cette zone, le joueur se retrouve dans un labyrinthe obscur avec un temps limité. Il doit impérativement utiliser la torche (récupérée au préalable) pour explorer dans le noir. L’objectif est de trouver le quatrième et dernier fragment, ainsi que la sortie de la pièce invisible, avant que le temps ne s’écoule. Cette zone est sans retour : une seule sortie existe, et si le joueur échoue, il doit recommencer l’aventure depuis le début, perdant tous les objets, indices et fragments déjà collectés.

#### 6. Laboratoire Abandonné
- Il s'agit de la zone finale, où se trouve une machine conçue pour assembler les quatre fragments. Pour débloquer cette machine, le joueur doit résoudre une énigme finale en utilisant les trois indices récoltés précédemment. S'il oublie un fragment ou s'il échoue à résoudre l'énigme, le joueur perd et doit recommencer l'aventure depuis le début, en perdant tous les objets et fragments collectés.


Chaque zone inclut des éléments interactifs, des énigmes, des obstacles, des objets et des indices. Les sorties peuvent être classiques (N, S, E, O, B, H), visibles ou invisibles ou fermées, certaines sans retour.

---

### Objets, Indices et Fragments

- **Carte** : Permet au joueur de visualiser l’agencement des zones de manière globale. Elle indique également les connexions entre les zones (retours possibles ou non). et le nombre de fragments. N’affiche pas les détails des énigmes ou des emplacements exacts des objets.
- **Eau Bénite** : Détruit les ronces dans La Clairière des Souvenirs, ouvrant des chemins bloqués.
- **Corde** : Utilisée pour traverser des obstacles physiques.
- **Potion de Temps** : accorde un délai supplémentaire lors d’énigmes chronométrées.
- **Fragments d'Artefact** : Dissimulés dans chaque zone, ils sont indispensables pour réussir la quête.
- **Eclat de cristal** : Un objet précieux pouvant être vendu au point de commerce pour récupérer le premier fragment dans Nouvelle-Dauréa.
- **Trois indice** : Pour aider à trouver l'énigme finale.
- **Indice initial** : en rapport avec la position de la deuxième zone.
- **Torche** : Essentielle pour explorer La Grotte . Elle permet d’illuminer les chemins et de découvrir des mécanismes invisibles dans l’obscurité.
- **Clé végétale** : Ouvre une porte bloquée dans la forêt.
- **Fragment d'artefact** : Collecté dans chaque zone.Requis pour assembler **L'Oculus du Jugement**.dans Le Laboratoire Abandonné.

Le joueur peut transporter ces objets dans un sac à dos limité à cinq objets, obligeant à des choix stratégiques. La commande I pour Inventaire permet de savoir combien d’objets il a dans son sac.

---

### Les Personnages

- **Kael Soren** : Jeune aventurier.
- **Tarjan** : Le gardien de la forêt.
- **Kosaraju** : Le gardien du point de commerce.
- **Ravok le Dévastateur** : Membre de la faction Hostile.

---

### Commandes Disponibles

- **Q** : Quitter le jeu.
- **N, S, E, O** : Se déplacer dans les directions cardinales.
- **H, B** : Se déplacer en hauteur (Haut, Bas).
- **R** : Revenir à la zone précédente (Retour).
- **P** : Prendre un objet.
- **D** : Déposer un objet.
- **I** : Ouvrir l'inventaire actuel.
- **A** : Accéder à l'aide.
- **T** : Test.
- **P** : Parler.
- **V** : Vendre.
- **F** : Fragments.
- **V** : Voir les indices.


---

### Situations Gagnantes et Perdantes

#### Situation Gagnante
- Assemblage complet de l'artefact dans le laboratoire.

#### Situations Perdantes
- Dépasser le temps maximum dans le labyrinthe.
- Oublier de collecter un fragment essentiel.
- Se faire tuer par Ravok le Dévastateur, membre de la faction hostile, dans le Camp Tarsis.


---

### Déroulement d'une Partie Gagnante

Kael Soren commence son aventure dans la ville antique de Nouvelle-Dauréa, où il résout une énigme ("J'ai un corps sans vie, une voix sans âme. Qui suis-je ?") en répondant un écho, ce qui débloque la sortie vers la Clairière des Souvenirs. Il collecte des objets essentiels comme une carte, une clé végétale, et de l’eau bénite tout en respectant la limite de cinq objets dans son sac. Arrivé dans la Clairière des Souvenirs, il rencontre Tarjan, le gardien, qui lui pose une énigme basée sur ses observations précédentes ("Combien d'arbres avez-vous vus avant d’arriver ici ?", réponse : "7"). En résolvant l’énigme, Soren obtient un fragment d’artefact, une corde, et un premier indice pour l’énigme finale. Grâce à l’eau bénite, il élimine les ronces bloquant son chemin et progresse vers la Zone Désertique.

Dans cette zone, Kael résout une énigme numérique ("La somme des deux premiers chiffres est égale à 9, mais le troisième chiffre est leur différence", réponse : "63") pour obtenir un éclat de cristal. Il échange cet objet précieux au point de commerce contre un autre fragment d’artefact et un deuxième indice. Après avoir traversé un obstacle à l’aide de la corde, il atteint le Camp Tarsis, où il récupère un troisième fragment d’artefact en ouvrant un coffre sécurisé tout en évitant Ravok le Dévastateur grâce à des mouvements stratégiques et rapides. Dans le coffre, il trouve également une torche et une potion de temps, indispensables pour explorer la prochaine zone. Une fosse invisible l’amène ensuite à la Grotte, un labyrinthe obscur où il utilise la torche pour naviguer et trouver le dernier fragment d’artefact avant que le temps imparti ne s’écoule.

Enfin, Soren arrive au Laboratoire Abandonné, où il assemble les quatre fragments à l’aide des indices collectés lors de son aventure. Après avoir résolu l’énigme finale, il active la machine et crée l’Oculus du Jugement, marquant ainsi la victoire et la fin de son périple.

---

## Enigmes

### Cadenas
Dans une zone il y a un cadenas avec un code à 3 chiffres qu’il faut débloquer pour accéder à la prochaine zone/salle.
A côté, on peut trouver une feuille avec les indications suivantes : 

**Indices :**
- `317 => 0 Correct | 1 Mal placé`
- `240 => 1 Correct | 1 Mal placé`
- `017 => 0 Correct | 0 Mal placé`

**Solution** : `234`

### Simon
C’est un jeu de mémoire où le héros va faire face à une suite de symboles/couleurs qui défile dans le terminal. Il va devoir retenir la suite de symboles qui évolue au fur et à mesure.

**Exemple :**
`Au début le symbole @ apparaît, le joueur va devoir écrire @ à son tour,
Puis les symboles @ # apparaissent, le joueur va devoir écrire @ # à son tour.
Puis les symboles @ # $ apparaissent, le joueur va devoir écrire la même suite à son tour et ainsi de suite jusqu’à atteindre une suite de 6 symboles qui valide l’énigme et fait passer le joueur à la suite.
`

### Juste Prix
C’est un jeu où pour trouver un code à 4 chiffres, le joueur doit proposer à un PNJ des nombres et celui-ci lui répondra “Plus” ou “Moins” en fonction de la valeur qu’il cherche dans un temps imparti de 60 secondes, au bout duquel l’énigme sera échouée.

**Exemple :**
- `Le joueur propose (écrit dans le terminal) au PNJ 2000,`
- `Le PNJ répond “Plus”,`
- `5000,`
- `"Plus",`
- `.....`
- `5693`

**Solution** : `5693`

Le joueur propose 5693. C’est la bonne réponse, il peut passer à la suite. S' il ne trouve pas la bonne réponse, le jeu est perdu.
Ainsi, le joueur peut entrer le code qu’il a trouvé pour passer un obstacle.


### L’énigme du choix

Pour ce jeu, le joueur a le choix entre deux possibilités, soit prendre une question compliquée pour obtenir un indice très important et qui donne de très bonnes indications au joueur ou bien une énigme plus simple pour obtenir un indice moins utile mais tout de même important.

**Exemple de la question difficile :**

- `"Je suis petit mais précieux,
 Dans l’ombre, je reste silencieux.
 Trouve-moi pour ouvrir la voie,
Car sans moi, tout s’arrête là."`

**Exemple de la question simple :** 

- `"Je suis petit et métallique,
J’ouvre les serrures, je suis pratique.
Sans moi, les portes sont fermées,
Je suis donc utile pour les déverrouiller."`

Qui suis-je ?

**Solution** : `Une clé`.

Si le joueur se trompe dans sa réponse à l’énigme, il n’obtient pas l’indice.

### Enigme de mémoire

Le joueur arrive devant un obstacle où une question lui est posée à propos de ce qu’il a vu auparavant.

**Exemple** : `Quel est le nom de l’artéfact ?`

**Solution** : `Oculus du Jugement`

### Enigme finale

Lorsque le joueur se trouve devant la machine de fin, lui permettant de rassembler les fragments de l’artefact, une énigme va lui être posée et le joueur aura accès à 3 indices.

**Exemple :** 
- `“Je suis un gardien sans yeux, sans bras ni voix,
Pourtant, je protège tout ce que tu tiens de précieux.
Je peux être caché dans un coin discret ou bien en évidence,
Mais tout le monde sait qu'un jour ou l'autre, je serai ouvert.
Ma clé est souvent d'or, mais parfois de fer,
Et dans mon ventre, tu trouveras des secrets, des biens ou des pierres.”`


---

## Checkstyle
Nous avons décidé d'adopter le checkstyle Java de Google. Cette décision repose sur le fait qu'il s'agit de l'un des checkstyles les plus connus et les plus utilisés. Par conséquent, nous considérons qu'il est plus pertinent d'apprendre à utiliser celui-ci.

- **Conventions** : [Google Java Style Guide](https://checkstyle.sourceforge.io/styleguides/google-java-style-20220203/javaguide.html#s1-introduction)
- **Configuration** : [Google Checks XML](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)

---

