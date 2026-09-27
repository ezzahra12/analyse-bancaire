package dao;

import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO {

    // Ajouter un compte
    public void ajouter(Compte compte) {

        String sql = """
                INSERT INTO compte
                (numero, solde, id_client, type_compte,
                 decouvert_autorise, taux_interet)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, compte.getNumero());
            statement.setDouble(2, compte.getSolde());
            statement.setInt(3, compte.getIdClient());
            statement.setString(4, compte.getType());

            if (compte instanceof CompteCourant compteCourant) {
                statement.setDouble(5, compteCourant.getDecouvertAutorise());
                statement.setDouble(6, 0);
            } else if (compte instanceof CompteEpargne compteEpargne) {
                statement.setDouble(5, 0);
                statement.setDouble(6, compteEpargne.getTauxInteret());
            }

            statement.executeUpdate();

            System.out.println("Compte ajouté avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du compte.");
            e.printStackTrace();
        }
    }

    // Récupérer tous les comptes
    public List<Compte> findAll() {

        List<Compte> comptes = new ArrayList<>();

        String sql = "SELECT * FROM compte";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                comptes.add(creerCompte(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des comptes.");
            e.printStackTrace();
        }

        return comptes;
    }

    // Trouver un compte par son ID
    public Compte findById(int id) {

        String sql = "SELECT * FROM compte WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return creerCompte(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du compte.");
            e.printStackTrace();
        }

        return null;
    }

    // Trouver les comptes d'un client
    public List<Compte> findByClient(int idClient) {

        List<Compte> comptes = new ArrayList<>();

        String sql = "SELECT * FROM compte WHERE id_client = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idClient);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    comptes.add(creerCompte(resultSet));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des comptes du client.");
            e.printStackTrace();
        }

        return comptes;
    }

    // Modifier un compte
    public void modifier(Compte compte) {

        String sql = """
                UPDATE compte
                SET numero = ?,
                    solde = ?,
                    id_client = ?,
                    type_compte = ?,
                    decouvert_autorise = ?,
                    taux_interet = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, compte.getNumero());
            statement.setDouble(2, compte.getSolde());
            statement.setInt(3, compte.getIdClient());
            statement.setString(4, compte.getType());

            if (compte instanceof CompteCourant compteCourant) {
                statement.setDouble(5, compteCourant.getDecouvertAutorise());
                statement.setDouble(6, 0);

            } else if (compte instanceof CompteEpargne compteEpargne) {
                statement.setDouble(5, 0);
                statement.setDouble(6, compteEpargne.getTauxInteret());
            }

            statement.setInt(7, compte.getId());

            statement.executeUpdate();

            System.out.println("Compte modifié avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du compte.");
            e.printStackTrace();
        }
    }

    // Supprimer un compte
    public void supprimer(int id) {

        String sql = "DELETE FROM compte WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Compte supprimé avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du compte.");
            e.printStackTrace();
        }
    }

    // Transformer une ligne SQL en objet Compte
    private Compte creerCompte(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String numero = resultSet.getString("numero");
        double solde = resultSet.getDouble("solde");
        int idClient = resultSet.getInt("id_client");
        String type = resultSet.getString("type_compte");

        if ("Courant".equalsIgnoreCase(type)) {

            double decouvert =
                    resultSet.getDouble("decouvert_autorise");

            return new CompteCourant(
                    id,
                    numero,
                    solde,
                    idClient,
                    decouvert
            );

        } else {

            double taux =
                    resultSet.getDouble("taux_interet");

            return new CompteEpargne(
                    id,
                    numero,
                    solde,
                    idClient,
                    taux
            );
        }
    }
}