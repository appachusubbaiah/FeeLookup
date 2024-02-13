package com.ocwen;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class FeeLookupAction extends ActionSupport {
	/**
	 * 
	 */
		
	Logger logger = LogManager.getLogger(FeeLookupAction.class);
	private static final long serialVersionUID = 1L;
	private String loanNumber;
	private String satisfactionFee;
	private String wireFee;
	private String payoffFee;
	private String nsfFee;
	private String jsonInputString;
	JSONArray array;
	JSONObject object;
	/*@Autowired
	HttpServiceImpl httpServiceImpl;*/
	URL url;
	
	public String getWireFee() {
		return wireFee;
	}


	public void setWireFee(String wireFee) {
		this.wireFee = wireFee;
	}

	public String getPayoffFee() {
		return payoffFee;
	}

	public void setPayoffFee(String payoffFee) {
		this.payoffFee = payoffFee;
	}
	public String getNsfFee() {
		return nsfFee;
	}

	public void setNsfFee(String nsfFee) {
		this.nsfFee = nsfFee;
	}
	
	public String getSatisfactionFee() {
		return satisfactionFee;
	}

	public void setSatisfactionFee(String satisfactionFee) {
		this.satisfactionFee = satisfactionFee;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String execute()
	{
		/*System.out.println("Execute method");
		System.out.println("API Url = " + getText("baseurl"));*/
		
		//xthis.httpServiceImpl.test();
		jsonInputString = "{\"loanNumber\": \"" + loanNumber + "\"}";
		//System.out.println("Loan number = " + loanNumber);
		logger.info("Loan number = " + loanNumber);
		try {

            
            fetchWireFee();
            fetchSatisfactionFee();
            fetchPayoffFeeFee();
            fetchNSFFeeFee();
            
        } catch (Exception e) {
            //System.out.println("Exception in NetClientGet:- " + e);
        	logger.error("Exception in NetClientGet:" + e);
            return ERROR;
        }
		
		return SUCCESS;
	}
	
	public void fetchWireFee() throws IOException
	{
		logger.info("In fetchWireFee method.");
		url = new URL(getText("baseurl")+"/wireFee");//your url i.e fetch data from .
		logger.info("Attempting to connect to " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		logger.info("Connection opened.");
		logger.info("Attempting post request.");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(
        		  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder response = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response.append(responseLine.trim());
        		    }
        		    response.append(']');
        		    response.insert(0, '[');
        		    //System.out.println(response.toString());
        		    logger.info("Received response " + response.toString());
        		    conn.disconnect();
        		    logger.info("Connection closed");
        		    array = new JSONArray(response.toString());  
        		    for(int i=0; i < array.length(); i++)   
        		    {  
        		    JSONObject object = array.getJSONObject(i);  
        		    System.out.println(object.getString("feeName"));  
        		    System.out.println(object.getDouble("feeAmount"));
        		    this.wireFee=Double.toString(object.getDouble("feeAmount"));
        		    }  
        		    
        		}
        

	}
	
	public void fetchSatisfactionFee() throws IOException
	{
		logger.info("In fetchSatisfactionFee method.");
		url = new URL(getText("baseurl")+"/satisfactionFee");//your url i.e fetch data from .
		logger.info("Attempting to connect to " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		logger.info("Connection opened.");
		logger.info("Attempting post request.");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(
        		  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder response = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response.append(responseLine.trim());
        		    }
        		    response.append(']');
        		    response.insert(0, '[');
        		    //System.out.println(response.toString());
        		    logger.info("Received response " + response.toString());
        		    conn.disconnect();
        		    logger.info("Connection closed");
        		    array = new JSONArray(response.toString());  
        		    for(int i=0; i < array.length(); i++)   
        		    {  
        		    JSONObject object = array.getJSONObject(i);  
        		    //System.out.println(object.getString("feeName"));  
        		    //System.out.println(object.getDouble("feeAmount"));
        		    this.satisfactionFee=Double.toString(object.getDouble("feeAmount"));
        		    }  
        		    
        		}

	}

	
	public void fetchPayoffFeeFee() throws IOException
	{
		logger.info("In fetchPayoffFeeFee method.");
		url = new URL(getText("baseurl")+"/payoffFee");//your url i.e fetch data from .
		logger.info("Attempting to connect to " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		logger.info("Connection opened.");
		logger.info("Attempting post request.");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(
        		  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder response = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response.append(responseLine.trim());
        		    }
        		    response.append(']');
        		    response.insert(0, '[');
        		    //System.out.println(response.toString());
        		    logger.info("Received response " + response.toString());
        		    conn.disconnect();
        		    logger.info("Connection closed");
        		    array = new JSONArray(response.toString());  
        		    for(int i=0; i < array.length(); i++)   
        		    {  
        		    JSONObject object = array.getJSONObject(i);  
        		    //System.out.println(object.getString("feeName"));  
        		    //System.out.println(object.getDouble("feeAmount"));
        		    this.payoffFee=Double.toString(object.getDouble("feeAmount"));
        		    }  
        		    
        		}
	}
	
	public void fetchNSFFeeFee() throws IOException
	{
		logger.info("In fetchNSFFeeFee method.");
		url = new URL(getText("baseurl")+"/nsfFee");//your url i.e fetch data from .
		logger.info("Attempting to connect to " + url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		logger.info("Connection opened.");
		logger.info("Attempting post request.");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        try(BufferedReader br = new BufferedReader(
        		  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        		    StringBuilder response = new StringBuilder();
        		    String responseLine = null;
        		    while ((responseLine = br.readLine()) != null) {
        		        response.append(responseLine.trim());
        		    }
        		    response.append(']');
        		    response.insert(0, '[');
        		    //System.out.println(response.toString());
        		    logger.info("Received response " + response.toString());
        		    conn.disconnect();
        		    logger.info("Connection closed");
        		    array = new JSONArray(response.toString());  
        		    for(int i=0; i < array.length(); i++)   
        		    {  
        		    JSONObject object = array.getJSONObject(i);  
        		    //System.out.println(object.getString("feeName"));  
        		    //System.out.println(object.getDouble("feeAmount"));
        		    this.nsfFee=Double.toString(object.getDouble("feeAmount"));
        		    }  
        		    
        		}
	}
}	
