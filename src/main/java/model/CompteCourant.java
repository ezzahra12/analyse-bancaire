package model;

public class CompteCourant extends Compte {

    private double decouvertAutorise;

    public CompteCourant() {
    }

    public CompteCourant(int id, String numero, double solde,
                         int idClient, double decouvertAutorise) {
        super(id, numero, solde, idClient);
        this.decouvertAutorise = decouvertAutorise;
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public String getType() {
        return "Courant";
    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "id=" + getId() +
                ", numero='" + getNumero() + '\'' +
                ", solde=" + getSolde() +
                ", idClient=" + getIdClient() +
                ", decouvertAutorise=" + decouvertAutorise +
                '}';
    }
}