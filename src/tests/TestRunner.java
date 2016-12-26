import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
 

public class TestRunner {
 
	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(MediaCheckTest.class);
		for (Failure fail : result.getFailures()) {
			System.out.println(fail.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Test finished successfully...");
		
		}else{
		    System.out.println("The test failed..");
		}

    }

}
