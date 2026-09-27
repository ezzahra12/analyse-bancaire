package service;

import dao.CompteDAO;
import model.Compte;

import java.util.List;

public class CompteService {

    private final CompteDAO compteDAO;

    public CompteService() {
        this.compteDAO = new CompteDAO();
    }

    public void ajouter(Compte compte) {
        compteDAO.ajouter(compte);
    }

    public List<Compte> findAll() {
        return compteDAO.findAll();
    }

    public Compte findById(int id) {
        return compteDAO.findById(id);
    }

    public List<Compte> findByClient(int idClient) {
        return compteDAO.findByClient(idClient);
    }

    public void modifier(Compte compte) {
        compteDAO.modifier(compte);
    }

    public void supprimer(int id) {
        compteDAO.supprimer(id);
    }
}