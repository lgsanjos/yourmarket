package br.com.sasolucoes.yourmarket;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpRequester {
	
	public static String get(final String url) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e); 
		}
		
	}

}