package br.com.sasolucoes.yourmarket.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HttpGetter extends AsyncTask<URL, Void, Void> {

	private StringBuilder builder = new StringBuilder();
	private final int HTTP_STATUS_CODE_OK = 200;
	
	@Override
	protected Void doInBackground(URL... urls) {
		
		final HttpClient client = new DefaultHttpClient();
		final HttpGet httpGet = new HttpGet(urls[0].toString());

		try {
			HttpResponse response = client.execute(httpGet);
			
			if (isResponseStatusOk(response)) {
				parseReponseToString(response);
				Log.v("HttpGetter", "Your data: " + builder.toString());
			} else {
				Log.e("HttpGetter", "Failed. Status: " + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("HttpGetter", e.getMessage());
		}
		
		return null;
	}
	
	private boolean isResponseStatusOk(HttpResponse response) {
		StatusLine statusLine = response.getStatusLine();
		return statusLine.getStatusCode() == HTTP_STATUS_CODE_OK;
	}

	private void parseReponseToString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		String line = "";
		do {
			builder.append(line);
			line = reader.readLine();
		} while (line != null);
	}
	
	public StringBuilder getResponse() {
		return builder;
	}
}