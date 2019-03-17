import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




/*
- TODO:
- See if we can do a node.equals() for LeafNode to compare sequence
- remove()
- search()
- main() to read from arg[0] file, and call tree functions
*/


public class DNAtree {

   /**
    * @param args the command line arguments
 * @throws FileNotFoundException 
    */
   public static void main(String[] args) throws FileNotFoundException {
       // TODO code application logic here
       DNAtree tree = new DNAtree();
       tree.print();
       tree.insert("A");
       tree.printLengths();
       tree.printStats();
       tree.searchSequence("A$");
       
       tree.insert("AA");
       tree.print();
       tree.searchSequence("A$");
       tree.searchSequence("AA$");
       //read file as an argument
       String fileName = args[0];
       File file = new File(fileName);
       Scanner sc = new Scanner(file);
       while (sc.hasNextLine())
       {
           String line = sc.nextLine();
           Parser parse = new Parser(tree, line);
           parse.parseString();
       
       }
       sc.close();
   }
   
   private DNANode root = EmptyNode.getInst();
   
   /*
    - isValidSequence(sequence): private/static method validates the sequence
    - cannot be empty
    - must conform to {A, C, G, T} charset.
   */
   private static final String DNA_CHARSET = "ACGT";
   private boolean isValidSequence(char[] sequence) {
       // Must be non-empty
       if (sequence.length == 0) {
           return false;
       }
       // Must conform to 
       for (char c : sequence) {
           if (DNA_CHARSET.indexOf(c) < 0) {
               return false;
           }
       }
       return true;
   }
   
   /*
    - print(): Public method to dump the tree
    - prints each node on a separate line in pre-order traversal
   */
   public void print() {
       root.print(0, DNANode.PRINT_SIMPLE);
   }
   /*
    - printLengths(): Public method to dump the tree
    - prints each node on a separate line in pre-order traversal
    - prints sequences and lengths
   */
   public void printLengths() {
       root.print(0, DNANode.PRINT_LENGTHS);
   }
   /*
    - printStats(): Public method to dump the tree
    - prints each node on a separate line in pre-order traversal
    - prints sequences and stats
   */
   public void printStats() {
       root.print(0, DNANode.PRINT_STATS);
   }
   
   /*
    insert(): Public method which inserts a given sequence
    - sequence must contain one or mode characters from {A, C, G, T}
    - sequence cannot already exist in the tree
    - returns the new ROOT node, if the insert changes the root
    - returns null if the insert does not change the root
    - always prints an appropriate message
   */
   public void insert(String sequence_str) {
       
       char[] sequence = sequence_str.toCharArray();
       
       // Sanity check on the given sequence
       if (! isValidSequence(sequence)) {
           System.out.println("Error: Invalid sequence!");
           return;
       }
       
       // Create a new leaf node
       LeafNode newnode = new LeafNode(sequence);
       
       // Insert the newnode starting at root
       if (root instanceof EmptyNode) {
           root = newnode;
       } else if (root instanceof LeafNode) {
           LeafNode LeafNode = (LeafNode) root;
           if (LeafNode.containsSequenceOf(newnode)) {
               System.out.println("Error: The sequence already exists!");
               return;
           }
           root = new InternalNode();
           InternalNode inode = (InternalNode) root;
           inode.insert(0, LeafNode);
           inode.insert(0, newnode);
       } else {
           InternalNode inode = (InternalNode) root;
           inode.insert(0, newnode);
       }
   }
   
   public void searchSequence(String sequence) {    
       
       char[] seq = sequence.toCharArray();
       boolean exactSearch = false;
       if (seq[seq.length - 1] == '$')
       {
           exactSearch = true;
       }
       // Sanity check on the given sequence
       //if (! isValidSequence(seq)) {
         //  System.out.println("# of nodes visited: 0");
           //System.out.println("no sequence found");
           //return;
       //}
       
       if (root instanceof EmptyNode)
       {
           System.out.println("# of nodes visited: 1");
           System.out.println("no sequence found");
           return; 
       }
       
       if (root instanceof LeafNode)
       {
           if (exactSearch)
           {
               String deformatted = sequence.substring(0, sequence.length() - 1);
               seq = deformatted.toCharArray();
           }
           
           LeafNode leafRoot = (LeafNode) root;
           char[] lnodeSeq = leafRoot.getSequence();
           
           if (String.valueOf(seq).equals(String.valueOf(lnodeSeq)))
           {
               System.out.println("# of nodes visited: 1");
               System.out.println("sequence: " + leafRoot.toString());
               return; 
           }
           
           System.out.println("# of nodes visited: 1");
           System.out.println("no sequence found");
           return; 
       }
       
       InternalNode internalRoot = (InternalNode) root;
       if (exactSearch)
       {
           internalRoot.search(0, seq);
           return;
       }
       
       internalRoot.searchPre(0, seq);
   }
}