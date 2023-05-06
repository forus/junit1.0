package samples;

import test.framework.Test;
import test.framework.TestCase;
import test.framework.TestResult;
import test.framework.TestSuite;

import java.util.Vector;

/**
 * A test case testing the testing framework.
 *
 */
public class TestTest extends TestCase {
    protected TestCase fFailure, fError, fSuccess;

    public TestTest(String name) {
        super(name);
    }

    protected void setUp() {
        fFailure= new TestCase("failure") {
            protected void runTest() {
                assertTrue(false);
            }
        };

        fError= new TestCase("error") {
            protected void runTest() {
                Vector v= new Vector();
                v.elementAt(-1);
            }
        };

        fSuccess= new TestCase("success") {
            protected void runTest() {
                assertTrue(true);
            }
        };
    }

    public void testFailure() {
        TestResult result= fFailure.run();
        assertTrue(result.runTests() == 1);
        assertTrue(result.testFailures() == 1);
        assertTrue(result.testErrors() == 0);
        assertTrue(! result.wasSuccessful());
    }

    public void testError() {
        TestResult result= fError.run();
        assertTrue(result.runTests() == 1);
        assertTrue(result.testFailures() == 0);
        assertTrue(result.testErrors() == 1);
        assertTrue(! result.wasSuccessful());
    }

    public void testSuccess() {
        TestResult result= fSuccess.run();
        assertTrue(result.runTests() == 1);
        assertTrue(result.testFailures() == 0);
        assertTrue(result.testErrors() == 0);
        assertTrue(result.wasSuccessful());
    }

    public static Test suite() {
        TestSuite suite= new TestSuite();

        suite.addTest(new TestTest("testFailure"));
        suite.addTest(new TestTest("testError"));
        suite.addTest(new TestTest("testSuccess"));
        return suite;
    }
}

