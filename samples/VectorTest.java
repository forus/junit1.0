package samples;

import test.framework.Test;
import test.framework.TestCase;
import test.framework.TestSuite;

import java.util.Vector;

/**
 * A sample test case, testing <code>java.util.Vector</code>.
 *
 */
public class VectorTest extends TestCase {
    protected Vector<Integer> fEmpty;
    protected Vector<Integer> fFull;

    public VectorTest(String name) {
        super(name);
    }

    protected void setUp() {
        fEmpty= new Vector<>();
        fFull= new Vector<>();
        fFull.addElement(1);
        fFull.addElement(2);
        fFull.addElement(3);
    }

    public void testContains() {
        assertTrue(fFull.contains(1));
        assertTrue(!fEmpty.contains(1));
    }

    public void testElementAt() {
        Integer i= fFull.elementAt(0);
        assertTrue(i == 1);

        try {
            fFull.elementAt(fFull.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        assertTrue(false); // should raise an exception
    }

    public void testClone() {
        Vector<Integer> clone= (Vector<Integer>)fFull.clone();
        assertTrue(clone.size() == fFull.size());
        assertTrue(clone.contains(1));
    }

    public void testRemoveAll() {
        fFull.removeAllElements();
        fEmpty.removeAllElements();
        assertTrue(fFull.isEmpty());
        assertTrue(fEmpty.isEmpty());
    }

    public void testRemoveElement() {
        fFull.removeElement(3);
        assertTrue(!fFull.contains(3) );
    }

    public void testCapacity() {
        int size= fFull.size();
        for (int i= 0; i < 100; i++)
            fFull.addElement(i);
        assertTrue(fFull.size() == 100+size);
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        suite.addTest(new VectorTest("testContains"));
        suite.addTest(new VectorTest("testClone"));
        suite.addTest(new VectorTest("testRemoveAll"));
        suite.addTest(new VectorTest("testRemoveElement"));
        suite.addTest(new VectorTest("testElementAt"));
        suite.addTest(new VectorTest("testCapacity"));
        return suite;
    }

    public static Test activeSuite() {
        TestSuite suite= new TestSuite();
        for (int i= 0; i < 100; i++) {
            suite.addTest(new ActiveTest(new VectorTest("testContains"))
            );
            suite.addTest(new ActiveTest(new VectorTest("testClone")));
            suite.addTest(new ActiveTest(new VectorTest("testRemoveAll")));
            suite.addTest(new ActiveTest(new VectorTest("testRemoveElement")));
            suite.addTest(new ActiveTest(new VectorTest("testElementAt")));
            suite.addTest(new ActiveTest(new VectorTest("testCapacity")));
        }
        return suite;
    }

    public static void main (String[] args) {
        test.textui.TestRunner.run (suite());
    }
}

