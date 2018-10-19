package app.utilities;

/**
 * This class prints the given message on console.
 * @author GROUP-35
 */
public class MessageUtil {

   private String message;

   public MessageUtil(String message){
      this.message = message; 
   }

   public String printMessage(){
      System.out.println(message);
      return message;
   }   

   public String salutationMessage(){
      message = "Hi!" + message;
      System.out.println(message);
      return message;
   }   
}  	