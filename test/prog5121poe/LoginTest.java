package prog5121poe;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest{
    // Test the validation and login functionality of the login class
    @Test
    public void testCheckUserName(){
        Login instance = new Login();
        assertTrue(instance.checkUserName("tl_01"));
        assertFalse(instance.checkUserName("user123"));        
    } 
    
    @Test
    public void testCheckPasswordComplixity(){
        Login instance = new Login();
        assertTrue(instance.checkPasswordComplixity("tlaleng@29W"));
        assertFalse(instance.checkPasswordComplixity("tl_01"));
    }
    
    @Test
    public void testCheckCellPhoneNumber(){
        Login instance = new Login();
        assertTrue(instance.checkCellPhoneNumber("+27697014624"));
        assertFalse(instance.checkCellPhoneNumber("0697014642"));
    }
    
    @Test
    public void testRegisterUser(){
        Login instance =new Login();
        String results =
                instance.registerUser(
                        "tl_01",
                        "tlaleng@29W", 
                        "+27697014624",
                        "Tlaleng",
                        "Mahoana");
        assertEquals("Registration successful.", results);
    }
    
    @Test
    public void testSuccessfulLogin(){
        Login instance = new Login();
        instance.registerUser(
                "tl_01",
                "tlaleng@29W",
                "+27697014624",
                "Tlaleng",
                "Mahoana");
        assertTrue(instance.loginUser("tl_01", "tlaleng@29W"));
    }
    
    @Test
    public void testFailedLogin(){
        Login instance = new Login ();
        instance.registerUser(
                "tl_01", 
                "tlaleng@29W",
                "+27697014624",
                "Tlaleng",
                "Mahoana");
        assertFalse(instance.loginUser("tl_01", "WrongPassword"));
    }
    
    @Test
    public void testReturninStatus(){
        Login instance = new Login();
        instance.registerUser(""
                + "tl_01",
                "tlaleng@29W",
                "+27697014624",
                "Tlaleng",
                "Mahoana");
        assertEquals("Welcome Tlaleng Mahoana,it is great to see you again.",
                instance.returnLoginStatus("tl_01", "tlaleng@29W"));
    }     
}