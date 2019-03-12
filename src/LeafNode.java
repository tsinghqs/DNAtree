
public class LeafNode extends DNANode {
    
    private char[] sequence;
    
    public LeafNode(char[] seq)
    {
        sequence = seq;
    }
    
    public void insert(char[] seq)
    {
        sequence = seq;
    }

}
