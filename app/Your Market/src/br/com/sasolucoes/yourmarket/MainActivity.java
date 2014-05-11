package br.com.sasolucoes.yourmarket;

import java.net.URL;

import br.com.sasolucoes.yourmarket.network.HttpGetter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void syncCategories() {
		try {
			HttpGetter get = new HttpGetter();
			get.execute(new URL("http://192.168.56.1:8080/get_categories"));
			get.get();
			String resp = get.getResponse().toString();
			Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
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
