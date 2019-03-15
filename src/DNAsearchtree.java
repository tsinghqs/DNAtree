

/*
- TODO:
- See if we can do a node.equals() for Lnode to compare sequence
- remove()
- search()
- main() to read from arg[0] file, and call tree functions
*/


public class DNAsearchtree {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       // TODO code application logic here
       DNAsearchtree tree = new DNAsearchtree();
       tree.print();
       tree.insert("A");
       tree.printLengths();
       tree.printStats();
       
       tree.insert("AA");
       tree.print();
       
       tree.insert("AA");
       tree.insert("A");
   }
   
   private Node root = Enode.getInst();
   
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
       root.print(0, Node.PRINT_SIMPLE);
   }
   /*
    - printLengths(): Public method to dump the tree
    - prints each node on a separate line in pre-order traversal
    - prints sequences and lengths
   */
   public void printLengths() {
       root.print(0, Node.PRINT_LENGTHS);
   }
   /*
    - printStats(): Public method to dump the tree
    - prints each node on a separate line in pre-order traversal
    - prints sequences and stats
   */
   public void printStats() {
       root.print(0, Node.PRINT_STATS);
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
       Lnode newnode = new Lnode(sequence);
       
       // Insert the newnode starting at root
       if (root instanceof Enode) {
           root = newnode;
       } else if (root instanceof Lnode) {
           Lnode lnode = (Lnode) root;
           if (lnode.containsSequenceOf(newnode)) {
               System.out.println("Error: The sequence already exists!");
               return;
           }
           root = new Inode();
           Inode inode = (Inode) root;
           inode.insert(0, lnode);
           inode.insert(0, newnode);
       } else {
           Inode inode = (Inode) root;
           inode.insert(0, newnode);
       }
   }
}