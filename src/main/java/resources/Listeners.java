package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.Utilities;

public class Listeners implements ITestListener{
	
	 public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		try {
			Utilities.takeScreenShot(testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		}
}
