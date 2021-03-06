import student.TestCase;
/**
 * Class to test DNAtree
 * @author tsingh
 * @version 2019
 */
public class DNAtreetest extends TestCase {
    
    /**
     * Field for testing.
     */
    private DNAtree tester;
    
    /**
     *  Sets up test cases.
     */
    public void setUp() 
    {
        tester = new DNAtree();
    }
    
    /**
     * Testing the insert method.
     */
    public void testInsert()
    {
        tester.insert("A");
        tester.insert("AA");
        tester.insert("F");
        String test = "ACT";
        tester.insert(test);
        tester.insert("AGT");
        tester.insert("CAG");
        tester.search("A");
        tester.search("A$");
        tester.print();
        tester.printLengths();
        tester.printStats();
        assertEquals("ACT", test);
        
    }
    
   
}
    