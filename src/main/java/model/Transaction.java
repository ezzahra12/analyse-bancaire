package model;

import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private LocalDateTime date;
    private double montant;
    private TypeTransaction type;
    private String lieu;
    private int idCompte;

    public Transaction() {
    }

    public Transaction(int id, LocalDateTime date, double montant,
                       TypeTransaction type, String lieu, int idCompte) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.lieu = lieu;
        this.idCompte = idCompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", montant=" + montant +
                ", type=" + type +
                ", lieu='" + lieu + '\'' +
                ", idCompte=" + idCompte +
                '}';
    }
}