package john.westendarp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HttpExample extends Activity {

	
	TextView httpStuff;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_example);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		GetMethodEx test = new GetMethodEx();
		String returned = null;
		try {
			returned = test.getInternetData();
			httpStuff.setText(returned);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
