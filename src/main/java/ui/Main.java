package ui;

import model.*;
import service.ClientService;
import service.CompteService;
import service.TransactionService;
import service.RapportService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ClientService clientService = new ClientService();
    private static final CompteService compteService = new CompteService();
    private static final TransactionService transactionService = new TransactionService();
    private static final RapportService rapportService = new RapportService();

    public static void main(String[] args) {

        int choix;

        do {

            afficherMenu();

            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:
                    ajouterClient();
                    break;

                case 2:
                    afficherClients();
                    break;

                case 3:
                    rechercherClient();
                    break;

                case 4:
                    modifierClient();
                    break;

                case 5:
                    supprimerClient();
                    break;

                case 6:
                    ajouterCompte();
                    break;

                case 7:
                    afficherComptes();
                    break;

                case 8:
                    rechercherCompte();
                    break;

                case 9:
                    ajouterTransaction();
                    break;

                case 10:
                    afficherTransactions();
                    break;

                case 11:
                    afficherTransactionsCompte();
                    break;

                case 12:
                    afficherTop5Clients();
                    break;

                case 13:
                    afficherTransactionsParType();
                    break;

                case 14:
                    afficherVolumeTotal();
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }

    // =========================
    // MENU
    // =========================

    private static void afficherMenu() {

        System.out.println("\n========== ANALYSE BANCAIRE ==========");

        System.out.println("\n--- CLIENTS ---");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Afficher les clients");
        System.out.println("3. Rechercher un client");
        System.out.println("4. Modifier un client");
        System.out.println("5. Supprimer un client");

        System.out.println("\n--- COMPTES ---");
        System.out.println("6. Ajouter un compte");
        System.out.println("7. Afficher les comptes");
        System.out.println("8. Rechercher un compte");

        System.out.println("\n--- TRANSACTIONS ---");
        System.out.println("9. Ajouter une transaction");
        System.out.println("10. Afficher les transactions");
        System.out.println("11. Transactions d'un compte");

        System.out.println("\n--- RAPPORTS ---");
        System.out.println("12. Top 5 clients");
        System.out.println("13. Transactions par type");
        System.out.println("14. Volume total");

        System.out.println("\n0. Quitter");

        System.out.println("======================================");
    }

    // =========================
    // CLIENT
    // =========================

    private static void ajouterClient() {

        System.out.println("\n--- Ajouter un client ---");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        Client client = new Client(0, nom, email);

        clientService.ajouter(client);
    }

    private static void afficherClients() {

        System.out.println("\n--- Liste des clients ---");

        List<Client> clients = clientService.findAll();

        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
            return;
        }

        clients.forEach(System.out::println);
    }

    private static void rechercherClient() {

        System.out.print("ID du client : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.findById(id);

        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("Client introuvable.");
        }
    }

    private static void modifierClient() {

        System.out.print("ID du client : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.findById(id);

        if (client == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Nouveau nom : ");
        String nom = scanner.nextLine();

        System.out.print("Nouvel email : ");
        String email = scanner.nextLine();

        client.setNom(nom);
        client.setEmail(email);

        clientService.modifier(client);
    }

    private static void supprimerClient() {

        System.out.print("ID du client : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        clientService.supprimer(id);
    }

    // =========================
    // COMPTE
    // =========================

    private static void ajouterCompte() {

        System.out.println("\n--- Ajouter un compte ---");

        System.out.print("Numéro du compte : ");
        String numero = scanner.nextLine();

        System.out.print("Solde initial : ");
        double solde = scanner.nextDouble();

        System.out.print("ID du client : ");
        int idClient = scanner.nextInt();

        System.out.println("\nType de compte :");
        System.out.println("1. Compte courant");
        System.out.println("2. Compte épargne");

        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();

        if (choix == 1) {

            System.out.print("Découvert autorisé : ");
            double decouvert = scanner.nextDouble();

            CompteCourant compte = new CompteCourant(
                    0,
                    numero,
                    solde,
                    idClient,
                    decouvert
            );

            compteService.ajouter(compte);

        } else if (choix == 2) {

            System.out.print("Taux d'intérêt : ");
            double taux = scanner.nextDouble();

            CompteEpargne compte = new CompteEpargne(
                    0,
                    numero,
                    solde,
                    idClient,
                    taux
            );

            compteService.ajouter(compte);

        } else {

            System.out.println("Type de compte invalide.");
        }

        scanner.nextLine();
    }

    private static void afficherComptes() {

        System.out.println("\n--- Liste des comptes ---");

        List<Compte> comptes = compteService.findAll();

        if (comptes.isEmpty()) {
            System.out.println("Aucun compte trouvé.");
            return;
        }

        comptes.forEach(System.out::println);
    }

    private static void rechercherCompte() {

        System.out.print("ID du compte : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Compte compte = compteService.findById(id);

        if (compte != null) {
            System.out.println(compte);
        } else {
            System.out.println("Compte introuvable.");
        }
    }

    // =========================
    // TRANSACTION
    // =========================

    private static void ajouterTransaction() {

        System.out.println("\n--- Ajouter une transaction ---");

        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\nType :");
        System.out.println("1. VERSEMENT");
        System.out.println("2. RETRAIT");
        System.out.println("3. VIREMENT");

        System.out.print("Votre choix : ");
        int choixType = scanner.nextInt();
        scanner.nextLine();

        TypeTransaction type;

        switch (choixType) {

            case 1:
                type = TypeTransaction.VERSEMENT;
                break;

            case 2:
                type = TypeTransaction.RETRAIT;
                break;

            case 3:
                type = TypeTransaction.VIREMENT;
                break;

            default:
                System.out.println("Type invalide.");
                return;
        }

        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        System.out.print("ID du compte : ");
        int idCompte = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = new Transaction(
                0,
                LocalDateTime.now(),
                montant,
                type,
                lieu,
                idCompte
        );

        transactionService.ajouter(transaction);
    }

    private static void afficherTransactions() {

        System.out.println("\n--- Liste des transactions ---");

        List<Transaction> transactions =
                transactionService.findAll();

        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction trouvée.");
            return;
        }

        transactions.forEach(System.out::println);
    }

    private static void afficherTransactionsCompte() {

        System.out.print("ID du compte : ");
        int idCompte = scanner.nextInt();
        scanner.nextLine();

        List<Transaction> transactions =
                transactionService.findByCompte(idCompte);

        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction pour ce compte.");
            return;
        }

        transactions.forEach(System.out::println);
    }

    // =========================
    // RAPPORTS
    // =========================

    private static void afficherTop5Clients() {

        System.out.println("\n--- Top 5 clients ---");

        List<Client> clients =
                rapportService.top5Clients();

        if (clients.isEmpty()) {
            System.out.println("Aucun client.");
            return;
        }

        clients.forEach(System.out::println);
    }

    private static void afficherTransactionsParType() {

        System.out.println("\n--- Transactions par type ---");

        Map<TypeTransaction, List<Transaction>> result =
                rapportService.transactionsParType();

        result.forEach((type, transactions) -> {

            System.out.println("\n" + type + " :");

            transactions.forEach(System.out::println);
        });
    }

    private static void afficherVolumeTotal() {

        double volume = rapportService.volumeTotal();

        System.out.println("\n--- Volume total ---");
        System.out.println("Volume total : " + volume);
    }
}