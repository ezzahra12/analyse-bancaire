package model;

public class CompteEpargne extends Compte {

    private double tauxInteret;

    public CompteEpargne() {
    }

    public CompteEpargne(int id, String numero, double solde,
                         int idClient, double tauxInteret) {
        super(id, numero, solde, idClient);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    @Override
    public String getType() {
        return "Epargne";
    }

    @Override
    public String toString() {
        return "CompteEpargne{" +
                "id=" + getId() +
                ", numero='" + getNumero() + '\'' +
                ", solde=" + getSolde() +
                ", idClient=" + getIdClient() +
                ", tauxInteret=" + tauxInteret +
                '}';
    }
}