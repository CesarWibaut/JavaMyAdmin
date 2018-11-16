# Projet 07 BDD

Sort of PHPMyAdmin in Java using servlets, maven and tomcat server

By César Wibaut


Pour se connecter sur la base de données, il suffit d'ajouter le driver JDBC adapté au pom.xml, et modifier les données de connexion.

Les paramètres de connexion se trouvent dans /src/main/resources/data.properties

## Pour lancer le serveur

`$ mvn package `

`$ mvn tomcat7:run`

## API

` /projet07/servlet-Select?table=table `  : List a database and allow you to insert data


