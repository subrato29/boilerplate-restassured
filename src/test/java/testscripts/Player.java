package testscripts;

import org.testng.annotations.Test;

import com.api.lib.ApiUtils;
import com.api.lib.HttpUtils;
import com.api.reports.ReportUtil;
import com.api.utilities.Constants;
import io.restassured.response.Response;

public class Player extends HttpUtils {
	
	String TEST_DATA = Constants.TEST_DATA;
	
	@Test
	public void TC014() {
		String tcId = "TC014";
		if(isTestCaseRunnable(tcId)) {
			
			Response response = get(endpoint);
			
			int actualStatusCode = response.getStatusCode();
			int expectedStatusCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			
			if(expectedStatusCode == actualStatusCode) {
				System.out.println(response.asString());
				ReportUtil.markPassed("Status code is correctly verified, which is: " + expectedStatusCode + " having response time " + ApiUtils.getResponseTime(response));
			} else {
				ReportUtil.markFailed("Status code is not correctly verified, "
						+ "where actual is: " + actualStatusCode 
						+ " and expected is: " + expectedStatusCode);
			}
		}
	}
	
	@Test
	public void TC015() {
		String tcId = "TC015";
		if(isTestCaseRunnable(tcId)) {
			Response response = get(endpoint);
			
			int actualStatusCode = response.getStatusCode();
			int expectedStatusCode = Integer.parseInt(xls.getCellData(TEST_DATA, "ResponseCode", rowNum));
			
			if(expectedStatusCode == actualStatusCode) {
				ReportUtil.markPassed("Status code is correctly verified, which is: " + expectedStatusCode + " having response time " + ApiUtils.getResponseTime(response));
				ReportUtil.markPassed("Response body: " + response.asString());
			} else {
				ReportUtil.markFailed("Status code is not correctly verified, "
						+ "where actual is: " + actualStatusCode 
						+ " and expected is: " + expectedStatusCode);
			}
		}
	}
}
