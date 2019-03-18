import student.TestCase;
/**
 * Class to test Parser.
 * @author tsingh
 * @version 2019
 */
public class ParserTest extends TestCase {
    
    /**
     * Fields for testing.
     */
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
    
   
    
    