\# Analyse Bancaire



Application de gestion et d'analyse bancaire développée en Java.



\## Technologies



\- Java 17

\- MySQL

\- JDBC

\- Maven

\- Stream API

\- IntelliJ IDEA



\## Fonctionnalités



\### Gestion des clients

\- Ajouter un client

\- Afficher les clients

\- Rechercher un client

\- Modifier un client

\- Supprimer un client



\### Gestion des comptes

\- Ajouter un compte courant

\- Ajouter un compte épargne

\- Afficher les comptes

\- Rechercher un compte



\### Gestion des transactions

\- Ajouter une transaction

\- Afficher les transactions

\- Consulter les transactions d'un compte



\### Analyse et rapports

\- Top 5 des clients par solde

\- Transactions par type

\- Calcul du volume total des transactions

\- Utilisation de Stream API et Collectors



\## Architecture



Le projet suit une architecture en couches :



```text

UI

&#x20;↓

Service

&#x20;↓

DAO

&#x20;↓

MySQL

