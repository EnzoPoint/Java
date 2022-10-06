# Lancement du Programme

Pour lancer le projet, il y a plusieurs méthode mais pour ma part j’ai opté pour la méthode que je trouve le plus simple, il suffit d’utiliser un logicielle du style d’eclipse qui va proposé de lancer le programme automatiquement, celui-ci sera exécutera le fichier src/Bataille \de \Cartes/Bataille.java qui se réfèrera aux main(). 

# Règle du jeu

La règle du jeu est simple 1 paquet de 52 cartes, distribuées en 2 tas réparties aux 2 joueurs, celui qui a la plus haute carte remporte et il ramasse alors les deux cartes.

Toutefois une bataille est possible, cela se produit lorsque les deux joueurs ont les deux mêmes cartes par exemple s'ils ont la carte 2 en même temps, alors une bataille commence.

Les deux joueurs vont alors dévoiler une carte chacun en même temps de manière caché puis une autre cette fois-ci face découverte.

C’est le joueur qui gagnera ce nouveau duel qui remportera toutes les cartes qui se seront amassées durant cette bataille, si une deuxième bataille se produit alors les deux joueurs reprennent leurs cartes et les mélangés.

Ainsi de suite jusqu’à ce que 1 des joueurs ne soit plus de carte et ça sera alors la personne perdante.

# Déroulement du Programme

Une fois le programme lancé, celui-ci va commencé par crée deux joueurs qui se verront attribuer un pseudo random, ces deux joueurs vont recevoir chacun 26 cartes qui auront au préalablement été mélangées.

Le programme va ensuite détailler chaque manche effectuer et quelles joueurs a gagné la manche, le programme suit les règles écrit plus haut dans la documentation.

Une fois que l’un des deux joueurs n’a plus de carte en main, le programme va alors définir un gagnant.

# Schéma UML

![Capture d’écran 2022-10-06 à 21.11.40.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/7e648cbb-7a9d-4980-842a-668cec6d39a3/Capture_decran_2022-10-06_a_21.11.40.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221006%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221006T194046Z&X-Amz-Expires=86400&X-Amz-Signature=ffaa82a804381982331dea3abb1d7072fa505e1ed865f4216c7e41fe4484b893&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Capture%2520d%25E2%2580%2599e%25CC%2581cran%25202022-10-06%2520a%25CC%2580%252021.11.40.png%22&x-id=GetObject)
