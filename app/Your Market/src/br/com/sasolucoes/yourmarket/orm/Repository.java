package br.com.sasolucoes.yourmarket.orm;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.util.Log;
import br.com.sasolucoes.yourmarket.network.HttpGetter;

abstract public class Repository {

	protected Boolean isOnline() {
		// TODO: Implement a network verification 
		return true;
	}
	
	
	protected String executeService(URL url)  {
		HttpGetter get = new HttpGetter();
		get.execute(url);
		
		try {
			get.get();
		} catch (InterruptedException e) {
			Log.e("Service: " + url.toString(), e.getMessage(), e);
		} catch (ExecutionException e) {
			Log.e("Service: " + url.toString(), e.getMessage(), e);
		}
		
		String resp = get.getResponse().toString();
		
		return resp;
	}
	
	
}
