/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author jusuf
 */

import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    
    public static void main(String args[]) throws IOException {
        // Ostvarivanje socket konekcije
        try (ServerSocket serveSocket = new ServerSocket(8080);) {
            Socket socket = serveSocket.accept(); 
  
            //Procesiranje zahtjeva 
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
            DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
  
            while (true) { 
                try {
                    //Čekanje i preuzimanje korisnikovog unosa
                    String receivedFirstInput = input.readUTF();
                    String receivedSecondInput = input.readUTF();
                
                    int firstNumber = Integer.parseInt(receivedFirstInput);
                    int secondNumber = Integer.parseInt(receivedSecondInput);
            
                    if(receivedFirstInput.equals("0000") && receivedSecondInput.equals("0000"))
                        break;
            
                    System.out.println("Numbers received : " + receivedFirstInput + ", " + receivedSecondInput);
                    int sumResult;
            
                    sumResult = firstNumber + secondNumber;
            
                    System.out.println("Sending the result...");
            
                    //Slanje rezultata klijentu
                    output.writeUTF(Integer.toString(sumResult));
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
