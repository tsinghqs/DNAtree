
/*
 - InternaLeafNode: Models intermediate nodes.
 - Contains exactly 5 children
 - Must contain absolutely NO OTHER data.
*/
public class InternalNode extends DNANode {
    private static final int NCHILDREN = 5;
    private final DNANode[] children = new DNANode[NCHILDREN];
    
    // Creates an instance with all Empty child nodes
    public InternalNode() {
        for (int i = 0; i < children.length; i++) {
            children[i] = EmptyNode.getInst();
        }
    }
    
    public void printInfo(int level, int type) {
        
        // Print "I"
        System.out.println("I");
        
        // And, print all children at level+1 indent
        for (DNANode child : children) {
            child.print(level + 1, type);
        }
    }
    
    public void insert(int level, LeafNode newnode) {
        
        System.out.println("InternaNodeInsert: " + level + " " + newnode);
        char ch = newnode.getCharAt(level);
        int position = 0;
        switch (ch) {
            case 'A': position = 0; break;
            case 'C': position = 1; break;
            case 'G': position = 2; break;
            case 'T': position = 3; break;
            case 0: position = 4; break;
        }
        DNANode child = children[position];
        if (child instanceof EmptyNode) {
            System.out.println("    Replacing EmptyNode @ " + position);
            children[position] = newnode;
            return;
        }
        if (child instanceof LeafNode) {
            if (position == 4) {
                System.out.println("Sequence already exists! (in $-bucket)");
                return;
            }
            
            System.out.println("    Replacing LeafNode @ " + position);
            InternalNode InternaLeafNode = new InternalNode();
            children[position] = InternaLeafNode;
            LeafNode LeafNode = (LeafNode) child;
            InternaLeafNode.insert(level + 1, LeafNode);
            InternaLeafNode.insert(level + 1, newnode);
            return;
        }
        
        System.out.println("    Processing InternalNode @ " + position);
        InternalNode InternaLeafNode = (InternalNode) child;
        InternaLeafNode.insert(level + 1, newnode);
    }
}