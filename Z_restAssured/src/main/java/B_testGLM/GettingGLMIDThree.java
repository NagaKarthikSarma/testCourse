package B_testGLM;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import static io.restassured.RestAssured.given;

public class GettingGLMIDThree extends Thread {

	public static List<List<String>> records = new ArrayList<>();

	public static List<String> recordAddress = new ArrayList<String>();

	public static List<String> glmAddress = new ArrayList<String>();

	int row;
	public static List<String> printCheck = new ArrayList<String>();

	public static List<String[]> data = new ArrayList<String[]>();

	public static Map<Integer, List<String>> mapValues = new HashMap<>();

	public static int qrfSize;

	public static Set<String> plSet = new HashSet<String>();
	public static Set<String> addressSet = new HashSet<String>();
	CountDownLatch latch;

	public GettingGLMIDThree(int i, CountDownLatch latch) {
		this.row = i;
		this.latch = latch;
	}

	public GettingGLMIDThree() {
		
	}

	static String inputfile;

	public static void main(String[] args) throws IOException, InterruptedException, CsvException {

		String path = System.getProperty("user.home");
		inputfile = "Glmfetch.csv";

		String smilingFace = "\uD83D\uDE00";
		System.out.println(" *******   Hello there! I am working on your request *******");
		// System.out.println(inputfile);

		try (CSVReader csvReader = new CSVReader(new FileReader(inputfile))) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));
			}
		}
		qrfSize = records.size() - 1;
		System.out.println();
		System.out.println("Please wait until I fetch PL/GLM Id's for " + (qrfSize) + " locations");
		System.out.println();
		System.out.println("**** Don't close me, until I process *****");

		
		int rowIndex = 1;

		double rowNum = 500;
		
		double num= qrfSize/rowNum;
		
		  int roundedUp = (int) Math.ceil(num);

		  CountDownLatch latch = new CountDownLatch(records.size() - 1);

		  long startTime = System.currentTimeMillis();	

		if (qrfSize>500 ) {
			

			//		System.out.println("--------------------------------------------------------------------------------");
					
					int startFile=1;
					int endFile=500;
					for (int file=1; file <= roundedUp; file++) {
					
						if ((endFile * file )>=qrfSize ) {
							
							endFile = qrfSize;
							//System.out.println("--------------------------------------- "+endFile+ "  ----"+file);
						}
						else {
							endFile =endFile * file;	
//System.out.println("*****************************************   "+endFile+ "  ----"+file);

						}
						for (int i = startFile; i <=endFile; i++) {
							GettingGLMIDThree ex = new GettingGLMIDThree(i, latch);
							ex.start();	
						}
						startFile = endFile+1;

						
				try{
					Thread.sleep(10000);
				}
				catch(Exception e){

				}
					}
				}
				
	else {

		for (int i = rowIndex; i < records.size(); i++) {
			GettingGLMIDThree ex = new GettingGLMIDThree(i, latch);
			ex.start(); 
			

		}
	}	
	long endTime = System.currentTimeMillis();	
	
		latch.await();

		System.out.println("------------------------------------------------------ "+  (endTime-startTime) + "   -----------------");

		for (Integer key : mapValues.keySet()) {
			String value = mapValues.get(key).get(0);
			String siteStatus = mapValues.get(key).get(1);
			updateCSV(inputfile, key, value, siteStatus);
		}

		System.out.println();

		System.out.println("********* Please wait until I validate the address **********");

		System.out.println();
		GettingGLMIDThree gd = new GettingGLMIDThree();

		gd.fallout();

		gd.duplicateAddress();

		gd.AddressValidate();

		String str = "";

		for (String word : printCheck) {

			str = str + word + " ";
		}

		if (str.contains("validate") && str.contains("duplicate") && str.contains("fallout")) {

			System.out.println();
			System.out.println(
					"********  The above address have Invalid, duplicate and fallout locations. Please modify those for accuracy  ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();

		} else if (str.contains("validate") && str.contains("fallout")) {

			System.out.println();
			System.out.println(
					"********  The above address have Invalid and fallout locations. Please modify those ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();

		} else if (str.contains("fallout") && str.contains("duplicate")) {

			System.out.println();
			System.out.println(
					"********  The above address have fallout and duplicate locations. Please modify those ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();
		} else if (str.contains("validate") && str.contains("duplicate")) {

			System.out.println();
			System.out.println(
					"********  The above address have Invalid and duplicat locations. Please modify those ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();
		} else if (str.contains("validate")) {

			System.out.println();
			System.out.println(
					"********  The above address have Invalid locations. Please modify the addresses ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();

		} else if (str.contains("fallout")) {

			System.out.println();
			System.out.println("********  The above address have fallout locations. Please provide correctly ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();

		} else if (str.contains("duplicate")) {

			System.out.println();
			System.out.println("********  The above address have duplicate locations. Please remove those ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();

		} else {
			System.out.println();
			System.out.println("********  Address are valid, PL/GLM ID's are added to the file  ********");
			System.out.println();
			System.out.println("Total No of locations present in the QRF File: " + qrfSize);
			System.out.println();
			System.out.println("you can close me");
			System.out.println();
		}
	}



	public void run() {
		int i = row;
		String streetAddress = records.get(i).get(1);
		String payload = "{\n" + "    \"AddressLine1\": \"" + records.get(i).get(1) + "\",\n" + "    \"City\": \""
				+ records.get(i).get(2) + "\",\n" + "    \"StateCode\": \"" + records.get(i).get(3) + "\",\n"
				+ "    \"CountryCode\": \"" + records.get(i).get(5) + "\",\n" + "    \"PostalCode\": \""
				+ records.get(i).get(4) + "\",\n" + "    \"CreateSiteIfNotFound\": true\n" + "}";
		List<String> siteID = null;
		siteID = getSiteIdFromGLMAPI(payload, streetAddress);

		mapValues.put(i, siteID);
		latch.countDown();
		
		System.out.println("-------------------   "+ latch+" ---------------------------------------");
	}

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

		List<String> TotalList = new ArrayList<>();

	

//System.out.println(extractor.getString("resultObject[0]"));

System.out.println(extractor.getString("resultObject[0].locationProductInfos[0].productName"));




		System.out.println(auth_res.getStatusCode());
		System.out.println(res.getStatusCode() + " - " + payload + " - " + siteId);




		

		// return siteId + " - " + address + " - "+ address1;

		glmAddress.add(address);
		recordAddress.add(streetAddress);
		List<String> dummy = new ArrayList<String>();
		dummy.add(siteId);
		dummy.add(sitestatus);
		return dummy;

	}

	public static void updateCSV(String fileToUpdate, int row, String value, String siteStatus)
			throws IOException, CsvException {
		File inputFile = new File(fileToUpdate);

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile));
		List<String[]> csvBody = reader.readAll();

		csvBody.get(row)[6] = value;
		csvBody.get(row)[7] = siteStatus;

		if (csvBody.get(row)[7] == null) {
			csvBody.get(row)[8] = "NotFound";

		}
		
		if(csvBody.get(row)[6] ==null) {
		
		}
		else if (!plSet.add(value)) {
			csvBody.get(row)[8]="Duplicate";
		}
		
		
		
		reader.close();

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
		writer.writeAll(csvBody);
		
		writer.flush();
		writer.close();

	}

	public void AddressValidate() throws IOException, CsvException {

		int cnt = 0;
		Map<String, String> validAddress = new HashMap<String, String>();

		for (int i = 0; i < recordAddress.size(); i++) {
			cnt = 0;

			String[] adstr = recordAddress.get(i).toUpperCase().split(" ");

			for (String word : adstr) {

				if (glmAddress.get(i) == null) {
					continue;

				} else if (glmAddress.get(i).contains(word)) {
					cnt = cnt + 1;

				}

			}

			if (adstr.length > 3 && (glmAddress.get(i) != null)) {
				int size = Math.round((adstr.length) / 2);

				if (cnt < (size + 1)) {

					validAddress.put(recordAddress.get(i), glmAddress.get(i));
					updateInvalidCsv(recordAddress.get(i), glmAddress.get(i));
					// System.out.println("Address in QRF: "+recordAddress.get(i)+" Glm Address:
					// "+glmAddress.get(i));
				}
			} else if (adstr.length == 3 && (glmAddress.get(i) != null)) {
				if (cnt < 2) {

					validAddress.put(recordAddress.get(i), glmAddress.get(i));
					updateInvalidCsv(recordAddress.get(i), glmAddress.get(i));
					// System.out.println("Address in QRF: "+recordAddress.get(i)+" Glm Address:
					// "+glmAddress.get(i));
				}
			} else if (adstr.length == 2 && (glmAddress.get(i) != null)) {
				if ((glmAddress.get(i) == recordAddress.get(i).toUpperCase())) {
					validAddress.put(recordAddress.get(i), glmAddress.get(i));
					updateInvalidCsv(recordAddress.get(i), glmAddress.get(i));
					// System.out.println("Address in QRF: "+recordAddress.get(i)+" Glm Address:
					// "+glmAddress.get(i));
				}
			} else {
				if ((glmAddress.get(i) != null)) {
					validAddress.put(recordAddress.get(i), glmAddress.get(i));
					updateInvalidCsv(recordAddress.get(i), glmAddress.get(i));
					// System.out.println("Address in QRF: "+recordAddress.get(i)+" Glm Address:
					// "+glmAddress.get(i));
				}
			}

		}
		if (validAddress.size() > 0) {
			System.out.println();
			System.out.println("*******************   Invalid Address Locations  ******************");
			System.out.println();
			System.out.println("Total invalid address locations: " + validAddress.size());
			System.out.println("The following address are invalid address: ");
			System.out.println();

			for (Map.Entry<String, String> entry : validAddress.entrySet()) {
				String qrfAddress = entry.getKey();
				String glmvalid = entry.getValue();

				System.out.println("Address in QRF: " + qrfAddress + "     Glm Address:  " + glmvalid);
			}
			printCheck.add("validate");
		}
		System.out.println();
		// for (int i = 0; i < recordAddress.size(); i++) {

		// if ( !((recordAddress.get(i).toUpperCase()).equals( glmAddress.get(i))) &&
		// (glmAddress.get(i)!= null)) {

		// System.out.println("Address in QRF: "+recordAddress.get(i)+" Glm Address:
		// "+glmAddress.get(i));
		// }

		// }

	}

	public void duplicateAddress() throws IOException, CsvException{

		
		Set<String> SetPL = new HashSet<String>();
		Map<String, Integer> repeatedValues = new HashMap<>();
		File inputFile = new File("Glmfetch.csv");

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile));
		List<String[]> csvBody = reader.readAll();

		int cnt = 0;
		for (int i = 1; i < records.size(); i++) {
			String address = csvBody.get(i)[1];
			String plId= csvBody.get(i)[6];

			if (csvBody.get(i)[8].equals("Duplicate")){
				cnt = cnt + 1;
		repeatedValues.put(address, repeatedValues.getOrDefault(address, 0) + 1);
			}
// 			if (!addressSet.add(plId)) {
// 				cnt = cnt + 1;
// 				repeatedValues.put(address, repeatedValues.getOrDefault(address, 0) + 1);

// 			} 
// if (!addressSet.add(address)) {
// 	cnt = cnt + 1;
// 	repeatedValues.put(address, repeatedValues.getOrDefault(address, 0) + 1);

//  }
}
		reader.close();

		if (repeatedValues.size() > 0) {
			System.out.println();
			System.out.println("************************ Duplicate Locations **********************");
			System.out.println();
			System.out.println("Total Duplicate locations: " + repeatedValues.size());
			System.out.println("The following address are duplicate address: ");
			System.out.println();
			for (Map.Entry<String, Integer> entry : repeatedValues.entrySet()) {
				String repeatedValue = entry.getKey();
				int count = entry.getValue();
				System.out.println("Duplicate Address: " + repeatedValue);
			}
			printCheck.add("duplicate");
			System.out.println();
		}
	}

	public void fallout() {

		List<String> notValid = new ArrayList<String>();
		int cnt = 0;
		for (int i = 0; i < glmAddress.size(); i++) {

			if ((glmAddress.get(i) == null)) {

				notValid.add(recordAddress.get(i));
				cnt = cnt + 1;
			}
		}

		if (notValid.size() > 0) {
			System.out.println("******************   Fallout locations  ****************** ");
			System.out.println();
			System.out.println("Total fallout locations:  " + cnt);
			System.out.println("The following address doesnot have valid GLM id");
			System.out.println();

			for (String word : notValid) {
				System.out.println(word);
			}
			printCheck.add("fallout");
			System.out.println();

		}

	}

	public static void updateInvalidCsv(String source, String target) throws IOException, CsvException {
		
		int rowIndex=0;
	
		File inputFile = new File("Glmfetch.csv");

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile));
		List<String[]> csvBody = reader.readAll();
	
		for (int i = 1; i < csvBody.size(); i++) {
			String innerList = csvBody.get(i)[1];
			if (innerList.contains(source)) {
				rowIndex = i;
				break;
			}
		}

	

		reader.close();
		csvBody.get(rowIndex)[9] = target;
	//	System.out.println("-------------------------------------------       " + rowIndex + "     ------------------------");

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
		writer.writeAll(csvBody);

		writer.flush();
		writer.close();

	}
}
