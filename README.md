# Test Project

[EN] 

This is a Spring Boot application that expose a REST API: "UserApi", that contains two endpoints:

1. "POST: /users/register": Endpoint to register a new user.
2. "GET: /users/{userId}": Endpoint fetch a user by its id.

[FR]

Il s'agit d'un projet Spring Boot qui contient une API Rest "UserApi", qui expose
deux endpoints REST:

1. "POST: /users/register": Endpoint pour ajouter un nouvel utilisateur.
2. "GET: /users/{userId}": Endpoint pour la récupération d'un utilisateur par son id unique.

## Modèle / Domain model
[
EN] 

The domain model is simple:

1. A user represented by the class [User](src/main/java/org/machinestalk/domain/User.java)
belong to one department ([Department](src/main/java/org/machinestalk/domain/Department.java))

2. Several users can belong to the same department.

[FR]

Le modèle est simple:

1. Un utilisateur représenté par la classe [User](src/main/java/org/machinestalk/domain/User.java)
appartient à un seul département (classe [Department](src/main/java/org/machinestalk/domain/Department.java))
et peut avoir une ou plusieurs adresses (classe [Address](src/main/java/org/machinestalk/domain/Address.java)
   
2. Plusieurs utlilisateurs peuvent appartenir au meme departement.

## Ce qui est demandé / What is requested

[EN]

The project as is is not functional.

* There are compilation errors => they must be corrected
* Some test fails => corrections must be made for these tests to work again
* There are no tests for the API layer => they must be written

The goal is to make this Spring Boot project work and have working endpoints.
The database to use is a memory base (H2 database)

[FR]

Le projet en l'état n'est pas fonctionnel:

* Il y'a des erreurs de compilations => il faut les corriger
* Certain test échoue => il faut apporter des corrections pour que ces tests fonctionnent de nouveau
* Il n'y a pas de tests pour la couche API => il faut les écrire

L'objectif étant de faire fonctionner ce projet Spring Boot et avoir des endpoints qui fonctionnent.
La base de donnée à utiliser est une base mémoire (H2 database)


  
