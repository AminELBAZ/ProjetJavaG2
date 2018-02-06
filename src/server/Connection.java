/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aelbaz
 */
public class Connection implements Runnable {

    private Server server;
    private ServerSocket serverSocket;

    public Connection(Server server) throws IOException {
        this.server = server;
        this.serverSocket = new ServerSocket(this.server.getPort());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket sockNewClient = serverSocket.accept();

                ConnectedClient newClient = new ConnectedClient(server, sockNewClient);

                server.addClient(newClient);

                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
