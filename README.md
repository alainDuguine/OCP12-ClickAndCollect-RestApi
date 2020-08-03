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
  
  ### Déploiement de la base de données avec Docker et chargement jeu de données:
    
   A partir du répertoire : ```/docker/``` 
   exécutez la commande : ```docker-compose up```
  
  ### Déploiement de la base de données en local et chargement jeu de données:
  
  Dans postgreSQL créer une base de données nommée ```db_clickandcollect```,
  Puis exécutez les scripts présents dans le répertoire ```/docker/sql``` dans l'ordre ```(01_Schema.sql puis 02_Data.sql)```
  
  ### Variables d'environnement :
    
   Pour l'exécution via Maven plusieurs variables d'environnement sont nécessaires :
         
   ```POSTGRESQL_ADDON_URI```: url de la base de données (pour docker : ```jdbc:postgresql://localhost:9032/db_clickandcollect```,
                               pour une instance locale de postgreSQL : ```jdbc:postgresql://localhost:5432/db_clickAndCollect```)
   
   ```POSTGRESQL_ADDON_USER```: utilisateur de la base de données (postgres)
     
   ```POSTGRESQL_ADDON_PASSWORD```: mot de passe de l'utilisateur de la base de données (admin)
   
  ## Démarrage de l'application :
          
   Pour créer les mappers exécuter la commande ```mvn clean package -DskipTests```
          
   `Pour démarrer le service rest, exécuter la commande ```mvn spring-boot:run``` depuis ```/clickandcollect-api/clickandcollect-api-webservice```
         
   *Les commandes ```mvn``` peuvent être remplacées par le wrapper Maven ```mvnw```*
