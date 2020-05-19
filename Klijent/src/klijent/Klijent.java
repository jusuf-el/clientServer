/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

/**
 *
 * @author jusuf
 */

import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 

public class Klijent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        InetAddress ip = InetAddress.getLocalHost(); 
        int port = 8080; 
        Scanner firstNumber = new Scanner(System.in);
        Scanner secondNumber = new Scanner(System.in);
  
        //Otvaranje socket konekcije
        try (Socket socket = new Socket(ip, port);) {
            //Komunikacija (ulazni i izlazni tokovi)
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
            DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
  
            while (true) {
                System.out.println("To exit enter 0000 for both numbers!!!");
                System.out.print("Enter the first number: ");
                String firstNum = firstNumber.nextLine();
                System.out.print("Enter the second number: ");
                String secondNum = secondNumber.nextLine();
  
                if (firstNum.equals("0000") && secondNum.equals("0000")) 
                    break; 
  
                //Slanje korisničkog unosa na server
                output.writeUTF(firstNum);
                output.writeUTF(secondNum);
  
                //Čekanje da zahtjev bude obrađen i preuzimanje odgovora od servera
                String sumResult = input.readUTF(); 
                System.out.println(firstNum + " + " + secondNum + " = " + sumResult); 
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}