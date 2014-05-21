package br.com.sasolucoes.yourmarket.business.category;

import java.util.List;

import br.com.sasolucoes.yourmarket.network.HttpGetter;
import br.com.sasolucoes.yourmarket.network.JsonUtils;
import br.com.sasolucoes.yourmarket.network.ServerUtils;

class CategoryRemoteRepository {

	public List<Category> selectAll() throws Exception {
		HttpGetter get = new HttpGetter();
		get.execute(ServerUtils.getAllCategoriesUrl());
		get.get();
		String resp = get.getResponse().toString();
		
		return JsonUtils.convertCategory(resp);
	}

}
