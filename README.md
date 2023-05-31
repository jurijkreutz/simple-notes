# Simple Notes

This is an additional bonus project which I started working on during the `Advanced - Java Spring` module of the Codecool Full Stack Developer Course.

It is a project with a Java Spring Backend and a React Frontend. Users can log in, add new notes and manage old ones.

## Main technologies used
 - Spring Boot
 - Spring Web MVC, Spring Data JPA, Spring Security
 - H2 Database
 - React
 - Material UI Library

## Run project

1. Clone the project
```ssh
git clone git@github.com:jurijkreutz/simple-notes.git
```

2. Start backend from the backend directory
```ssh
cd backend
mvn spring-boot:run
```

3. Start frontend from the frontend directory
```ssh
cd frontend
npm run dev
```

## Current State

### Already done

- React Frontend with Register Page, Login Page, Note Overview, Add Note Page
- Spring Backend with endpoints for CRUD operations
- Spring Security with H2 DB Users
- JWT handling for Spring Security
- Spring Data with H2 DB for saving notes / users
- Unit Tests covering the current backend

### To Do

- Dockerize

## Screenshots

<img width="920" alt="login" src="https://github.com/jurijkreutz/simple-notes/assets/104159382/d52ad133-4b73-4a39-b26a-bf51a0c5ae70">

<img width="930" alt="add-note" src="https://github.com/jurijkreutz/simple-notes/assets/104159382/0ac3c6a6-461b-4236-a8be-c8e9c5516205">
