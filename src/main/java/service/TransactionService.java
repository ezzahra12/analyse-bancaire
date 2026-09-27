package service;

import dao.TransactionDAO;
import model.Transaction;

import java.util.List;

public class TransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public void ajouter(Transaction transaction) {
        transactionDAO.ajouter(transaction);
    }

    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }

    public Transaction findById(int id) {
        return transactionDAO.findById(id);
    }

    public List<Transaction> findByCompte(int idCompte) {
        return transactionDAO.findByCompte(idCompte);
    }

    public void modifier(Transaction transaction) {
        transactionDAO.modifier(transaction);
    }

    public void supprimer(int id) {
        transactionDAO.supprimer(id);
    }
}