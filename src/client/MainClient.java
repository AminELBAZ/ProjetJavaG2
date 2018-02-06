package client;

import java.io.IOException;
import java.net.UnknownHostException;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * starts a client. Reads the address and port from the command line argument
 *
 * @author Remi Watrigant
 *
 */
public class MainClient {

    static Client c = null;

    /**
     * construct a new client
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                printUsage();
            } else {
                String address = args[0];
                Integer port = new Integer(args[1]);
                String login = args[2];
                System.out.println(args[0]);
                System.out.println(args[1]);
                System.out.println(args[2]);
                c = new Client(address, port, login);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println("java client.Client <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
        System.out.println("\t<login>: client login");
    }

    public static Client getInstance() {
        if (c == null) {
            return null;
        }
        return c;
    }

}
