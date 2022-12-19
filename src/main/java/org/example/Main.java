package org.example;

import org.example.entities.Agence;
import org.example.entities.Client;
import org.example.entities.Compte;
import org.example.services.ClientService;
import org.example.services.CompteService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Agence agence1 = new Agence();
        agence1.setAdresse("Adresse 1");

        Agence agence2 = new Agence();
        agence2.setAdresse("Adresse 2");

        transaction.begin();
        em.persist(agence1);
        em.persist(agence2);
        transaction.commit();

        Client client1 = new Client();
        client1.setNom("un");
        client1.setPrenom("un");
        client1.setDateDeNaissance(new Date("1980/10/12"));

        Client client2 = new Client();
        client2.setNom("deux");
        client2.setPrenom("deux");
        client2.setDateDeNaissance(new Date("1980/01/08"));

        transaction.begin();
        em.persist(client1);
        em.persist(client2);
        transaction.commit();

        Compte compte1 = new Compte("libelle1", "iban1");
        compte1.setSolde(100.00);
        compte1.setAgence(agence1);


        Compte compte2 = new Compte("libelle2", "iban2");
        compte2.setSolde(200.00);
        compte2.setAgence(agence1);

        transaction.begin();
        em.persist(compte1);
        em.persist(compte2);
        transaction.commit();


        // Attribuer des clients aux comptes et des comptes aux clients
        // Faire une collection de clients
        transaction.begin();
        Query query1 = em.createQuery("SELECT c FROM Client c");
        Collection<Client> clientList = query1.getResultList();
        transaction.commit();

        transaction.begin();
        Query query2 = em.createQuery("SELECT c FROM Compte c");
        Collection<Compte> compteList = query2.getResultList();
        transaction.commit();

        transaction.begin();
        for (Client c : clientList) {
            c.setCompteList(compteList);
            em.persist(c);
        }
        for (Compte c : compteList) {
            c.setClientList(clientList);
            em.persist(c);
        }
        transaction.commit();




//        ClientService clientService = new ClientService();
//        Collection<Client> clientList = clientService.findAll();
//
//        // Faire une collection de comptes
//        CompteService compteService = new CompteService();
//        Collection<Compte> compteList = compteService.findAll();
//
//        transaction.begin();
//        for (Client c : clientList) {
//            c.setCompteList(compteList);
//            em.persist(c);
//        }
//        for (Compte c : compteList) {
//            c.setClientList(clientList);
//            em.persist(c);
//        }
//        transaction.commit();
//
//
//        clientService.close();
//        compteService.close();

        em.close();
        emf.close();
    }
}