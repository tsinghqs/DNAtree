/*
 - Node: Abstract base class for all DNA tree nodes
 - Currently, no data is planned.
 - Only method is the print().
*/
public abstract class DNANode {
    
    public static final int PRINT_SIMPLE = 0;
    public static final int PRINT_LENGTHS = 1;
    public static final int PRINT_STATS = 2;
    
    // print(level): public method
    // - prints the indent; 2-spaces for each level
    // - prints the node info
    public void print(int level, int type) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        printInfo(level, type);
    }
    
    // Non-public printInfo(): To be implemented in a node-type sepcific way
    protected abstract void printInfo(int level, int type);
}
