package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Classe che agisce da trasformatore per le richieste da inoltrare ad Azure
 */
public class Azure {
	
	/**Metodo che inoltra una POST request ad Azure tenendo conto di eventuali parametri specificati.
	 * @param data stringa contentente le istanze della classe di riferimento
	 * @param filter stringa formato json contenente le specifiche del filtro
	 * @return stringa relativa al tipo di servizio richiesto ad Azure
	 */
	public static String sendPost(String data, String filter) {

		String urlParameters = "&FILTER=" + filter.replace(" ","");
		System.out.println(filter);
		byte[] postData = data.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;
		String url = "https://quickstats.azurewebsites.net/api/HttpTrigger?code=wHaIvJGIHJTYK2PwWEA1SEJkSRFOstTgWyx85B/Raum7rXNiNYyBEQ==" + urlParameters;
		//String url = "https://stats2.azurewebsites.net/api/HttpTrigger" + urlParameters;
		//String url = "http://localhost:7071/api/HttpTrigger" + urlParameters;
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
		System.out.println("Parameters : NONE");// + urlParameters);
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
		return response.toString();
	}
}
