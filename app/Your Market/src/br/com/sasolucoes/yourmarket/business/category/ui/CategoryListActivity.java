package br.com.sasolucoes.yourmarket.business.category.ui;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import br.com.sasolucoes.yourmarket.R;
import br.com.sasolucoes.yourmarket.business.category.Category;
import br.com.sasolucoes.yourmarket.business.category.CategoryRepository;

public class CategoryListActivity extends ListActivity {


	private ArrayAdapter<Category> getCategoriesAdapter() {
		List<Category> categories = new CategoryRepository().selectAll();
		return new CategoryArrayAdapter(this, android.R.id.list, categories);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list_activity);

		setListAdapter(getCategoriesAdapter());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
