# ClickAndCollect-API
[![CircleCI](https://circleci.com/gh/alainDuguine/ClickAndCollect-RestApi.svg?style=svg)](https://circleci.com/gh/alainDuguine/ClickAndCollect-RestApi)
[![codecov](https://codecov.io/gh/alainDuguine/ClickAndCollect-RestApi/branch/master/graph/badge.svg)](https://codecov.io/gh/alainDuguine/ClickAndCollect-RestApi)

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
  
   Déploiement de la base de données avec Docker:
   
    à partir du répertoire : ``/docker/dev/``
    exécutez la commande : ``docker-compose up``
  
    Variables d'environnement :
    
     Pour l'exécution via Maven plusieurs variables d'environnement sont nécessaires :
      
     ```IP_SERVER``` : pour du local "localhost"    
     
     ```API_PORT``` : par exemple 8080
     
     ```POSTGRESQL_ADDON_URI```: url de la base de données (jdbc:postgresql://localhost:5432/db_clickAndCollect)
     
     ```POSTGRESQL_ADDON_USER```: utilisateur de la base de données (adm_clickAndCollect)
     
     ```POSTGRESQL_ADDON_PASSWORD```: mot de passe de l'utilisateur de la base de données (admin)
          
  ## Démarrage des applications :
          
         exécuter la commande ```mvn spring-boot:run``` depuis ```/clickandcollect-api/clickandcollect-api-webservice>
         
   *Les commandes ```mvn``` peuvent être remplacées par le wrapper Maven ```mvnw```*
