# ForumJavaWS

# Contexte :
Il nous a été demandé de créer un forum via les technologies Java/Spring, en reprenant des compétences acquises lors d'un module de WebService

# Equipe :
Promo B2DEV Campus Academy 2020/2021
Ludovic Angenard
Adrien Meunier
Samuel Wroblewski


# Installation :

1. Cloner le repository git.

2. Créer une base de donnée sur PHPMyAdmin au nom de : forumjavaws avec un user root et aucun mot de passe. Votre base de données doit être sur le port 3308. Pour changer les paramètres par défauts veuillez les changer localement dans [application.properties](src/main/ressources/application.properties) .

3. Lancer le fichier : [ForumJavaWsApplication.java](src/main/java/com/ForumJavaWS/demo/ForumJavaWsApplication.java) L'application va créer les tables de la base de donnée et créer automatiquement 4 catégories ainsi que les différents rôles. si besoin, vous trouverez un dump sql [ici](src/main/ressources/forumjavaws.sql)

4. ouvrir PostMan et y importer la collection [collection](src/main/ressources/postmanCollection.json). pour s'inscrire en tant qu'ADMIN :
```
    {
        "email":"ludovic.angenard@campus.academy",
        "password":"123456"
    }
```
    Attention ! il faut bien vérifier que le token générer lors de la connexion est dans la variable d'environnement {{token}}.


## Notre parcours :



* Après avoir lu le PDF, nous avons longuement réfléchit sur le diagramme de classe avant de commencer à travailler. Cela nous a permis de nous mettre d'accord  sur différents éléments en équipe.

* Après avoir créer un projet via spring.io, nous avons fait le choix de copier/coller le pom.xml du tp3 que nous avons vu en cours. En effet, malgré avoir vu certaines dépendances en cours, nous voulions être sur d'avoir un ensemble de dépendances cohérentes.

* Puis nous avons commencé les entity en respectant scrupulesement les diagrammes de classes.

* Nous avons modifié ForumJavaWsApplication.java afin de créer les catégories directement dans le code comme demandé.

* Nous avons longuement réfléchit sur la façon de créer un Topic : nous étions parti sur une phase en deux étapes dans le TopicController, la 1ere étant de créer un topic, puis de l'y ajouter à une catégorie et faire pareil pour le post. Finalement nous avons trouver le moyen de créer le Topic à partir de CategoryController, afin qu'il soit directement relié à une catégorie tout en créant un post relié au Topic créer dans la même requete.

* (bien évidemment nous avons du modifier les entity pour y ajouter des cascades, fetchTypes etc ...) Nous avons eu un blocage sur le fait que les Types demandés par le cahier des charges étaient des Long et non pas des Integer, pour contourner ce problème, nous avons utilisé le polymorphysme dans les repository. C'est à dire que nous avons redéfinit la méthode mais en changeant le type du paramètre attendu.

* Par la suite, Nous nous sommes attaqués au Post, qui nous a semblé plus facile que Topic car nous avons compris un certain nombre d'élément en ayant travaillé sur la gestion des Topic.

* C'est une fois la base Category / Topic / Post bien solide que nous avons continué sur les reports et les users. Pour la logique des controlleurs et des modèles, cela a été plutôt rapide car une routine c'est installé depuis le début de l'exercice. Cependant, dès qu'est venu la gestion des droits utilisateurs avec la gestion des tokens et de l'authentification cela est devenu plus complexe car nous étions moins entrainés sur cette partie là. Nous avons donc regardez beaucoup de documentation sur Spring Security pour bien comprendre comment le jeton était générer et comme le user est authentifié. Une fois ceci compris, nous avonc pu accélérer le pas et finir ce tp.

* Enfin est venu de l'étape de vérification. Certes, nous avons vérifier notre code tout du long de son développement mais il est nécessaire de se mettre à la place de l'utilisateur à la fin de la création de l'A.P.I. Cela nous a permis de nous rendre compte qu'il y'avait des attributs manquant lors d'une combinaison de requêtes. Nous avons donc pris un peu de temps pour corriger tout ceci.

* Pour conclure, nous pensons avoir cochés toutes les cases du cahier des charges (excepté le bonus). Cependant, lors de la mise en situation dans les test PostMan nous pouvons critiquer le fait que le manque de réponses de l'A.P.I. lors de l'éxécution d'une requête peut être perturbant pour l'utilisateur. Par manque de temps, nous n'avons pas pu corriger cela.