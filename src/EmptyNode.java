/*
- EmptyNode: Empty Leaf Node to model flyweight node
- Extends Node, and is a final class (cannot be extended further)
- Singleton: Single EmptyNode instance will be re-used as a read-only marker.
*/
public final class EmptyNode extends DNANode {
    private EmptyNode() {}
    private static EmptyNode inst = new EmptyNode();
    public static EmptyNode getInst() {return inst;}
    
    public void printInfo(int level, int type) {
        System.out.println("E");
    }
}
