package server;

import java.io.IOException;

/**
 * start a server. Reads the server's port from the command line argument
 *
 * @author Remi Watrigant
 *
 */
public class MainServer {
    
    static Server server = null;

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
                server = new Server(port);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    /**
     * Retourne L'instance du serveur, si il existe
     * 
     * @return 
     */
    public static Server getInstance(){
        if(server == null){
            return null;
        }
        return server;
    }
    
    private static void printUsage() {
        System.out.println("java server.Server <port>");
        System.out.println("\t<port>: server's port");
    }
}
