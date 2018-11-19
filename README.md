# Projet 07 BDD

Sort of PHPMyAdmin in Java using servlets, maven and tomcat server

By César Wibaut

In order to connect to your own database, you have to add the right JDBC driver to the pom.xml dependencies, and put the database connexion data in the /src/main/resources/data.properties file

## To run the server

`$ mvn tomcat7:run`

## API

`GET /projet07/servlet-Select?table=table `  : List a database and allows you to insert data

`POST /projet07/servlet-Delete ` : Delete an entry from the database

`POST /projet07/updateDB ` : Update an entry in the database

`GET /projet07/servlet-Update` : Get the update form with the current data in the inputs

`POST /projet07/servlet-Insert` : Add an entry in the database


