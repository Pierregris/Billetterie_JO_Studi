# Application web : billetterie des JO 2024
Application web pour la gestion de la billetterie des JO 2024 : affichage des offres et des événements, gestion des commandes et des billets.  

## Introduction:
Application développée dans le cadre du bachelor concepteur développeur d'applications Java par HETIC, via Studi.  
Application Spring Boot Web

## Environnement nécessaire:
Java 17 ou supérieur 
Maven 3.6 ou supérieur
Une base de données opérationnelle (voir paragraphe ci-après) 
Compte messagerie pour l'envoi des mails  

## Cloner le répository github suivant:
https://github.com/Pierregris/Billetterie_JO_Studi.git

## Créer une base de données opérationnelle
1. Connectez vous sur le site https://admin.alwaysdata.com/
    Si vous n'avez pas de compte, cliquez sur "S'inscrire"
    Si vous avez un compte, renseignez vos identifiants

2. Cliquez à gauche sur "Bases de données" puis "MySQL"
3. Cliquez sur le bouton "Ajouter une base de données"
4. Choisissez le nom de votre base de données
5. Notez bien :
    - le nom de la base de données
    - le nom de l'utilisateur propriétaire des droits
    - le mot de passe associé
    - l'url de la base de données (par exemple : jdbc:mariadb://mysql-username.alwaysdata.net/dbname)
    

## Renseignez les variables d'environnement suivantes sur votre machine
### Url de base
'${APP_BASE_URL}' : url de base de l'application (par exemple : http://localhost:8081)

### Profil
'${PROFIL}' = local pour un lancement en local

### Accès à la base de données
'${DB_URL}' : l'url de la base de données
'${DB_USERNAME}' : nom d'utilisateur de la base de données  
'${DB_PASSWORD}' : mot de passe de la base de données

### Accès à la messagerie
'${MAIL_USERNAME}' : email utilisé pour l'envoi des mails par l'application  
'${MAIL_PASSWORD}' : mot de passe d'application du compte email (par ex. gmail)  

## Lancement de l'application
mvn spring-boot:run
Le port utilisé par défaut est le 8081

## Tests
L'application intègre une série de tests que vous pouvez lancer avec la commande :
mvn test

