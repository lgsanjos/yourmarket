package br.com.sasolucoes.yourmarket;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.sasolucoes.yourmarket.category.Category;
import br.com.sasolucoes.yourmarket.category.CategoryRepository;

public class MainActivity extends Activity {

	private CategoryRepository categoryRepository = new CategoryRepository();
	private TextView listCategorias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listCategorias = (TextView) findViewById(R.id.list_categorias);
	}

	private void syncCategories() {
		try {
			List<Category> categories = categoryRepository.selectAll();
			String text = "";
			
			for (Category cat : categories)
				text += cat.name + " - " + cat.description + "\n"; 
			
			listCategorias.setText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.sync_categories) {
			syncCategories();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
