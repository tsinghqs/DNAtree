/**
 * DNANode is an abstract base class for all 
 * types of DNA tree nodes.
 * @author vpratha
 * @version 3.17.2019
 */
public abstract class DNANode 
{
    /**
     * Constants for different types of prints.
     */
    public static final int PRINT_SIMPLE = 0;
    public static final int PRINT_LENGTHS = 1;
    public static final int PRINT_STATS = 2;
    
    /**
     * Prints the indent based on the node's level
     * in the tree along with its corresponding
     * information.
     * @param level the level of the node in the tree
     * @param type the type of print
     */
    public void print(int level, int type) 
    {
        for (int i = 0; i < level; i++) 
        {
            System.out.print("  ");
        }
        printInfo(level, type);
    }
    
    /**
     * Method implemented by EmptyNode and
     * overridden by LeafNode and InternalNode for 
     * getting all the sequences under a certain node
     * and storing them in results.
     * @param results the SearchResults object being updated
     */
    public void searchAll(SearchResults results)
    {
        results.incrementNodesVisited();
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
    public void search(int level, char[] sequence, boolean exact, SearchResults results) 
    {
        results.incrementNodesVisited();
    }
    
    /**
     * Helper method for print(), to be implemented in a node-subclass
     * specific way.
     * @param level the level of the node in the tree
     * @param type the type of print
     */
    protected abstract void printInfo(int level, int type);
}
