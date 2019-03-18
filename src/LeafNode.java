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
     * @param sequence
     */
    public LeafNode(char[] seq) {
        sequence = seq;
    }


    /**
     * Returns String version of sequence.
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
     * @param lNode
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
                double a = 0, c = 0, g = 0, t = 0;
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
}
