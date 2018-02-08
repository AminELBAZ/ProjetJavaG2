/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Client;
import client.MainClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aelbaz
 */
public class ConnectedClient implements Runnable {

    private static int idCounter;
    private int id;
    private String login;

    private Server server;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ConnectedClient(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.id = idCounter;
        this.login = String.valueOf(this.id);
        idCounter++;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());

        System.out.println("Nouvelle connexion, id = " + this.id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void run() {
        boolean isActive = true;
        while (isActive) {
            try {

                String message = in.readLine();

                if (message != null && !message.equals("")) {
                    server.broadcastMessage(message, id);
                } else {
                    server.disconnectedClient(this);
                    isActive = false;
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendMessage(String m) {
        this.out.println(m);
        this.out.flush();
    }

    public void closeClient() throws IOException {

        try {

            this.in.close();
            this.out.close();
            this.socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get And Set *
     */
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        ConnectedClient.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

}
