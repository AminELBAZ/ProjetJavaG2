/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author aelbaz
 */
public class Client implements Runnable {

    private String address;
    private String login;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ObservableList<String> chatLog;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Client(String address, int port, String login) throws IOException {
        this.address = address;
        this.port = port;
        this.login = login;
        this.socket = new Socket(this.address, this.port);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
        chatLog = FXCollections.observableArrayList();

        ClientSend clientSend = new ClientSend(this.out);
        Thread threadClientSend = new Thread(clientSend);
        threadClientSend.start();

        ClientReceive clientReceive = new ClientReceive(this, this.in);
        Thread threadClientReceive = new Thread(clientReceive);
        threadClientReceive.start();
    }

    /**
     * Déconnecte le client du server et quitte l'app
     *
     * @throws IOException
     */
    public void disconnectedServer() throws IOException {
        try {

            this.in.close();
            this.out.close();
            this.socket.close();
            System.exit(0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        /* Infinite loop to update the chat log from the server */
        while (true) {
            try {
                final String inputFromServer = this.in.readLine();
                Platform.runLater(new Runnable() {
                    public void run() {
                        chatLog.add(inputFromServer);
                    }
                });

            } catch (SocketException e) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        chatLog.add("Error in server");
                    }

                });
                break;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Get And Set *
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
