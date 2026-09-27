package dao;

import model.Transaction;
import model.TypeTransaction;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Ajouter une transaction
    public void ajouter(Transaction transaction) {

        String sql = """
                INSERT INTO transaction_bancaire
                (date, montant, type, lieu, id_compte)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(
                    1,
                    Timestamp.valueOf(transaction.getDate())
            );

            statement.setDouble(2, transaction.getMontant());

            statement.setString(
                    3,
                    transaction.getType().name()
            );

            statement.setString(4, transaction.getLieu());
            statement.setInt(5, transaction.getIdCompte());

            statement.executeUpdate();

            System.out.println("Transaction ajoutée avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la transaction.");
            e.printStackTrace();
        }
    }

    // Récupérer toutes les transactions
    public List<Transaction> findAll() {

        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transaction_bancaire";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                transactions.add(creerTransaction(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des transactions.");
            e.printStackTrace();
        }

        return transactions;
    }

    // Trouver une transaction par son ID
    public Transaction findById(int id) {

        String sql = """
                SELECT * FROM transaction_bancaire
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return creerTransaction(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la transaction.");
            e.printStackTrace();
        }

        return null;
    }

    // Trouver les transactions d'un compte
    public List<Transaction> findByCompte(int idCompte) {

        List<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT * FROM transaction_bancaire
                WHERE id_compte = ?
                ORDER BY date DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idCompte);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    transactions.add(creerTransaction(resultSet));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des transactions du compte.");
            e.printStackTrace();
        }

        return transactions;
    }

    // Modifier une transaction
    public void modifier(Transaction transaction) {

        String sql = """
                UPDATE transaction_bancaire
                SET date = ?,
                    montant = ?,
                    type = ?,
                    lieu = ?,
                    id_compte = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(
                    1,
                    Timestamp.valueOf(transaction.getDate())
            );

            statement.setDouble(2, transaction.getMontant());

            statement.setString(
                    3,
                    transaction.getType().name()
            );

            statement.setString(4, transaction.getLieu());
            statement.setInt(5, transaction.getIdCompte());
            statement.setInt(6, transaction.getId());

            statement.executeUpdate();

            System.out.println("Transaction modifiée avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la transaction.");
            e.printStackTrace();
        }
    }

    // Supprimer une transaction
    public void supprimer(int id) {

        String sql = """
                DELETE FROM transaction_bancaire
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Transaction supprimée avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la transaction.");
            e.printStackTrace();
        }
    }

    // Transformer une ligne SQL en objet Transaction
    private Transaction creerTransaction(ResultSet resultSet)
            throws SQLException {

        Transaction transaction = new Transaction();

        transaction.setId(resultSet.getInt("id"));

        Timestamp timestamp = resultSet.getTimestamp("date");

        if (timestamp != null) {
            transaction.setDate(timestamp.toLocalDateTime());
        }

        transaction.setMontant(resultSet.getDouble("montant"));

        transaction.setType(
                TypeTransaction.valueOf(
                        resultSet.getString("type")
                )
        );

        transaction.setLieu(resultSet.getString("lieu"));
        transaction.setIdCompte(resultSet.getInt("id_compte"));

        return transaction;
    }
}