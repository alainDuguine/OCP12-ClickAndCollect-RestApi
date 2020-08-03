# ClickAndCollect-API
[![CircleCI](https://circleci.com/gh/alainDuguine/ClickAndCollect-RestApi.svg?style=svg)](https://circleci.com/gh/alainDuguine/ClickAndCollect-RestApi)

  Ce repository est l'Api Rest du projet n° 12 du parcours Java d'OpenClassrooms.
  L'application permet la mise en place de vente à emporter simple pour les commerces de bouche durant l'épidémie de Covid-19.
  
  ## Spécifications
  
  Projet Java/Spring-boot/Maven multi module exposant une Api Rest 
    
  ## Installation
  
    * Prérequis :
      * Java Jdk 11
      * Spring Boot 2.3.0.RELEASE
      * Spring Security
      * Apache Maven 3.6.1
      * PostgreSql 12
      * Apache Tomcat 9
      * Swagger
  
  ## Déploiement
  
  ### Déploiement de la base de données avec Docker et chargement du jeu de données:
    
   A partir du répertoire : ```/docker/``` 
exécutez la commande : ```docker-compose up```
  
  ### Déploiement de la base de données en local et chargement du jeu de données:
  
  Dans postgreSQL créer une base de données nommée ```db_clickandcollect```,
  Puis exécutez les scripts présents dans le répertoire ```/docker/sql``` dans l'ordre ```(01_Schema.sql puis 02_Data.sql)```
  
  ### Variables d'environnement :
    
   Pour l'exécution via Maven plusieurs variables d'environnement sont nécessaires :
         
   ```POSTGRESQL_ADDON_URI```: url de la base de données (pour docker : ```jdbc:postgresql://localhost:9032/db_clickandcollect```,
                               pour une instance locale de postgreSQL : ```jdbc:postgresql://localhost:5432/db_clickAndCollect```)
   
   ```POSTGRESQL_ADDON_USER```: utilisateur de la base de données (```postgres```)
     
   ```POSTGRESQL_ADDON_PASSWORD```: mot de passe de l'utilisateur de la base de données (```admin```)
   
   ```PHOTO_PATH```: pour l'upload des photos de chaque restaurant
   
   *Note: Si vous exécutez l'api Rest depuis Intelli-J (Maj+F10), les variables sont configurables dans Run/Debug configurations.
   Si vous lancez l'api en ligne de commande, il faudra les spécifier dans votre système.*
   *Il est également possible de spécifier directement les valeurs dans le fichier application.properties, sans passer par les variables d'environnements*
   
  ## Démarrage de l'application :
          
   Pour générer l'implémentation des mappers et envoyer les différents modules dans votre repository local mvn exécutez la commande ```mvn clean install -DskipTests``` depuis ```/clickandcollect-api```
          
   Pour démarrer le service rest, exécuter la commande ```mvn spring-boot:run``` depuis ```/clickandcollect-api/clickandcollect-api-webservice```
         
   *Les commandes ```mvn``` peuvent être remplacées par le wrapper Maven ```mvnw```*

Le service Rest est en marche, vous pouvez l'essayer avec un client http, ou bien démarrer l'application cliente Angular sur le repository [ClickAndCollect-WebApp](https://github.com/alainDuguine/ClickAndCollect-WebApp)
