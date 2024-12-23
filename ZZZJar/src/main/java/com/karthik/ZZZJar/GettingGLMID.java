package com.karthik.ZZZJar;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import io.restassured.path.json.JsonPath;

//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;


import static io.restassured.RestAssured.given;

public class GettingGLMID extends Thread {

    public static List<List<String>> records = new ArrayList<>();

    int row;
    public static List<String[]> data = new ArrayList<String[]>();

    public static Map<Integer,String> mapValues= new HashMap<>();

    public GettingGLMID(int i) {
        this.row = i;
    }

    static String inputfile;


    public static void main(String[] args) throws IOException, InterruptedException, CsvException {

        String path=System.getProperty("user.home");
        inputfile =path+"\\\\Downloads\\\\DSR1016939\\\\Qrf Accelq Automation.csv";

        System.out.println(inputfile);

        try (CSVReader csvReader = new CSVReader(new FileReader(inputfile))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
      System.out.println(records.size());

        int rowIndex =1;
        for(int i=rowIndex;i<records.size();i++)
        {
            GettingGLMID ex = new GettingGLMID(rowIndex);
            ex.start();
            rowIndex++;
        }

        Thread.sleep(60000);

         for(Integer key : mapValues.keySet())
         {
             String value = mapValues.get(key);
             updateCSV(inputfile,key,value);
         }


    }

    public void run() {
        int i = row;
       String payload = "{\n" +
                "    \"AddressLine1\": \"" + records.get(i).get(1) + "\",\n" +
                "    \"City\": \"" + records.get(i).get(2) + "\",\n" +
                "    \"StateCode\": \"" + records.get(i).get(3) + "\",\n" +
                "    \"CountryCode\": \"" + records.get(i).get(5) + "\",\n" +
                "    \"PostalCode\": \"" + records.get(i).get(4) + "\",\n" +
                "    \"CreateSiteIfNotFound\": true\n" +
                "}";
        String siteID = null;
        siteID = getSiteIdFromGLMAPI(payload);

        //System.out.println("Thread id : " + GettingGLMID.currentThread().threadId());

         mapValues.put(i,siteID);
    }



    public static String getSiteIdFromGLMAPI(String payload) {

        String siteId = null;
        String base_url = "https://location-service-prod.rke-odc-prod.corp.intranet/api/LocationManager/SearchLocation";

        String auth_base_url = "https://authentication-service-prod.rke-odc-prod.corp.intranet/api/User/Authorize";
        String auth_payload = "{\n" +
                "    \"username\": \"AD40750\",\n" +
                "    \"opportunityId\": \"59421092\",\n" +
                "    \"scenarioId\": \"SM10596164\"\n" +
                "}";


        Response auth_res = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
                .body(auth_payload).when().request("POST", auth_base_url);

        JsonPath auth_json = auth_res.jsonPath();

        String token = auth_json.getString("resultObject.token");

        Response res;

        res = given().relaxedHTTPSValidation().header("Content-Type", "application/json").header("Authorization",
                        "Bearer " + token)
                .body(payload).when().request("POST", base_url);

        if(res.getStatusCode()!=200)
        {
            res = given().relaxedHTTPSValidation().header("Content-Type", "application/json").header("Authorization",
                            "Bearer " + token)
                    .body(payload).when().request("POST", base_url);
        }

        JsonPath extractor = res.jsonPath();

        int arraySize = extractor.get("resultObject.size()");

        siteId = extractor.getString("resultObject[0].siteId");

        String address = extractor.getString("resultObject[0].addressLine1Combined");

        String address1 = extractor.getString("resultObject[0].primaryGeopolitical");


        System.out.println(auth_res.getStatusCode());
        System.out.println(res.getStatusCode() +" - "+ payload + " - "+siteId);
        //return siteId + " - " + address + " - "+ address1;
        return siteId;

    }

    public static void updateCSV(String fileToUpdate,int row,String value) throws IOException, CsvException {
        File inputFile = new File(fileToUpdate);

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        // get CSV row column and replace with by using row and column
//        for(int i=0; i<csvBody.size(); i++){
//            String[] strArray = csvBody.get(i);
//            for(int j=0; j<strArray.length; j++){
//                if(strArray[j].equalsIgnoreCase("Update_date")){ //String to be replaced
//                    csvBody.get(i)[j] = "Updated_date"; //Target replacement
//                }
//            }
//        }
        csvBody.get(row)[6] = value;
        reader.close();

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
}
