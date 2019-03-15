/*
 - LeafNode: Non-Empty Leaf DNANode
 - Contains exactly one sequence
*/
public class LeafNode extends DNANode {
    
    private final char[] sequence;
    
    // For debugging; get rid of it later
    public String toString() {return String.valueOf(sequence);}
    
    // Creates a Leaf DNANode with the given sequence
    public LeafNode(char[] sequence) {
        this.sequence = sequence;
    }
    
    public char[] getSequence()
    {
        return sequence;
    }
    
    public char getCharAt(int level) {
        if (level >= 0 && level < sequence.length) {
            return sequence[level];
        }
        return 0;
    }
    
    public boolean containsSequenceOf(LeafNode DNANode) {
        return String.valueOf(this.sequence).equals(String.valueOf(DNANode.sequence));
    }
    
    public void printInfo(int level, int type) {
        switch (type) {
            case DNANode.PRINT_SIMPLE:
                System.out.println(String.valueOf(sequence));
                break;
            case DNANode.PRINT_LENGTHS:
                System.out.println(String.valueOf(sequence) + " " + sequence.length);
                break;
            case DNANode.PRINT_STATS:
            default:
                int total = sequence.length;
                double a = 0, c = 0, t = 0, g = 0;
                String stats = "A:25.00 C:25.00 G:25.00 T:25.00";
                // TODO: count A, C, T and G percentages, and create stats string
                System.out.println(String.valueOf(sequence) + " " + stats);
                break;
        }
    }
}