package org.example.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private int id;

    private String nom;

    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;

    @ManyToMany
    @JoinTable(name = "CLIENT_COMPTE",
        joinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "COMPTE_ID", referencedColumnName = "id"))
    private Collection<Compte> compteList;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Collection<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(Collection<Compte> compteList) {
        this.compteList = compteList;
    }
}
