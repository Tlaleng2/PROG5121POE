/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121poe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tlale
 */
public class QuickChat {
    private final Scanner scanner;
    private final List<Message> sentMessage;
    private int messageCounter;
    
    public QuickChat(Scanner scanner){
        this.scanner = scanner;
        this.sentMessage =new ArrayList<>();
        this.messageCounter = 0;
        
        loadMessage();
    }
    
    public void startQuickChat(){
        System.out.println("\n=====QUICKCHAT=====");
        System.out.println("Welcome to QuickChat!");
        
        boolean running = true;
        while (running){
            System.out.println("\nChoose an option:");
            System.out.println("1. Send Message");
            System.out.println("2. View Sent Message:");
            System.out.println("3. Search for a Mssage");
            System.out.println("4. Delete a Message");
            System.out.println("5. Quit");
            System.out.println("Enter your choice:");
            
            String choice = scanner.nextLine();
            switch (choice){
                case"1": sendMessage();
                break;
                
                case"2": viewSentMessage();
                break;
                
                case"3": searchMessage();
                break;
                
                case"4": deleteMessage();
                break;
                
                case"5": displayMessageReport();
                case"6":
                    System.out.println("Thank you for using QuickChat.");
                    running = false;
                    break;
                    
                default: System.out.println("Invalid option. Please choose 1,2 ,3.");                   
            }
          }
    }
    
    private void sendMessage(){
        System.out.print("How many message would you like to send?");
        int numberOfMessage;
        try {
            numberOfMessage = Integer.parseInt(scanner.nextLine());
            if (numberOfMessage <= 0)
            {
                System.out.println("Please enter a number greater than zero.");
                return;
            }
        } 
        catch (NumberFormatException e){
            System.out.println("Please enter a valid whole number.");
            return;
        }
        for (int i = 0; i < numberOfMessage; i++){
            System.out.println("\nMessage" + (i + 1));
            System.out.print("Enter recipient cellphone number (+27...):");
            String recipient = scanner.nextLine();
            
            if (! recipient.matches("^\\+27[6-8][0-9]{8}$")){
                System.out.println("Invalid cellphone number. Message not processed.");
                
                continue;
            }
            System.out.print("Enter your message:");
            String messageText = scanner.nextLine();
            
            if (messageText.isEmpty()|| messageText.length()>250){
                System.out.println("Message must contain 1 to 250 characters.");
                continue;
                
            }
            Message message = new Message( ++messageCounter, recipient, messageText);
            
            System.out.println("\nChoose an action:");
            System.out.println("1. Send Message");
            System.out.println("2. Discard Message");
            System.out.println("Your choise:");
            
            String action = scanner.nextLine();
            if (action.equals("1")){
                sentMessage.add(message);
                System.out.println("Message sent successfully!");
                System.out.println(message.printMessage());
                saveMessage(message);
            }
            
            else if (action.equals("2")){
                System.out.println("Message discarded.");
                
            }
            else{
                System.out.println("Invalid choice. Message was not sent.");
            }
        }
    }
    
    private void viewSentMessage(){
        if (sentMessage.isEmpty()){
            System.out.println("No sent messages available.");
            return;
            
        }
        System.out.println("\n=====SENT MESSAGES=====");
        
        for (Message message : sentMessage){
            System.out.println("---------------");
            
            System.out.println(message.printMessage());
            
        }
        System.out.println("---------------");
        System.out.println("Total sent messages:" + sentMessage.size());
    }
    
    private void saveMessage(Message message){
        Path filePath = Path.of("messages.txt");
        String record = message.printMessage() + 
                System.lineSeparator() +
                "-----------------" +
                System.lineSeparator();
        try {
            Files.writeString(
                    filePath,
                    record,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.println("Message saved to messages.txt.");
                    
        }
        catch (IOException e){
            System.out.println("Error saving message:" + e.getMessage());
        }
    }
    
    private void searchMessage(){
        System.out.print("Enter the Message ID to search for:");
        String searchID = scanner.nextLine();
        
        for (Message message : sentMessage){
            if
                    (message.getMessageID().equalsIgnoreCase(searchID)){
                System.out.println("Message found:");
                System.out.println(message.printMessage());
                return;
                                      
            }          
        }
        
        System.out.println("Message not found.");
    }
    
    private void deleteMessage(){
        System.out.print("Enter the Message ID to delete:");
        String deleteID = scanner.nextLine();
        
        for (int i =0; i < sentMessage.size(); i ++){
            Message message = sentMessage.get(i);
            
            if
                    (message.getMessageID().equalsIgnoreCase(deleteID)){
                sentMessage.remove(i);
                updateMessageFile();
                
                System.out.println("Message deleted successfully.");
                return;
            }
        }
        System.out.println("Message not found.");
    }
    
    private void updateMessageFile(){
        Path filePath = Path.of("message.txt");
        
        StringBuilder content = new  StringBuilder();
        
        for (Message message : sentMessage){
            content.append(message.printMessage()).
                    append(System.lineSeparator()).
                    append("-----------------").
                    append(System.lineSeparator());
                    
        }
        
        try {
            Files.writeString(
            filePath, content.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            
            System.out.println("Saved message file updated.");
        }
        
        catch (IOException e){
            System.out.println("Error updating message file:" + e.getMessage());
        }
    }
    
    private void loadMessage(){
        Path filePath = Path.of("message.txt");
        
        if (! Files.exists(filePath)){
            return;
        }
        
        try {
            List<String> lines = Files.readAllLines(filePath);
            
            String id = null;
            String hash = null;
            String recipient = null;
            String messageText = null;
            int number = 0;
            
            for (String line: lines){
                
                if 
                        (line.startsWith("Message ID")){
                    id = line.substring("Message ID".length());
                }
                
                else if (line.startsWith("Message Number: ")){
                    number = 
                            Integer.parseInt(
                            line.substring("Message Number: " .length()));
                }
                
                else if (line.startsWith("Recipient :")){
                    recipient = line.substring("Recipient:".length());
                }
                
                else if (line.startsWith("Message:")){
                    messageText = line.substring("Message:".length());
                }
                
                else if (line.startsWith("Message Hash:")){
                    hash = line.substring("Message Hash:".length());
                    
                    if (id != null && recipient != null
                            &&
                            messageText != null){
                         Message message = new Message(
                         id,number,recipient,messageText,hash);
                         
                         sentMessage.add(message);
                         messageCounter = Math.max(messageCounter, number);
                    }
                    
                    id = null;
                    hash = null;
                    recipient = null;
                    messageText =null;
                    number = 0 ;
                }
          
            }
            System.out.println("Saved message loaded:" + sentMessage.size());
        }
        catch (IOException | NumberFormatException e){
            System.out.println("Could not load saved messages:" + e.getMessage());
        }
    }
    
    private void displayMessageReport(){
        System.out.println("\n=====MESSAGE REPORT=====");
        
        if (sentMessage.isEmpty ()){
            System.out.println("No message have been sent yet.");
            return;
            
        }
        System.out.println("Total messages:" + sentMessage.size());
        
        for (Message message : sentMessage){
            System.out.println("Message ID:" + message.getMessageID());
            System.out.println("Recipient:" + message.getRecipient());
            System.out.println("Message Hash:" + message.getMesssageHash());
            System.out.println("MessageText:" + message.getMessageText());
        }
        System.out.println("---------------------");
    }
}
                  
                
                      
            
      
    
