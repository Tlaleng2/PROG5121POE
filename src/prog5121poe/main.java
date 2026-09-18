/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog5121poe;

/**
 *
 * @author tlale
 */
import java.util.Scanner;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       Login login = new Login();
       
       //Registration
       System.out.print("Enter your first name:");
       String firstName = scanner.nextLine();
       
       System.out.print("Enter your last name:");
       String lastName = scanner.nextLine();
       
       System.out.print("Enter your username :");
       String username = scanner.nextLine();
       
       System.out.print("Enter your password :");
       String password = scanner.nextLine();
       
       System.out.print("Enter your South African cellphone number:");
       String cellPhoneNumber = scanner.nextLine();
       
       String registrationMessage = login.registerUser(username, password, cellPhoneNumber, firstName, lastName);
       
       System.out.println(registrationMessage);
       
       //Login
       if 
               (registrationMessage.equals("Registration successful.")){
           
           System.out.println("\n==== LOGIN ====");
           System.out.print("Enter your username:");
           String loginUsername = scanner.nextLine();
           
           System.out.print("Enter your password:");
           String loginpassword = scanner.nextLine();
           
           String loginMessage = login.returnLoginStatus(username, password);
           System.out.println(loginMessage);
       }
       scanner.close();
                                   
    }   
}
