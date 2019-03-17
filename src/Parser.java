
public class Parser {

    private String input;
    private DNAtree parseBoy;
    /**
    * Constructor for parser
    * @param name name of BST
    * @param terms string commands
    */
   public Parser(DNAtree tree, String terms)
   {
       parseBoy = tree;
       input = terms;
   }
   
   /**
    * fields for parser
    */
   
   
   
   public void parseString()
   {
       String[] commands = input.trim().split("\\s+");
       if (commands.length > 0)
       {
           if (commands[0].equals("insert"))
           {
               
               parseBoy.insert(commands[1]);
           }
           else if (commands[0].equals("remove"))
           {
               //parseBoy.remove(commands[1]);
               return;
           }
           else if (commands[0].equals("search"))
           {
               parseBoy.searchSequence(commands[1]);
           }
           else if (commands.length >= 2 && commands[1].equals("length"))
           {
               parseBoy.printLengths();
           }
           else if (commands.length >= 2 && commands[1].equals("stats"))
           {
               parseBoy.printStats();
           }
           else if (commands[0].equals("print"))
           {
               parseBoy.print();
           }
       }
   }
}
