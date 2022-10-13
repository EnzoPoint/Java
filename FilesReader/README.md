# Lancement du Programme

Pour lancer le projet, il y a plusieurs méthode mais pour ma part j’ai opté pour la méthode que je trouve le plus simple, il suffit d’utiliser un logicielle du style d’eclipse qui va proposé de lancer le programme automatiquement, celui-ci sera exécutera le fichier src/MainReader.java qui se réfèrera aux main(). 

## Déroulement du Programme

Une fois le projet lancer, il va demander a l’utilisateur d’entrée deux PATH qui sont équivalents a nos deux fichiers, le PATH est situé de base dans du programme.

Il faudra alors se référer a /FilesReader/ donc pour mon fichier exemple1.txt il se trouvera dans /src/Exemple/exemple1.txt et le programme juste récupérer le getAbsolute.

Par défaut le programme lancera 3 étapes :

- Lecture des deux fichiers de manière normal
- Lecture des deux fichiers de manière inversée
- Lecture des deux fichiers de manière normal puis rajout de sa forme Palindrome.

J’ai garder pour l’instant l’affichage sous forme ArrayList pour le côté esthétique du programme mais il suffit de parcourir l’ArrayList pour l’afficher de manière normal

Gestion de l’erreur si jamais le path du fichier n’est pas trouvé le programme va juste avertir :

```java
System.out.println("Error : " + e.getMessage());
System.out.println("Ignore...");
```

Et va ensuite ignorer le problème.

## Screen du déroulement du Programme

![Capture d’écran 2022-10-13 à 09.55.06.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/83dda133-d1a5-4e13-ac83-e75cf07eab21/Capture_decran_2022-10-13_a_09.41.47.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221013%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221013T080050Z&X-Amz-Expires=86400&X-Amz-Signature=26a2c41179bbf8fe9ce8cb4d6795b00ae7b8eecc70b5f17982c04647056c43d3&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Capture%2520d%25E2%2580%2599e%25CC%2581cran%25202022-10-13%2520a%25CC%2580%252009.41.47.png%22&x-id=GetObject)

Nous pourrions optimiser le palindrome pour éviter de reverse les caractères du style “?,!..” mais pour l’instant ce n’est pas vraiment nécessaire.

## Diagram UML

![Capture d’écran 2022-10-13 à 09.41.47.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/10357f5e-7aa2-4c8f-bbc8-d7754e9bb62e/Capture_decran_2022-10-13_a_09.55.06.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221013%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221013T080037Z&X-Amz-Expires=86400&X-Amz-Signature=73cf03565c473db7100f506a566b99e7a9a89736d367f0b1fda2d21c358ac425&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Capture%2520d%25E2%2580%2599e%25CC%2581cran%25202022-10-13%2520a%25CC%2580%252009.55.06.png%22&x-id=GetObject)

---
