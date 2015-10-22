package gov.state.history;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Testsuite1 {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(TestCase1.class);
    suite.addTestSuite(TestCase2.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
