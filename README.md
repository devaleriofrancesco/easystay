<p align="center">
  <img src="https://github.com/devaleriofrancesco/easystay/blob/master/easystay-frontend/src/assets/images/logo2.png?raw=true" alt="Sublime's custom image"/>
</p>

<p align="center">
    <i>EasyStay - Soggiorni smart, prenotazioni Easy</i>
</p>

## Overview

EasyStay è un'applicazione web per la gestione di prenotazioni di camere d'albergo. L'applicazione consente agli utenti di visualizzare e prenotare camere disponibili, nonché di gestire le proprie prenotazioni. Gli amministratori possono gestire le camere e le prenotazioni degli utenti.

Progetto di tesi per il corso di laurea in Informatica per le aziende digitali (L-31) presso l'Università telematica Unipegaso.

Studente: Francesco Paolo De Valerio 
Matricola: 0312200004

## Requisiti
- Java 23
- Apache Maven 3.9.9
- NodeJs 23.5.0
- Npm 10.9.2
- Docker

## Backend (BE)

Il backend è costruito utilizzando Spring Boot e fornisce un'API RESTful per la gestione delle camere, delle prenotazioni e degli utenti.
La sicurezza è implementata tramite meccanismi di autenticazione e autorizzazione JWT.

## Frontend (FE)
Il frontend è costruito utilizzando VueJS e consente agli utenti di visualizzare e prenotare le camere della struttura. Gli amministratori possono gestire gli alloggi e le prenotazioni degli utenti.

### Struttura del progetto

- `src/main/java/com/devaleriofrancesco/easystay`: Contiene il codice backend dell'applicazione.
- `easystay_frontend`: Contiene il codice frontend dell'applicazione.

## Ambiente di produzione / TEST con Docker

Per avere un ambiente già pronto con tutte le dipendenze richieste, è stato utilizzato docker. Se si vuole utilizzare docker, ecco il comando da effettuare per il setup:

 ```sh
    docker compose up -d
```

Questo comando andrà a leggere il file docker-compose.yml che contiene la lista dei servizi da inizializzare. Sia il progetto di backend che il progetto di frontend hanno i loro Dockerfile, dei file con istruzioni che docker utilizza per creare immagini personalizzate, partendo da immagini base.

Il frontend sarà disponibile all'indirizzo `http://localhost`  
Il backend sarà disponibile all'indirizzo `http://localhost:8080`

## Istruzioni per contribuire allo sviluppo

1. **Clona repository**:
    ```sh
    git clone https://github.com/devaleriofrancesco/easystay-backend.git
    ```

2. **Build Mavend - Download dipendenze**:
    ```sh
    mvn clean install
    ```

3. **Run dell'applicazione Spring Boot**:
    ```sh
    mvn spring-boot:run
    ```

4. **Spostarsi nella cartella del progetto frontend**:
    ```sh
    cd easystay-frontend
    ```

5. **Copia del file .env.dist in .env**
    ```sh
    cp .env.dist .env
    ```
   Cambiare i puntamenti al backend!

5. **Installazione dipendenze**:
    ```sh
    npm install
    ```

6. **Run dell'applicazione di frontend**:
    ```sh
    npm run dev
    ```

Il backend sarà accessibile su `http://localhost:8080`.  
Il frontend sarà accessibile su `http://localhost:5173`.