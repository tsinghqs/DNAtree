import java.util.ArrayList;

public class SearchResults {
    
    private int nodesVisited;
    private ArrayList<char[]> matches;
    
    public SearchResults()
    {
        nodesVisited = 0;
        matches = new ArrayList<char[]>();
    }
    
    public int getNodesVisited()
    {
        return nodesVisited;
    }
    
    public ArrayList<char[]> getResults()
    {
        return matches;
    }
    
    public void incrementNodesVisited()
    {
        nodesVisited++;
    }
    
    public void addMatch(char[] sequence)
    {
        matches.add(sequence);
    }
}
