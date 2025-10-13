# Application web : billetterie des JO 2024
Application web pour la gestion de la billetterie des JO 2024 : affichage des offres et des événements, gestion des commandes et des billets.  

## Introduction:
Application développée dans le cadre du bachelor concepteur développeur d'applications Java par HETIC, via Studi.  
Application Spring Boot Web

## Environnement nécessaire:
Java 17 ou plus  
Maven 3 ou plus  
Base de données SQL (mariaDB, mySQL)  
Compte messagerie pour envoi des mails  

## Cloner le répository github suivant:
https://github.com/Pierregris/Billetterie_JO_Studi.git

## Renseignez les variables d'environnement suivantes sur votre machine
### Url de base
'${APP_BASE_URL}' : url de base de l'application (par exemple : http://localhost8081)

### Accès à la base de données
'${DB_USERNAME}' : nom d'utilisateur de la base de données  
'${DB_PASSWORD}' : mot de passe de la base de données

### Accès à la messagerie
'${MAIL_USERNAME}' : email utilisé pour l'envoi des mails par l'application  
'${MAIL_PASSWORD}' : mot de passe d'application du compte email (par ex. gmail)  

## Lancement de l'application
Le port utilisé par défaut est le 8081

