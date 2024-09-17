# Gestion des tâches - Spring Boot

## Description

Ce projet est une application de **gestion de tâches** permettant aux utilisateurs d'assigner des **tâches** à des **membres** d'une **équipe**. Il y'a possibilité de créer des membres, des projets, des tâches, des notifications et des équipes.Quant à la notification, on peut en plus :  **supprimer** et **obtenir un/tous les éléments**  tandisque pour les autres entités , il y'a possibilité de :
- **modifier**
- **obtenir un/tous les éléments** et
- **supprimer**

L'application expose également des **API REST** pour la gestion des données.

## Fonctionnalités

- **Gestion des projets** : Créer et lister des forums, obtenir les détails d'un forum.
- **Gestion des sujets** : Chaque forum contient des sujets créés .
- **Gestion des messages** : Répondre aux sujets par des messages.
- **API REST** : Toutes les entités (Forum, Sujet, Message) sont accessibles via des endpoints RESTful.

## Structure du projet

Les principales entités du projet sont :
- **Bas'e sur l'achitecture en couche**

1. **Forum** : Représente un espace de discussion contenant plusieurs sujets.
2. **Subject (Sujet)** : Un sujet appartient à un forum et contient des messages.
3. **Message** : Un message est la réponse d'un utilisateur à un sujet.

## Technologies utilisées

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** pour l'accès aux données.
- **Hibernate** pour la gestion ORM.
- **PostgreSQL** pour la base de données.
- **Maven** pour la gestion des dépendances.
- **Lombok** pour simplifier le code (réduire les getters/setters).
- **PostMan** documentation de l'API.  


## Prérequis

- **Java 17+**
- **Maven**
- **PostgreSQL** 

## Installation

- **Clone project on your local machine**
    ```bash
    git clone https://github.com/attoubo/Forum-V1.git
    ```

- **Got to project directory**
    ```bash
    cd forum-V1
    ```

- **Setting up your application.proprieties file**
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5433/(yourdatabase_name)
    spring.datasource.username=(your username)
    spring.datasource.password=(your password)
    spring.jpa.hibernate.ddl-auto=create
    ```


- **Command for run project**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. L'api sera accessible à l'adresse [http://localhost:8080/api](http://localhost:8080/api).


### Documentation de l'api disponible via ce lien: [https://documenter.getpostman.com/view/7602674/2sAXqngkYz](https://documenter.getpostman.com/view/7602674/2sAXqngkYz).
