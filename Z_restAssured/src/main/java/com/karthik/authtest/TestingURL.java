package com.karthik.authtest;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestingURL {

	
	
	public static List<String> getSiteIdFromGLMAPI(String payload, String streetAddress) {

		String siteId = null;
		String base_url = "https://location-service-prod.rke-odc-prod.corp.intranet/api/LocationManager/SearchLocation";

		String auth_base_url = "https://authentication-service-prod.rke-odc-prod.corp.intranet/api/User/Authorize";
		String auth_payload = "{\n" + "    \"username\": \"AD42746\",\n" + "    \"opportunityId\": \"59421092\",\n"
				+ "    \"scenarioId\": \"SM10596164\"\n" + "}";

		Response auth_res = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body(auth_payload).when().request("POST", auth_base_url);

		JsonPath auth_json = auth_res.jsonPath();

		String token = auth_json.getString("resultObject.token");

		Response res;

		res = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + token).body(payload).when().request("POST", base_url);

		if (res.getStatusCode() != 200) {
			res = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).body(payload).when().request("POST", base_url);
		}

		JsonPath extractor = res.jsonPath();

		int arraySize = extractor.get("resultObject.size()");

		siteId = extractor.getString("resultObject[0].siteId");

		String address = extractor.getString("resultObject[0].addressLine1Combined");

		String address1 = extractor.getString("resultObject[0].primaryGeopolitical");

		String sitestatus = extractor.getString("resultObject[0].onNet");

		System.out.println(auth_res.getStatusCode());
		System.out.println(res.getStatusCode() + " - " + payload + " - " + siteId);

		// return siteId + " - " + address + " - "+ address1;

//		glmAddress.add(address);
//		recordAddress.add(streetAddress);
		List<String> dummy = new ArrayList<String>();
		dummy.add(siteId);
		dummy.add(sitestatus);
		return dummy;

	}
}
