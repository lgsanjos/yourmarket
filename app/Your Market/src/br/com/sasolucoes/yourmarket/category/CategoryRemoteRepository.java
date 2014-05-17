package br.com.sasolucoes.yourmarket.category;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.sasolucoes.yourmarket.network.HttpGetter;
import br.com.sasolucoes.yourmarket.network.ServerUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class CategoryRemoteRepository {

	public List<Category> selectAll() throws Exception {
		HttpGetter get = new HttpGetter();
		get.execute(ServerUtils.getAllCategoriesUrl());
		get.get();
		String resp = get.getResponse().toString();
		
		return convertFromJson(resp);
	}

	protected List<Category> convertFromJson(String resp) {
		//List<Category> convert = JsonUtils.get(Category.class).convert(resp);
		Type collectionType = new TypeToken<ArrayList<Category>>(){}.getType();
		
		Gson gson = new Gson();
		List<Category> fromJson = gson.fromJson(resp, collectionType);
		
		return fromJson;
	}

}
