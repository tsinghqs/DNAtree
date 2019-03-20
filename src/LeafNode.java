/**
 * LeafNode contains exactly one sequence.
 * 
 * @author vpratha
 * @version 3.17.2019
 */
public class LeafNode extends DNANode {
    /**
     * Sequence of the node.
     */
    private final char[] sequence;


    /**
     * LeafNode's constructor;
     * updates sequence field.
     * 
     * @param seq the sequence initiated
     */
    public LeafNode(char[] seq) {
        sequence = seq;
    }


    /**
     * Returns String version of sequence.
     * @return String the version of sequence
     */
    public String toString() {
        return String.valueOf(sequence);
    }


    /**
     * Returns the sequence.
     * 
     * @return sequence
     */
    public char[] getSequence() {
        return sequence;
    }


    /**
     * Gets a certain character from the
     * sequence based on level.
     * 
     * @param level
     *            position of the character in sequence
     * @return the character in sequence
     */
    public char getCharAt(int level) {
        if (level >= 0 && level < sequence.length) {
            return sequence[level];
        }
        return 0;
    }


    /**
     * Determines if sequence is the same as
     * lNode's sequence.
     * 
     * @param lNode node containing sequence
     * @return true if sequences match
     */
    public boolean containsSequenceOf(LeafNode lNode) {
        return String.valueOf(this.sequence).equals(String.valueOf(
            lNode.sequence));
    }


    /**
     * Prints the sequence and other information
     * based on type.
     * 
     * @param level
     *            (unused)
     * @param type
     *            the type of print
     */
    public void printInfo(int level, int type) {
        switch (type) {
            case DNANode.PRINT_SIMPLE:
                System.out.println(String.valueOf(sequence));
                break;
            case DNANode.PRINT_LENGTHS:
                System.out.println(String.valueOf(sequence) + " "
                    + sequence.length);
                break;
            case DNANode.PRINT_STATS:
            default:
                double n = sequence.length / 100.;
                double a = 0;
                double c = 0;
                double g = 0;
                double t = 0;
                for (char ch : sequence) {
                    switch (ch) {
                        case 'A':
                            a++;
                            break;
                        case 'C':
                            c++;
                            break;
                        case 'G':
                            g++;
                            break;
                        case 'T':
                            t++;
                            break;
                    }
                }
                String stats = String.format("A:%.2f C:%.2f G:%.2f T:%.2f", a
                    / n, c / n, g / n, t / n);
                System.out.println(String.valueOf(sequence) + " " + stats);
                break;
        }
    }


    /**
     * Implementation of searchAll;
     * updates results accordingly.
     * 
     * @param results
     *            the SearchResults object to be updated
     */
    public void searchAll(SearchResults results) {
        results.incrementNodesVisited();
        results.addMatch(sequence);
    }
    
    /**
     * Method implemented by LeafNode and EmptyNode and
     * overridden by InternalNode for recursively
     * finding a given sequence(s) and storing it in
     * results, based on the current level of the tree.
     * @param level the current level in the tree
     * @param sequence the search term
     * @param exact true if search term is exact
     * @param results the SearchResults object being updated
     */
    public void search(int level, char[] searchSequence, 
        boolean exact, SearchResults results) 
    {
        results.incrementNodesVisited();
        if (exact)
        {
            if (toString().equals(String.valueOf(searchSequence)))
            {
                results.addMatch(searchSequence);
            }
        }
        else
        {
            if (toString().startsWith(String.valueOf(searchSequence)))
            {
                results.addMatch(this.sequence);
            } 
        }
    }
}
