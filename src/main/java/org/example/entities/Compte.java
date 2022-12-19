package org.example.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Compte {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false, length = 17)
    private String iban;

    @Column(precision=10, scale=2)
    private double solde;

    @ManyToOne
    @JoinColumn(name = "AGENCE_ID")
    private Agence agence;

    @ManyToMany(mappedBy = "compteList")
    private Collection<Client> clientList;

    public Compte(String libelle, String iban) {
        this.libelle = libelle;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Collection<Client> getClientList() {
        return clientList;
    }

    public void setClientList(Collection<Client> clientList) {
        this.clientList = clientList;
    }
}
