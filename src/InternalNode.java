
public class InternalNode extends DNANode {
    
    private DNANode aNode;
    private DNANode cNode;
    private DNANode gNode;
    private DNANode tNode;
    private DNANode dollarNode;
    
    public InternalNode(DNANode flyweightNode)
    {
        aNode = flyweightNode;
        cNode = flyweightNode;
        gNode = flyweightNode;
        tNode = flyweightNode;
        dollarNode = flyweightNode;
    }
    
    public void insert(char[] seq)
    {
        
    }

}
