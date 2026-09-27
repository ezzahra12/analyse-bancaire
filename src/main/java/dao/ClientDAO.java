package dao;

import model.Client;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // Ajouter un client
    public void ajouter(Client client) {

        String sql = "INSERT INTO client (nom, email) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, client.getNom());
            statement.setString(2, client.getEmail());

            statement.executeUpdate();

            System.out.println("Client ajouté avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du client.");
            e.printStackTrace();
        }
    }

    // Récupérer tous les clients
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();

        String sql = "SELECT * FROM client";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Client client = new Client();

                client.setId(resultSet.getInt("id"));
                client.setNom(resultSet.getString("nom"));
                client.setEmail(resultSet.getString("email"));

                clients.add(client);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients.");
            e.printStackTrace();
        }

        return clients;
    }

    // Trouver un client par ID
    public Client findById(int id) {

        String sql = "SELECT * FROM client WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Client client = new Client();

                    client.setId(resultSet.getInt("id"));
                    client.setNom(resultSet.getString("nom"));
                    client.setEmail(resultSet.getString("email"));

                    return client;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du client.");
            e.printStackTrace();
        }

        return null;
    }

    // Modifier un client
    public void modifier(Client client) {

        String sql = """
                UPDATE client
                SET nom = ?, email = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, client.getNom());
            statement.setString(2, client.getEmail());
            statement.setInt(3, client.getId());

            statement.executeUpdate();

            System.out.println("Client modifié avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du client.");
            e.printStackTrace();
        }
    }

    // Supprimer un client
    public void supprimer(int id) {

        String sql = "DELETE FROM client WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Client supprimé avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du client.");
            e.printStackTrace();
        }
    }
}