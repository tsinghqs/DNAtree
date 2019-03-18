import java.util.ArrayList;

/**
 * SearchResults stores information
 * needed when performing search commands.
 * @author vpratha
 * @version 3.17.2019
 */
public class SearchResults 
{
    /**
     * Field for # of nodes visited.
     */
    private int nodesVisited;
    /**
     * Field for each sequence found.
     */
    private ArrayList<char[]> matches;
    
    /**
     * SearchResult's default constructor.
     */
    public SearchResults()
    {
        nodesVisited = 0;
        matches = new ArrayList<char[]>();
    }
    
    /**
     * Returns nodes visited.
     * @return nodes visited.
     */
    public int getNodesVisited()
    {
        return nodesVisited;
    }
    
    /**
     * Returns sequences found.
     * @return matches
     */
    public ArrayList<char[]> getMatches()
    {
        return matches;
    }
    
    /**
     * Updates nodes visited.
     */
    public void incrementNodesVisited()
    {
        nodesVisited++;
    }
    
    /**
     * Adds sequence to matches.
     * @param sequence the sequence to be added.
     */
    public void addMatch(char[] sequence)
    {
        matches.add(sequence);
    }
}
