/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aelbaz
 */
public class ClientReceive implements Runnable {

    private Client client;
    private BufferedReader in;

    public ClientReceive(Client client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    @Override
    public void run() {
        boolean isActive = true;
        try {
            while (isActive) {
                String message = in.readLine();
                if (message != null) {
                    System.out.println("\nMessage reçu : " + message);
                } else {
                    isActive = false;
                }
            }
            client.disconnectedServer();
        } catch (IOException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
