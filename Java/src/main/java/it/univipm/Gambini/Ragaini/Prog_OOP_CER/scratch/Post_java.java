package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;

public class Post_java {
	
	public static String REST_POST(String geo, String obj, String filterS, String data) {
		
		URL url = null;
		HttpURLConnection conn = null;
		
		try {
			url = new URL("http://localhost:7071/api/HttpTrigger");
		} catch (MalformedURLException e3) {
			e3.printStackTrace();
		}

		try {
			conn = (HttpURLConnection)url.openConnection();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e1) {
			e1.printStackTrace();
		}
		
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setDoOutput(true);
		
		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = ClassTo.Json(data).getBytes("utf-8");
			os.write(input, 0, input.length);     
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		return "REST POST";
		
	}
	
	
	/* ho raccolto qui due metodi che effettuano post via Java, spero possiamo utilizzarli per le nostre esigenze
	 * il secondo codice mi sembra migliore
	 */
	public static String sendPost(String geo, String obj, String filter, String data) {
;
		String urlParameters = "?GEO="+geo+"&OBJ="+obj+"&Filter="+filter;
		byte[] postData = data.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		String url = "http://localhost:7071/api/HttpTrigger"+urlParameters;
		URL URL = null;
		HttpURLConnection con = null;
		try {
			URL = new URL(url);
			con = (HttpURLConnection) URL.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		con.setDoOutput(true);
		con.setInstanceFollowRedirects( false );
		//add request header
		try {
			con.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
		con.setUseCaches( false );
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		// Send post request
		DataOutputStream wr = null;
		try {
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//print result
		//System.out.println(response.toString());
		
		return response.toString();

	}
	
	    public static void send_post2(String[] args) throws Exception {
	    	
	        URL url = new URL("http://example.net/new-message.php");
	        // construct hash map with parametres
	        Map<String,Object> params = new LinkedHashMap<>();
	        params.put("name", "Freddie the Fish");
	        params.put("email", "fishie@seamail.example.com");
	        params.put("reply_to_thread", 10394);
	        params.put("message", "Shark attacks in Botany Bay have gotten out of control. We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too busy stuffing his snout with lobsters. He's so shellfish.");
	        
	        // construct a string of parametres in UTF-8 with correct format
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        // transform String post data in array of bytes
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        
	        //add header
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);

	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

	        for (int c; (c = in.read()) >= 0;)
	            System.out.print((char)c);
	    }
	}



