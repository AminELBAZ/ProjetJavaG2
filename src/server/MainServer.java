package server;

import java.io.IOException;

/**
 * start a server. Reads the server's port from the command line argument
 *
 * @author Remi Watrigant
 *
 */
public class MainServer {

    /**
     * creates a new server
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                printUsage();
            } else {
                Integer port = new Integer(args[0]);
                Server server = new Server(port);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private static void printUsage() {
        System.out.println("java server.Server <port>");
        System.out.println("\t<port>: server's port");
    }
}
