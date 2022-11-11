package testscripts;

import com.api.lib.ApiUtils;
import com.api.lib.HttpUtils;
import com.api.reports.ReportUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class RequestChaining extends HttpUtils {

    @Test
    public void post() {
        String tcId = "TC001";
        if (isTestCaseRunnable(tcId)) {
            Response response = post(endpoint, ApiUtils.generateJsonRequestBody());
            System.out.println(response.getStatusCode());
            ReportUtil.markPassed("Passed123: " + response.getStatusCode());
        }
    }
}
