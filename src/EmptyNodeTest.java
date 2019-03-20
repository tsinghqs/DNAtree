import student.TestCase;
/**
 * Class to test EmptyNode
 * @author tsingh
 * @version 3.17.2019
 */
public class EmptyNodeTest extends TestCase {
    
    /**
     * Field for testing.
     */
    private EmptyNode tester;
    
    /**
     *  Sets up test cases.
     */
    public void setUp() 
    {
        tester = EmptyNode.getInst();
    }
    
    /**
     * Tests getInst().
     */
    public void testGetInst()
    {
        assertTrue(tester instanceof EmptyNode);
    }
   
}