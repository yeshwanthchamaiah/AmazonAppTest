package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.addchart.demo.Base;


public class Listeners implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getTestName() + " Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getTestName() + " Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getTestName() + " Failed");
		result.getThrowable().printStackTrace();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName() + " Started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName() + " Finished");
	}
	
	

}
