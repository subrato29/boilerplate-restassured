package testscripts;

import com.api.console.Logging;
import com.api.lib.ApiUtils;
import com.api.lib.HttpUtils;
import com.api.reports.ReportUtil;
import com.api.support.Constants;
import com.api.support.ResponseCodeFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class RequestChaining extends HttpUtils {
    String email = "";
    String name = "";
    int id;
    JSONObject requestBody;

    @Test (priority = 1)
    public void post() {
        String tcId = "TC001";
        if (isRunnable(tcId)) {
            requestBody = ApiUtils.generateRequestBody();
            Response response = post(endpoint, requestBody);
            if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_201) {
                JsonPath jsonPath = response.jsonPath();
                id = jsonPath.get(Constants.ID_KEY);
                email = jsonPath.get("email");
                name = jsonPath.get("name");
                Logging.info("CREATE user is successful with email: " + email);
                ReportUtil.markPassed("CREATE user is successful with email: " + email);
            } else {
                ReportUtil.markFailed("CREATE user is not successful");
            }
        }
    }

    @Test (priority = 2)
    public void put() {
        String tcId = "TC002";
        if (isRunnable(tcId)) {
            endpoint = endpoint + Constants.FRONT_SLASH + id;
            Response response = put(endpoint, ApiUtils.updateRequestBody(requestBody));
            if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_200) {
                JsonPath jsonPath = response.jsonPath();
                name = jsonPath.get("name");
                Logging.info("PUT user is successful where updated name: " + name);
                ReportUtil.markPassed("UPDATE user is successful with the response body: " + jsonPath.prettify());
            } else {
                ReportUtil.markFailed("UPDATE user is not successful");
            }
        }
    }

    @Test (priority = 3)
    public void get() {
        String tcId = "TC003";
        if (isRunnable(tcId)) {
            endpoint = endpoint + Constants.FRONT_SLASH + id;
            Response response = get(endpoint);
            if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_200) {
                ReportUtil.markPassed("GET user is successful");
            } else {
                ReportUtil.markFailed("GET user is not successful");
            }
        }
    }

    @Test (priority = 4)
    public void delete() {
        String tcId = "TC004";
        if (isRunnable(tcId)) {
            endpoint = endpoint + Constants.FRONT_SLASH + id;
            Response response = delete(endpoint);
            if (response.getStatusCode() == ResponseCodeFactory.RESPONSE_CODE_204) {
                ReportUtil.markPassed("DELETE user is successful");
            } else {
                ReportUtil.markFailed("DELETE user is not successful");
            }
        }
    }
}
