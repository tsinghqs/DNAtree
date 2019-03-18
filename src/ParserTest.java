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
    
    public void testParseString()
    {
        tester.parseString();
        check = "print";
        Parser test1 = new Parser(right, check);
        test1.parseString();
        check = "print length";
        Parser test2 = new Parser(right, check);
        test2.parseString();
        check = "print stats";
        Parser test4 = new Parser(right, check);
        test4.parseString();
        check = "";
        Parser test5 = new Parser(right, check);
        test5.parseString();
        
        assertEquals(check, "");
    }

}
    
   
    
    