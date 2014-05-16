package br.com.sasolucoes.yourmarket.network;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils<T> {
	
	public static <T> JsonUtils<T> get(Class<T> clazz) {
		try {
			return new JsonUtils<T>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<T> convert(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<T>>() {	}.getType();
		
		return gson.fromJson(json, type);
	}
	
}
