/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121poe;
import java.util.UUID;

/**
 *
 * @author tlale
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    public Message (int messageNumber, String recipient, String messageText){
        this.messageID =
                UUID.randomUUID().toString()
                        .replace("_",
                                "").substring(0,10);
        this.messageNumber = messageNumber;
        this.messageText = messageText;
        this.recipient = recipient;
        this.messageHash = createMessageHash();
    }
    
    public Message(String messageID, int messageNumber, String recipient,String messageText,
            String messageHash){
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHash;
    }
    // Checks that the message ID contains no more than 10 characters
    public boolean checkMessageID(){
        return messageID != null && messageID.length()<=10;
    }
    
    // validates a South African cellphone number
    public boolean checkRecipientCell(){
        return recipient != null &&
                recipient.matches("^\\+27[6-8][0-9]{8}$");
    }
    
    // A message must not exceed 250 characters
    public boolean checkMessageLength(){
        return messageText != null &&
                messageText.length() <250;
    }
    
    // Create the message hash 
    public String createMessageHash(){
        String[] words =
                messageText.trim().split("\\s+");
        
        String firstWord = words.length
                >0 ? words[0]:"";
        String lastword = words.length
                >0 ? words[words.length-1]:"";
        
        String firstTwoID = messageID.substring(0,2);
        return (firstTwoID +":" + messageNumber + ":"
                + firstWord + lastword).toUpperCase();
    }
    public String getMessageID(){
        return messageID;
    }
    
    public int getMessageNumber(){
        return messageNumber;
    }
    
    public String getRecipient(){
        return recipient;
    }
    
    public String getMessageText(){
        return messageText;
    }
     
    public String getMesssageHash(){
        return messageHash;
    }
    
    public String printMessage(){
        return "Message ID:" + messageID
                + "\nMessage Number:" + messageNumber
                + "\nRecipient:" + recipient
                + "\nMessage:" + messageText
                + "\nMessage Hash:" +messageHash;
    }
}
        
        
        
        
           

