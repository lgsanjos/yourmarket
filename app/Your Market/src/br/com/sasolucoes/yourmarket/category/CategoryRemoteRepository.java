package br.com.sasolucoes.yourmarket.category;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.sasolucoes.yourmarket.network.HttpGetter;
import br.com.sasolucoes.yourmarket.network.ServerUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class CategoryRemoteRepository {

	private List<Category> jsonToCategories(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Category>>() {	}.getType();
		
		return gson.fromJson(json, type);
	}
	
	public List<Category> selectAll() throws Exception {
		HttpGetter get = new HttpGetter();
		get.execute(ServerUtils.getAllCategoriesUrl());
		get.get();
		String resp = get.getResponse().toString();
		
		return jsonToCategories(resp);
	}

}
