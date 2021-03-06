import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * DNATree is the driver class that has the flyweight node
 * and all of the methods that the parser file interprets.
 * 
 * @author vpratha
 * @version 3.17.2019
 *
 */
public class DNAtree {
    /**
     * DNA_CHARSET is used to determine if sequences are valid.
     */
    private static final String DNA_CHARSET = "ACGT";
    private DNANode root;


    /**
     * DNAtree's constructor;
     * initializes root to flyweight node
     */
    public DNAtree() {
        root = EmptyNode.getInst();
    }


    /**
     * Processes input file and sends each command to the Parser.
     * 
     * @param args
     *            the input file of commands
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // creates DNAtree used by Parser
        DNAtree tree = new DNAtree();
        
        
        // reads command line arg as input file
        String fileName = args[0];
        File input = new File(fileName);

        // parses each command in input file
        Scanner sc = new Scanner(input);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Parser parse = new Parser(tree, line);
            parse.parseString();
        }
        sc.close();
    }


    /**
     * Determines if sequence is valid based on DNA_CHARSET.
     * 
     * @param sequence
     *            the sequence in question
     * @return true if the sequence is valid
     */
    private boolean isValidSequence(char[] sequence) {
        // Must be non-empty
        if (sequence.length == 0) {
            return false;
        }
        // Must conform to DNA_CHARSET
        for (char c : sequence) {
            if (DNA_CHARSET.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Prints dump of tree in preorder traversal
     */
    public void print() {
        System.out.println("tree dump:");
        root.print(0, DNANode.PRINT_SIMPLE);
    }


    /**
     * Prints dump of tree with lengths of sequences
     */
    public void printLengths() {
        System.out.println("tree dump:");
        root.print(0, DNANode.PRINT_LENGTHS);
    }


    /**
     * Prints dump of tree with stats of sequences
     */
    public void printStats() {
        System.out.println("tree dump:");
        root.print(0, DNANode.PRINT_STATS);
    }


    /**
     * Inserts sequence in tree.
     * 
     * @param sequence
     *            the sequence to be inserted
     */
    public void insert(String sequence) {
        char[] seq = sequence.toCharArray();

        if (!isValidSequence(seq)) {
            //System.out.println("sequence is invalid");
            return;
        }

        // Create a new leaf node
        LeafNode newnode = new LeafNode(seq);

        // Insert newnode starting at root
        if (root instanceof EmptyNode) {
            root = newnode;
            System.out.println("sequence " + sequence + " inserted at level "
                + 0);
        }

        else if (root instanceof LeafNode) {
            LeafNode leafRoot = (LeafNode)root;
            if (leafRoot.containsSequenceOf(newnode)) {
                System.out.println("sequence " + sequence + " already exists");
                return;
            }
            root = new InternalNode();
            InternalNode internalRoot = (InternalNode)root;
            internalRoot.insert(0, leafRoot, false);
            internalRoot.insert(0, newnode, true);
        }

        else {
            InternalNode internalRoot = (InternalNode)root;
            internalRoot.insert(0, newnode, true);
        }
    }


    /*
     * remove(sequenceStr): Public method which removes a sequence from the
     * tree
     * - sequence must contain one or more characters from {A, C, G, T}
     * - always (success or failure) prints an appropriate message
     */
    /**
     * Removes sequence in tree.
     * 
     * @param sequence the sequence in the tree
     */
    public void remove(String sequence) {
        char[] seq = sequence.toCharArray();

        if (!isValidSequence(seq)) {
            System.out.println("sequence " + sequence + " does not exist");
            return;
        }

        // Create a leaf node, to package the sequence to be deleted
        LeafNode nodeRemove = new LeafNode(seq);

        // Delete the sequence, starting at root
        if (root instanceof EmptyNode) {
            System.out.println("sequence " + sequence + " does not exist");
        }

        else if (root instanceof LeafNode) {
            LeafNode lnode = (LeafNode)root;
            if (!lnode.toString().equals(sequence)) {
                System.out.println("sequence " + sequence + " does not exist");
            }

            else {
                root = EmptyNode.getInst();
                System.out.println("sequence " + sequence + " removed");
            }
        }

        else {
            InternalNode inode = (InternalNode)root;
            DNANode loneNode = inode.remove(0, nodeRemove);
            if (loneNode != null) {
                root = loneNode;
            }
        }
    }


    /**
     * Searches tree for given sequence.
     * 
     * @param sequenceStr given sequence
     */
    public void search(String sequenceStr) {
        boolean exact = sequenceStr.endsWith("$");
        if (exact)
        {
            sequenceStr = sequenceStr.substring(0, sequenceStr.length() - 1);
        }
        SearchResults results = new SearchResults();
        char[] sequence = sequenceStr.toCharArray();
        if (isValidSequence(sequence))
        {
            root.search(0, sequence, exact, results);
        }
        System.out.println("# of nodes visited: " + results.getNodesVisited());
        if (results.getMatches().size() == 0) {
            System.out.println("no sequence found");
        }
        else {
            for (char[] seq : results.getMatches()) {
                System.out.println("sequence: " + String.valueOf(seq));
            }
        }
    }
}
