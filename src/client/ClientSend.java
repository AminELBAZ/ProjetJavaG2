/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author aelbaz
 */
public class ClientSend implements Runnable{
    
    private PrintWriter out;

    public ClientSend(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Votre message >> ");
            String m = sc.nextLine();
            out.println(m);
            out.flush();
        }
        
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
    
    
    
    
    
}
