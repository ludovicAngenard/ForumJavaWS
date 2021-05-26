# ForumJavaWS

Contexte : 
Il nous a été demandé de créer un forum via les technologies Java/Spring, en reprenant des compétences acquises lors d'un module de WebService

Equipe :

Ludovic Angenard
Adrien Meunier
Samuel Wroblewski


Mise en place de l'application : 

1) 
    créer une base de donnée sur PHPMyAdmin au nom de : forumjavaws
2)
    ouvrir le projet.
3) 
    lancer le fichier : ForumJavaWsApplication.java
    l'application va créer les tables de la base de donnée et créer automatiquement 4 catégories ainsi que les différents rôles.
    si besoin, vous trouverez un dump sql en piece jointe

4)
    ouvrir PostMan et y importer la collection en pièce jointe dans le mail.
    pour s'inscrire en tant qu'ADMIN :
    {
        "email":"ludovic.angenard@campus.academy",
        "password":"123456"
    }
    Attention ! il faut bien copier coller le token récupéré lors d'un login dans chacune des requetes à effectuer avec le compte en question.


Notre parcours :



Après avoir lu le PDF, nous avons longuement réfléchit sur le diagramme de classe avant de commencer à travailler.
Cela nous a permis de nous mettre d'accord  sur différents éléments en équipe.

Après avoir créer un projet via spring.io, nous avons fait le choix de copier/coller le pom.xml du tp3 que nous avons vu en cours.
En effet, malgré avoir vu certaines dépendances en cours, nous voulions être sur d'avoir un ensemble de dépendances cohérentes.

Puis nous avons commencé les entity en respectant scrupulesement les diagrammes de classes.

Nous avons modifié ForumJavaWsApplication.java afin de créer les catégories directement dans le code comme demandé

Nous avons longuement réfléchit sur la façon de créer un Topic : 
nous étions parti sur une phase en deux étapes dans le TopicController, la 1ere étant de créer un topic, puis de l'y ajouter à une catégorie et faire pareil pour le post.
Finalement nous avons trouver le moyen de créer le Topic à partir de CategoryController, afin qu'il soit directement relié à une catégorie tout en créant un post relié au Topic créer dans la même requete.

(bien évidemment nous avons du modifier les entity pour y ajouter des cascades, fetchTypes etc ...)
Nous avons eu un blocage sur le fait que les Types demandés par le cahier des charges étaient des Long et non pas des Integer,
pour contourner ce problème, nous avons utilisé le polymorphysme.

Par la suite, Nous nous sommes attaqués au Post, qui nous ont semblé plus facile que Topic car nous avons compris un certain nombre d'élément en ayant travaillé sur la gestion des Topic.





