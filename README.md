# paymybuddy
# Créer le projet
Pour créer un projet spring boot il faut se rendre sur cette page : https://start.spring.io/.
Cette page est une aide pour construire le projet. il faut remplir avec le nom de groupe et le nom d'artifact que l'on souhaite. (par example: GROUP : com.talan et ARTIFACT: spring-boot-tuto).
Dans la partie Dépendencies j'y ai mis jpa et H2 database on peut en rajouté par la suite.

Il faut ensuite ouvrir le dossier avec IntelliJ ou Eclipse. 

# Configuration

Config in file applications.properties:
- > server.port=8080

- spring.datasource.url=jdbc:mysql://localhost:3306/paymybuddy?serverTimezone=UTC

- spring.datasource.username=root

- spring.datasource.password=Root123.

- spring.jpa.hibernate.ddl-auto=update

- spring.jpa.show-sql=true

# Structure 
Créer dossier config pour config spring security. 

Ensuite dossiers controller, model, repository, service et exception

# Diagramme de classe UML 
![UML](https://github.com/kimvu264/paymybuddy/files/8148448/Diagramme.UML.transfert.d.argent.pdf)

# Le modèle physique de données
![MPD2](https://user-images.githubusercontent.com/71970977/155877923-24c99497-05a9-4b86-bfab-5810f350a5ee.png)
