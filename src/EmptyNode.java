/**
 * EmptyNode models a flyweight node.
 * It is a final class, so it cannot be extended further.
 * Singleton: A single EmptyNode instance will be 
 * re-used as a read-only marker.
 * @author vpratha
 * @version 3.17.2019
 */
public final class EmptyNode extends DNANode 
{
    /**
     * Only instance of EmptyNode.
     */
    private static EmptyNode inst = new EmptyNode();
    
    /**
     * Private constructor so that more
     * EmptyNode objects cannot be created.
     */
    private EmptyNode() 
    {
        
    }
    
    /**
     * Returns the only instance of EmptyNode.
     * @return the flyweight node.
     */
    public static EmptyNode getInst() 
    {
        return inst;
    }
    
    /**
     * Prints "E", regardless of type.
     * @param level level of the node in tree (unused)
     * @param type type of print (unnecessary)
     */
    public void printInfo(int level, int type) 
    {
        System.out.println("E");
    }
}
