import org.junit.runner.JUnitCore;
import tests.TestSuite;


public class Main {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.run(TestSuite.class);
    }
}
