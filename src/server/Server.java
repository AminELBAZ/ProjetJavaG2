/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aelbaz
 */
public class Server {

    private int port;
    private List<ConnectedClient> clients;
    private List<String> clientsCo;

    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<ConnectedClient>();
        this.clientsCo = new ArrayList<String>();
        this.clientsCo.add("?CO");

        try {
            Thread threadConnection = new Thread(new Connection(this));
            threadConnection.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getClientsCo() {
        return clientsCo;
    }

    /**
     * Ajoute newClient à clients et envoie un message à tous les clients, pour
     * les prévenir d'une nouvelle connexion
     *
     * @param newClient
     */
    public void addClient(ConnectedClient newClient) {
        //Message d'information à tous les clients
        for (ConnectedClient client : clients) {
            client.sendMessage("Le client " + newClient.getLogin() + " vient de se connecter");
            this.clientsCo.add(newClient.getLogin());
        }
        this.clients.add(newClient);
        this.sendListeClientCo();
    }

    /**
     * Envoi la liste des clients à tous les utilisateurs connectés
     *
     * @param newClient
     */
    public void sendListeClientCo() {
        //Message d'information à tous les clients
        for (ConnectedClient client : clients) {
            client.sendMessage(clientsCo.toString());
        }
    }

    /**
     * Envoie un message à tous les clients qui n'ont pas l'id d
     *
     * @param m
     * @param d
     */
    public void broadcastMessage(String m, int d) {
        for (ConnectedClient client : clients) {
            if (client.getId() != d) {
                client.sendMessage(m);
            }
        }
    }

    /**
     * Déconnecte le discClient, le supprime de clients et previent les autres
     * client de sa déconnexion
     *
     * @param discClient
     * @throws IOException
     */
    public void disconnectedClient(ConnectedClient discClient) throws IOException {
        try {

            discClient.closeClient();
            clients.remove(discClient);
            for (ConnectedClient client : clients) {
                client.sendMessage("Le client " + discClient.getId() + " s'est déconnecté");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get And Set*
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<ConnectedClient> getClients() {
        return this.clients;
    }

    public void setClients(List<ConnectedClient> clients) {
        this.clients = clients;
    }

}
