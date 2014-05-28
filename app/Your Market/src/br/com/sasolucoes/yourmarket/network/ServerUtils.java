package br.com.sasolucoes.yourmarket.network;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerUtils {
	
	public final static int HTTP_STATUS_CODE_OK = 200;
	private final static String port = "8080";
	private final static String address = "192.168.56.1";
	
	private final static String LIST_CATEGORIES = "get_categories";

	public static String formatedAddress() {
		return "http://" + address + ":" + port + "/";
	}
	
	public static URL createUrl(final String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static URL getServerUrl() {
		return createUrl(formatedAddress());
	}
	
	public static URL getAllCategoriesUrl() {
		return createUrl(formatedAddress() + LIST_CATEGORIES);
	}

}
