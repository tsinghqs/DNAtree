
public class Parser {

    private String input;
    
    /**
    * Constructor for parser
    * @param name name of BST
    * @param terms string commands
    */
   public Parser(String terms)
   {
       input = terms;
   }
   
   public void parseString()
   {
       String[] commands = input.trim().split("\\s+");
       
       if (commands.length > 0)
       {
           if (commands[0].equals("insert"))
           {
               System.out.println("INSERT BITCH");
           
           }
       }
   }
}
