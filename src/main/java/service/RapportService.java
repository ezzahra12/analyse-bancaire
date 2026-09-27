package service;

import model.Client;
import model.Compte;
import model.Transaction;
import model.TypeTransaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RapportService {

    private final ClientService clientService;
    private final CompteService compteService;
    private final TransactionService transactionService;

    public RapportService() {
        this.clientService = new ClientService();
        this.compteService = new CompteService();
        this.transactionService = new TransactionService();
    }

    // Top 5 clients selon le solde total de leurs comptes
    public List<Client> top5Clients() {

        List<Client> clients = clientService.findAll();
        List<Compte> comptes = compteService.findAll();

        Map<Integer, Double> soldesParClient = comptes.stream()
                .collect(Collectors.groupingBy(
                        Compte::getIdClient,
                        Collectors.summingDouble(Compte::getSolde)
                ));

        return clients.stream()
                .sorted((c1, c2) ->
                        Double.compare(
                                soldesParClient.getOrDefault(c2.getId(), 0.0),
                                soldesParClient.getOrDefault(c1.getId(), 0.0)
                        )
                )
                .limit(5)
                .collect(Collectors.toList());
    }

    // Transactions regroupées par type
    public Map<TypeTransaction, List<Transaction>> transactionsParType() {

        return transactionService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    // Volume total des transactions
    public double volumeTotal() {

        return transactionService.findAll()
                .stream()
                .mapToDouble(Transaction::getMontant)
                .sum();
    }

    // Nombre de transactions par type
    public Map<TypeTransaction, Long> nombreTransactionsParType() {

        return transactionService.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Transaction::getType,
                        Collectors.counting()
                ));
    }

    // Transactions d'un compte
    public List<Transaction> transactionsCompte(int idCompte) {

        return transactionService.findByCompte(idCompte);
    }
}