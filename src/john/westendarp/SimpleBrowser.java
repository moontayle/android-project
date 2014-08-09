package john.westendarp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView ourBrowser;
	Button go, back, refresh, forward, clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		initalize();
		try {
			ourBrowser.loadUrl("http://www.mybringback.com");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initalize() {
		// TODO Auto-generated method stub
		ourBrowser = (WebView) findViewById(R.id.wvBrowser);
		ourBrowser.setWebViewClient(new ourViewClient());
		ourBrowser.getSettings().setJavaScriptEnabled(true);
		ourBrowser.getSettings().setLoadWithOverviewMode(true);
		ourBrowser.getSettings().setUseWideViewPort(true);

		go = (Button) findViewById(R.id.bGo);
		back = (Button) findViewById(R.id.bBack);
		refresh = (Button) findViewById(R.id.bRefresh);
		forward = (Button) findViewById(R.id.bForward);
		clear = (Button) findViewById(R.id.bHistory);
		url = (EditText) findViewById(R.id.etURL);
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bGo:
			String theWebsite = url.getText().toString();
			ourBrowser.loadUrl(theWebsite);
			
			// Hide the keyboard
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if (ourBrowser.canGoBack())
				ourBrowser.goBack();
			break;
		case R.id.bForward:
			if (ourBrowser.canGoForward())
				ourBrowser.goForward();
			break;
		case R.id.bRefresh:
			ourBrowser.reload();
			break;
		case R.id.bHistory:
			ourBrowser.clearHistory();
			break;
		}
	}

}
