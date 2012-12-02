package org.greenpeace.rhokgreenpeace;

import org.greenpeace.rhokgreenpeace.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/*
 * For some reason it throws warnings about JavaScript being enabled.
 */
@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	
	TextView textView;
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * GCM - Google Cloud Messaging - registers itself through the ID
		 * 365490956433, which belongs to the app. The id is sent to the
		 * server, which in turn uses it to make GCM relay messages. The way
		 * this is handled should probably be refined or changed entirely in
		 * the future, when a more suitable way to relay the ID to the server
		 * is known.
		 */
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		final String regId = GCMRegistrar.getRegistrationId(this);
		if (regId.equals("")) {
			GCMRegistrar.register(this, "365490956433");
		}
		else Log.v("GCMIntentService", "Already registered");
		
		/*
		 * WebView is Android's browser-like View for easy processing of HTML
		 * data. It has certain problems and certain JavaScript and CSS will
		 * result in blank pages, which are otherwise correctly read by the
		 * built in browser.
		 */
		webView = (WebView) findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		//webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("http://rhokaarhus.jit.su/events/list");
		webView.setWebChromeClient(new WebChromeClient());

		
		/*
		 * AsyncHttpClient is a workaround class that allows get and post
		 * http requests. In Android, since some version, it is not possible
		 * to make URL connections from the main thread, so this was the
		 * easiest solution.
		 */
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://rhokaarhus.jit.su/gcm/register/" + regId, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				/*
				 * Interesting fact: System.out.println(null) prints "null" in
				 * Java, whereas it throws a NullPointerException in Android.
				 */
				if (response != null) System.out.println(response);
				else System.out.println("Null message");
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
}
