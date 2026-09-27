package service;

import dao.ClientDAO;
import model.Client;

import java.util.List;

public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public void ajouter(Client client) {
        clientDAO.ajouter(client);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    public void modifier(Client client) {
        clientDAO.modifier(client);
    }

    public void supprimer(int id) {
        clientDAO.supprimer(id);
    }
}