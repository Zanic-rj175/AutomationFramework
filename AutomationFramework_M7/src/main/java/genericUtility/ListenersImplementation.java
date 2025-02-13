package genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Started");

		test = report.createTest(methodname);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Successfully");
		
		test.log(Status.PASS,methodname +"---Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Failed");
		
		test.log(Status.FAIL,methodname +"---Failed");
		test.log(Status.INFO, result.getThrowable());
		
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshotname = methodname + "-" + jutil.toGetSystemDateAndTime();
		try {
			 String path = wutil.toTakeScreenShot(BaseClass.sDriver, screenshotname);
			 test.addScreenCaptureFromPath(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Skiped");
		test.log(Status.SKIP,methodname +"---Skiped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started");
		// ExtentReports
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"./ExtentReport/Report-" + new JavaUtility().toGetSystemDateAndTime() + ".html");
		htmlReport.config().setDocumentTitle("Vtiger Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BaseUrl", "http://localhost:8888/");
		report.setSystemInfo("username", "admin");
		report.setSystemInfo("password", "password");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finished");

		report.flush();

	}
}
