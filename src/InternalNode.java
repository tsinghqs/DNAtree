/**
 * InternalNode has five children DNANodes
 * and has its own methods implemented.
 * 
 * @author vpratha
 * @version 3.17.2019
 */
public class InternalNode extends DNANode {
    /**
     * Constant for number of children
     * an InternalNode can have.
     */
    private static final int NCHILDREN = 5;
    /**
     * children of this node.
     */
    private final DNANode[] children = new DNANode[NCHILDREN];


    /**
     * Initializes children to EmptyNodes.
     */
    public InternalNode() {
        for (int i = 0; i < children.length; i++) {
            children[i] = EmptyNode.getInst();
        }
    }


    /**
     * Prints itself along with all of its children.
     */
    public void printInfo(int level, int type) {
        System.out.println("I");
        for (DNANode child : children) {
            child.print(level + 1, type);
        }
    }


    /**
     * Handles insertion of a LeafNode.
     * 
     * @param level
     *            the level in the tree currently
     * @param newnode
     *            the LeafNode to be inserted
     * @flag true if new node is being inserted
     *       false when already existing node is
     *       reorganized in tree
     */
    public void insert(int level, LeafNode newnode, boolean flag) {
        char ch = newnode.getCharAt(level);
        int position = 0;
        switch (ch) {
            case 'A':
                position = 0;
                break;
            case 'C':
                position = 1;
                break;
            case 'G':
                position = 2;
                break;
            case 'T':
                position = 3;
                break;
            case 0:
                position = 4;
                break;
        }
        DNANode child = children[position];
        if (child instanceof EmptyNode) {
            children[position] = newnode;
            if (flag) {
                System.out.println("sequence " + newnode + " inserted at level "
                    + (level + 1));
            }
            return;
        }
        if (child instanceof LeafNode) {
            if (position == 4) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            LeafNode lnode = (LeafNode)child;
            if (lnode.containsSequenceOf(newnode)) {
                System.out.println("sequence " + newnode + " already exists");
                return;
            }

            InternalNode inode = new InternalNode();
            children[position] = inode;
            inode.insert(level + 1, lnode, false);
            inode.insert(level + 1, newnode, true);
            return;
        }
        InternalNode inode = (InternalNode)child;
        inode.insert(level + 1, newnode, true);
    }


    /**
     * Removes given LeafNode starting at given level.
     * 
     * @param level
     *            the level in the tree currently
     * @param node_to_remove
     *            node to be removed
     * @return a child node if it is the only child after removal
     *         or null if there are more children remaining
     */
    public DNANode remove(int level, LeafNode node_to_remove) {
        char ch = node_to_remove.getCharAt(level);
        int position = 0;
        switch (ch) {
            case 'A':
                position = 0;
                break;
            case 'C':
                position = 1;
                break;
            case 'G':
                position = 2;
                break;
            case 'T':
                position = 3;
                break;
            case 0:
                position = 4;
                break;
        }
        DNANode child = children[position];
        if (child instanceof EmptyNode) {
            System.out.println("sequence " + node_to_remove
                + " does not exist");
            return null;
        }

        // Case: The child is a leaf; replace with empty, and check for merge
        if (child instanceof LeafNode) {
            LeafNode lNode = (LeafNode) child;
            if (lNode.toString().equals(node_to_remove.toString()))
            {
                children[position] = EmptyNode.getInst();
                System.out.println("sequence " + node_to_remove + " removed");
                return getLoneNode();
            }
            else
            {
                System.out.println("sequence " + node_to_remove
                    + " does not exist");
                return null; 
            }
        }

        // Case: The child is an InternalNode
        InternalNode inode = (InternalNode)child;
        DNANode lone_node = inode.remove(level + 1, node_to_remove);

        // Perform merge
        if (lone_node != null) {
            // replace internal with leaf since this internal has just one
            // leaf
            children[position] = lone_node;
            lone_node = getLoneNode();
        }
        return lone_node;
    }


    /**
     * Helper method for remove().
     * 
     * @return the lone node
     */
    public DNANode getLoneNode() {
        int count = 0;
        DNANode ret = null;
        for (DNANode node : children) {
            if (!(node instanceof EmptyNode)) {
                if (++count > 1) {
                    return null;
                }
                ret = node;
            }
        }
        if (ret instanceof InternalNode)
        {
            return null;
        }
        return ret;
    }


    /**
     * Searches for all sequences under this node
     * and updates results.
     * 
     * @param results
     *            the SearchResults object to be updated.
     */
    public void searchAll(SearchResults results) {
        results.incrementNodesVisited();
        for (DNANode child : children) {
            child.searchAll(results);
        }
    }


    /**
     * Searches for all sequences matching given
     * sequence/prefix and updates results recursively
     * using level.
     * 
     * @param level
     *            the level of the tree currently
     * @param sequence
     *            the search term
     * @param exact
     *            if the search term is exact               
     * @param results
     *            the SearchResults object being updated
     */
    public void search(int level, char[] sequence, boolean exact, SearchResults results) {
        char ch = 0;
        if (level >= 0 && level < sequence.length) {
            ch = sequence[level];
        }

        if (ch == 0 && !exact) {
            searchAll(results);
            return;
        }

        results.incrementNodesVisited();
        DNANode child = null;
        switch (ch) {
            case 'A':
                child = children[0];
                break;
            case 'C':
                child = children[1];
                break;
            case 'G':
                child = children[2];
                break;
            case 'T':
                child = children[3];
                break;
            case 0:
                child = children[4];
                break;
        }

        // Perform an exact-search match
        if (ch == 0 && exact) {
            results.incrementNodesVisited();
            if (child instanceof LeafNode) {
                LeafNode lNode = (LeafNode) child;
                results.addMatch(lNode.toString().toCharArray());
            }
            return;
        }

        // EmptyNode: do nothing
        if (child instanceof EmptyNode) {
            results.incrementNodesVisited();
            return;
        }

        // LeafNode: verify a starts-with match (auto-includes exact match)
        if (child instanceof LeafNode) {
            results.incrementNodesVisited();
            LeafNode lnode = (LeafNode)child;
            if (exact)
            {
                if (lnode.toString().equals(String.valueOf(sequence)))
                {
                    results.addMatch(sequence);
                }
            }
            else
            {
                if (lnode.toString().startsWith(String.valueOf(sequence)))
                {
                    results.addMatch(lnode.toString().toCharArray());
                } 
            }
            return;
        }

        // InternalNode: progress to next level
        child.search(level + 1, sequence, exact, results);
    }

}