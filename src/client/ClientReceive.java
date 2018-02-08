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
                // SI ON A LENTETE POUR LA LISTE DES CLIENTS
                if (message.startsWith("?CO")) {
                    // ON RECUPERE LA LISTE DES CLIENTS SANS LENTETE
                    message = message.substring(3);
                    // ON SEPARE LES CLIENTS
                    String[] split = message.split(";");
                    for (int i = 0; i < split.length; i++) {
                        // AJOUT DES CLIENTS A LA LISTE
                        client.listeClientsCo.add(split[i]);
                    }
                    //SINON ON ENVOIE LE MESSAGE
                } else {
                    if (message != null && !message.equals("")) {
                        System.out.print("\nMessage reçu : " + message);
                        client.chatLog.add(message);
                    } else {
                        isActive = false;
                    }
                }
            }
            client.disconnectedServer();
        } catch (IOException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
