package br.com.sasolucoes.yourmarket.network;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.sasolucoes.yourmarket.business.category.Category;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	
	public static List<Category> convertCategory(final String json) {
		Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
		Gson gson = new Gson();
		List<Category> fromJson = gson.fromJson(json, collectionType);
		return fromJson;
	}
}