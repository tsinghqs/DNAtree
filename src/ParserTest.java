import student.TestCase;
/**
 * Class to test BSTNode
 * @author tsingh
 *@version 2019
 * @param <T>
 */
public class ParserTest extends TestCase {
    
    private Parser tester;
    private String check;
    private DNAtree right;
    
    /**
     *  Sets up test cases.
     */
    public void setUp() 
    {
        
        right = new DNAtree();
        check = "insert AA";
        tester = new Parser(right, check);
    }

}
    
   
    
    